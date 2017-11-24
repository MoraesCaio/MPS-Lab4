package business.control.login;
import business.model.User;
import java.util.Date;

class FacebookAPI
{
    User loginV2(String login, String password, Date date)
    {
        System.out.println("Login by Facebook.");
        return new User(login, password);
    }
}