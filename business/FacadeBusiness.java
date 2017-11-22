package business;

import business.model.User;
import business.model.tree.Member;
import business.model.tree.TreeController;
import business.model.tree.command.AddMemberCommand;
import business.model.tree.command.SearchMemberCommand;
import business.model.tree.command.UpdateMemberCommand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by caiomoraes on 22/11/17.
 */
public class FacadeBusiness
{
    public List<User> userList = new ArrayList<User>();
    User currentUser;
    TreeController treeController;
    AddMemberCommand addMemberCommand;
    SearchMemberCommand searchMemberCommand;
    UpdateMemberCommand updateMemberCommand;


    public void addUser(String name, String password)
    {
        if (name.length() >= 6)
        {
            if (password.length() >= 4)
            {
                for (User user : userList)
                {
                    if (user.getName().equals(name))
                    {
                        System.out.println("Usuário já existe.");
                        return;
                    }
                }
                currentUser = new User(name, password);
                userList.add(currentUser);
                login(name, password);
            }
            System.out.println("Senha deve possuir pelo menos 4 caracteres.");
        }
        System.out.println("Nome deve possuir pelo menos 6 caracteres.");
    }

    public void login(String name, String password)
    {
        Scanner s = new Scanner(System.in);
        for (User user : userList)
        {
            if (user.getName().equals(name))
            {
                for (int i = 0; i < 3; i++)
                {
                    System.out.println("Usuário encontrado.");
                    if (user.getPassword().equals(password))
                    {
                        currentUser = user;
                        initTree();
                        System.out.println("Login realizado com sucesso");
                        return;
                    }
                    if (i == 2)
                    {
                        break;
                    }
                    System.out.println("Senha inválida. Tente novamente.");
                    password = s.nextLine();
                }
                System.out.println("Três tentativas inválidas");
                return;
            }
        }
        System.out.println("Usuário não encontrado.");
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
        addMemberCommand.memberInfo.gender = (gender == "M") ? true : false;
        addMemberCommand.memberInfo.name = name;
        treeController.setTreeCommand(addMemberCommand).run();
    }

    public void updateMemberInTree(String gender, String name, String newGender, String newName)
    {
        if (currentUser == null)
        {
            return;
        }
        updateMemberCommand.memberInfo.gender = (gender == "M") ? true : false;
        updateMemberCommand.memberInfo.name = name;
        updateMemberCommand.memberNewInfo.gender = (gender == "M") ? true : false;
        updateMemberCommand.memberNewInfo.name = newName;
        treeController.setTreeCommand(updateMemberCommand).run();
    }

    public Member searchMemberInTree(String gender, String name)
    {
        if (currentUser == null)
        {
            return null;
        }
        searchMemberCommand.memberInfo.gender = (gender == "M") ? true : false;
        searchMemberCommand.memberInfo.name = name;
        return treeController.setTreeCommand(searchMemberCommand).run();
    }
}
