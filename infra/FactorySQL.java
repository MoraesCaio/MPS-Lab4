package infra;

class FactorySQL extends UserDAOFactory
{
    UserDAO getDAO()
    {
        return new UserDAODB();
    }
}
