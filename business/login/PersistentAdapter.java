package business.login;

import business.model.User;

public class PersistentAdapter extends LoginInterface
{
    RegisterManager registerManager;

    PersistentAdapter()
    {
        registerManager = new RegisterManager();
    }

    public User login(String login, String password) throws Exception
    {
        System.out.println("Login by local server.");
        return registerManager.login(login, password);
    }

    public User signUp(String login, String password) throws Exception
    {
        System.out.println("Signing up on server...");
        return registerManager.signUp(login, password);
    }
}