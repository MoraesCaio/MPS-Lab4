package infra;

public abstract class UserDAOFactory
{
    public enum Type
    {
        File,
        SQL
    }

    public static UserDAO getPersistent(Type type)
    {
        switch(type)
        {
            case File:
                return new FactoryFile().getDAO();
            case SQL: default:
                return new FactorySQL().getDAO();
        }
    }
}
