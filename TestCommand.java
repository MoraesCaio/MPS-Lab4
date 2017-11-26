import business.FacadeBusiness;
import business.model.User;
import business.model.tree.Member;
import infra.UserDAOFactory;

import java.util.List;
import java.util.Scanner;

/**
 * Created by caiomoraes on 22/11/17.
 */
public class TestCommand
{
    public static void main(String[] args)
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness(UserDAOFactory.Type.File);
        facadeBusiness.addUser("Janyelson", "12345");
        try {
            User user = facadeBusiness.userDAO.getUser("Janyelson");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
