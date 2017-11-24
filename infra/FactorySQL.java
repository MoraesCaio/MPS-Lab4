package infra;

public class FactorySQL extends UserDAOFactory
{
    protected UserDAO getDAO()
    {
        return new UserDAODB();
    }
}
