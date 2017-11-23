package business.login;
import business.model.User;

public class FacebookAPI
{
    User login(String login, String password)
    {
        System.out.println("Login by Facebook.");
        return new User(login, password);
    }
}