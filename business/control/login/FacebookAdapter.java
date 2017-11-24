package business.control.login;

import business.model.User;


public class FacebookAdapter extends LoginInterface
{
    FacebookAPI facebookAPI;

    FacebookAdapter()
    {
        facebookAPI = new FacebookAPI();
    }

    public User login(String login, String password)
    {
        return facebookAPI.login(login, password);
    }
}