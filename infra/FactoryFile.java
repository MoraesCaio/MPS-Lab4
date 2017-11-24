package infra;

class FactoryFile extends UserDAOFactory
{
    UserDAO getDAO()
    {
        return new UserDAOFile();
    }
}
