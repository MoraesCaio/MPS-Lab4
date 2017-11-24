package business.control.login;

import business.model.User;

public abstract class LoginInterface
{
    public abstract User login(String login, String password) throws Exception;

    public enum Adapter{
        Facebook,
        Twitter,
    }

    public static LoginInterface getAdapter(Adapter adapter)
    {
        if (adapter.equals(Adapter.Facebook)){
            return new FacebookAdapter();
        }
        if (adapter.equals(Adapter.Twitter)){
            return new TwitterAdapter();
        }
        return null;
    }
}