package lapr.project.controller;

import lapr.project.data.ConnectionFactory;
import lapr.project.data.DatabaseConnection;
import lapr.project.data.login.AuthFacade;
import lapr.project.data.login.UserSession;
import lapr.project.model.Company;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class App {

    private final DatabaseConnection databaseConnection;

    private final Company company;

    private final AuthFacade authFacade;

    private App (){
        company=new Company();
        this.databaseConnection = initializeConnection();
        authFacade = new AuthFacade();
    }

    public Company getCompany(){
        return company;
    }

    private static App singleton = null;

    public static App getInstance(){
        if(singleton == null){
            synchronized (App.class){
                singleton=new App();
            }
        }
        return singleton;
    }

    /**
     * Method responsible for initializing the database connection.
     * @return the database connection
     */
    private DatabaseConnection initializeConnection(){
        DatabaseConnection databaseConnection = null;
        try {
            databaseConnection = ConnectionFactory.getInstance()
                    .getDatabaseConnection();
        } catch (IOException exception) {
            Logger.getLogger(App.class.getName())
                    .log(Level.SEVERE, null, exception);
        }

        return databaseConnection;
    }

    public DatabaseConnection getDatabaseConnection(){
        return this.databaseConnection;
    }


    public UserSession getCurrentUserSession()
    {
        return this.authFacade.getCurrentUserSession();
    }

    public boolean doLogin(String username, String pwd) throws SQLException {
        return this.authFacade.doLogin(username,pwd).isLoggedIn();
    }

    public void doLogout()
    {
        this.authFacade.doLogout();
    }
}
