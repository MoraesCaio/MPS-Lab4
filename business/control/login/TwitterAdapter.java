package business.control.login;

import business.model.User;
import java.util.Date;

public class TwitterAdapter extends LoginInterface
{
    private TwitterAPI twitterAPI;

    TwitterAdapter()
    {
        TwitterAPI twitterAPI = new TwitterAPI();
    }

    public User login(String login, String password)
    {
        return twitterAPI.loginV3(new Date(), login, password);
    }
}