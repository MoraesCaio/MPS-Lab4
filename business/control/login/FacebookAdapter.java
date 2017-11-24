package business.control.login;

import business.model.User;

import java.util.Date;


public class FacebookAdapter extends LoginInterface
{
    private FacebookAPI facebookAPI;

    FacebookAdapter()
    {
        facebookAPI = new FacebookAPI();
    }

    public User login(String login, String password)
    {
        return facebookAPI.loginV2(login, password, new Date());
    }
}