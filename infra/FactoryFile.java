package infra;

public class FactoryFile extends UserDAOFactory
{
    protected UserDAO getDAO()
    {
        return new UserDAOFile();
    }
}
