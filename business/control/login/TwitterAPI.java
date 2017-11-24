package business.control.login;

import business.model.User;
import java.util.Date;

class TwitterAPI
{
    User loginV3(Date date, String login, String password)
    {
        System.out.println("Login by Twitter");
        return new User(login, password);
    }
}