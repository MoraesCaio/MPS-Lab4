package business;

import business.login.LoginInterface;
import business.login.PersistentAdapter;
import business.model.User;
import business.model.tree.Member;
import business.model.tree.TreeController;
import business.model.tree.command.AddMemberCommand;
import business.model.tree.command.SearchMemberCommand;
import business.model.tree.command.UpdateMemberCommand;

/**
 * Created by caiomoraes on 22/11/17.
 */
public class FacadeBusiness
{
    LoginInterface loginInterface;
    User currentUser;
    TreeController treeController;
    AddMemberCommand addMemberCommand;
    SearchMemberCommand searchMemberCommand;
    UpdateMemberCommand updateMemberCommand;

    public FacadeBusiness(LoginInterface.Adapter adapter)
    {
        this.loginInterface = LoginInterface.getAdapter(adapter);
    }

    public FacadeBusiness()
    {
        this(LoginInterface.Adapter.Persistent);
    }

    public void addUser(String name, String password)
    {
        if (loginInterface instanceof PersistentAdapter)
        {
            PersistentAdapter persistentAdapter = (PersistentAdapter) loginInterface;
            User foundUser = null;
            try
            {
                foundUser = persistentAdapter.signUp(name, password);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            if (foundUser != null)
            {
                currentUser = foundUser;
            }
        }
    }

    public void login(String name, String password)
    {
        try
        {
            User userFound = loginInterface.login(name, password);
            if (userFound == null) return;
            initTree();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void logout()
    {
        currentUser = null;
    }

    public void initTree()
    {
        treeController = new TreeController();

        addMemberCommand = new AddMemberCommand(currentUser.getGenealogicalTree());
        searchMemberCommand = new SearchMemberCommand(currentUser.getGenealogicalTree());
        updateMemberCommand = new UpdateMemberCommand(currentUser.getGenealogicalTree());
    }

    public void addMemberInTree(String gender, String name)
    {
        if (currentUser == null)
        {
            return;
        }
        addMemberCommand.memberInfo.gender = (gender == "M");
        addMemberCommand.memberInfo.name = name;
        treeController.setTreeCommand(addMemberCommand).run();
    }

    public void updateMemberInTree(String gender, String name, String newGender, String newName)
    {
        if (currentUser == null)
        {
            return;
        }
        updateMemberCommand.memberInfo.gender = (gender == "M");
        updateMemberCommand.memberInfo.name = name;
        updateMemberCommand.memberNewInfo.gender = (gender == "M");
        updateMemberCommand.memberNewInfo.name = newName;
        treeController.setTreeCommand(updateMemberCommand).run();
    }

    public Member searchMemberInTree(String gender, String name)
    {
        if (currentUser == null)
        {
            return null;
        }
        searchMemberCommand.memberInfo.gender = (gender == "M");
        searchMemberCommand.memberInfo.name = name;
        return treeController.setTreeCommand(searchMemberCommand).run();
    }
}
