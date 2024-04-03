package lapr.project.data.login;

import lapr.project.controller.App;

import java.sql.SQLException;


/**
 *
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class AuthController {

    private final App app;

    public AuthController()
    {
        this.app = App.getInstance();
    }

    public boolean doLogin(String username, String password)
    {
        try {
            return this.app.doLogin(username, password);
        } catch(IllegalArgumentException | SQLException ex)
        {
            return false;
        }
    }

    public Role getUserRole()
    {
        if (this.app.getCurrentUserSession().isLoggedIn())
        {
            return this.app.getCurrentUserSession().getUserRole();
        }
        return null;
    }

    public void doLogout()
    {
        this.app.doLogout();
    }
}
