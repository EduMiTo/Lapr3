package lapr.project.data.login;

/**
 *
 * @author Eduardo Silva <1201371@isep.ipp.pt>
 */
public class UserSession{

    private User user = null;

    public UserSession()
    {
        this.user = null;
    }

    public UserSession(User user)
    {
        if (user == null)
            throw new IllegalArgumentException("Argument cannot be null.");
        this.user = user;
    }

    public void doLogout()
    {
        this.user = null;
    }

    public boolean isLoggedIn()
    {
        return this.user != null;
    }

    public String getUserNam()
    {
        if (isLoggedIn())
            this.user.getUsername();
        return null;
    }

    public Role getUserRole()
    {
        if (isLoggedIn()) {
            return user.getUserRole();
        }
        return null;
    }

}
