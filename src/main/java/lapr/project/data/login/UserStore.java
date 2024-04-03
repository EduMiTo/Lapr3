package lapr.project.data.login;

import lapr.project.controller.App;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class UserStore {

    private Connection connectionDatabase;
    private final RoleStore roleStore;

    public UserStore(){
        roleStore = new RoleStore();
    }

    public boolean createUser(String username, String password, String roleName) throws SQLException {
        if (userExist(username,password)) return false;
        else{
            connectionDatabase= App.getInstance().getDatabaseConnection().getConnection();
            String sqlCommand = "insert into Users(username, password, roleId) values(?, ?, ?)";

            try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
                String hashedPassword = hashPassword(password);

                preparedStatement.setString(1, username);
                preparedStatement.setString(2, hashedPassword);
                preparedStatement.setInt(3, roleStore.getRoleId(roleName));

                try {
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }
    }

    private boolean userExist(String userName, String password) throws SQLException {


        connectionDatabase= App.getInstance().getDatabaseConnection().getConnection();
        boolean isUserOnDatabase;
        String hashedPassword="";
        String sqlCommand = "select password from users where username = ?";

        try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, userName);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                userResultSet.next();

                hashedPassword = userResultSet.getString("password");


                isUserOnDatabase = checkPass(password, hashedPassword);


                userResultSet.close();
            } catch (Exception e) {
                isUserOnDatabase = false;
                e.printStackTrace();
            }
        }
        return isUserOnDatabase;
    }


    public User getByUsername(String username, String password) throws SQLException {


        if(userExist(username,password)) {
            connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();
            String userNames;
            String passwords;
            int roleId;


            String sqlCommand = "select username,password,roleId from users where username = ?";

            try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
                preparedStatement.setString(1, username);

                try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                    userResultSet.next();
                    userNames = userResultSet.getString("username");
                    passwords = userResultSet.getString("password");
                    roleId = userResultSet.getInt("roleId");


                    userResultSet.close();
                } catch (SQLException e) {
                    System.out.println(e.getErrorCode());
                    e.printStackTrace();
                    return null;
                }
            }

            return new User(userNames, passwords, new Role(roleId, roleStore.getRoleName(roleId)));
        }else{
            return null;
        }
    }

    public boolean checkPass(String plainPassword, String hashedPassword) {

        return BCrypt.checkpw(plainPassword, hashedPassword);

    }

    public String hashPassword(String plainTextPassword){
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }
}
