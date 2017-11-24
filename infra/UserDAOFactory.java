package infra;

public abstract class UserDAOFactory
{
    public static UserDAO getPersistent(String type)
    {
        switch(type)
        {
            case "sql":
                return new FactorySQL().getDAO();
            case "file":
                return new FactoryFile().getDAO();
            default:
                return new FactorySQL().getDAO();
        }
    }
}
