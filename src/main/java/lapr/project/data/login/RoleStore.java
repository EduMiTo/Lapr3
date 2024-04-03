package lapr.project.data.login;

import lapr.project.controller.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class RoleStore {

    private Connection connectionDatabase;

    public RoleStore(){
        //connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();
    }

    public boolean roleExist(int id, String description) throws SQLException {
        connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

        boolean isRoleOnDatabase;

        String sqlCommand = "select id from Role where id = ? and designation = ?";

        try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, description);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                isRoleOnDatabase = userResultSet.next();
                userResultSet.close();
            }
        }
        return isRoleOnDatabase;
    }

    public int getRoleId(String roleName) throws SQLException {
        connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

        int roleId;

        String sqlCommand = "select id from Role where designation = ?";

        try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
            preparedStatement.setString(1, roleName);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                userResultSet.next();
                roleId = userResultSet.getInt(1);
                userResultSet.close();
            }
        }
        return roleId;
    }

    public String getRoleName(int id) throws SQLException {
        connectionDatabase = App.getInstance().getDatabaseConnection().getConnection();

        String roleName;

        String sqlCommand = "select designation from Role where id = ?";

        try (PreparedStatement preparedStatement = connectionDatabase.prepareStatement(sqlCommand)) {
            preparedStatement.setInt(1, id);

            try (ResultSet userResultSet = preparedStatement.executeQuery()) {
                userResultSet.next();
                roleName = userResultSet.getString("designation");
                userResultSet.close();
            }
        }
        return roleName;
    }



}
