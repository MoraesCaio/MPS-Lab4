package business.control.login;

import business.model.User;
import java.util.Date;

public class TwitterAPI
{
    User login(String login, String password, Date date)
    {
        System.out.println("Login by Twitter");
        return new User(login, password);
    }
}