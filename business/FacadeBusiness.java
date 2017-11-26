package business;

import business.control.login.LoginInterface;
import business.control.report.PDFReport;
import business.control.report.ReportTemplate;
import business.control.report.ReportWriter;
import business.control.report.XMLReport;
import business.model.User;
import business.model.tree.Member;
import business.control.tree.TreeController;
import business.control.tree.AddMemberCommand;
import business.control.tree.SearchMemberCommand;
import business.control.tree.UpdateMemberCommand;
import infra.UserDAO;
import infra.UserDAOFactory;

import java.util.List;

public class FacadeBusiness
{
    User currentUser;
    private TreeController treeController;
    private AddMemberCommand addMemberCommand;
    private SearchMemberCommand searchMemberCommand;
    private UpdateMemberCommand updateMemberCommand;
    public UserDAO userDAO;

    public FacadeBusiness(String typeDAO)
    {
        this.userDAO = UserDAOFactory.getPersistent(typeDAO);
    }

    public void addUser(String login, String password)
    {
        User newUser = null;
        if (login.length() >= 6)
        {
            if (password.length() >= 4)
            {
                User foundUser = null;

                try {
                    foundUser = userDAO.getUser(login);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                if (foundUser == null)
                {
                    System.out.println(login);
                    newUser = new User(login, password);
                    try {
                        userDAO.saveUser(newUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            else
            {
                System.out.println("Senha deve possuir pelo menos 4 caracteres.");
            }
        }
        else
        {
            System.out.println("Nome deve possuir pelo menos 6 caracteres.");
        }
        currentUser = newUser;
        if(currentUser != null)
        {
            initTree();
        }
    }
    
    public void login(String login, String password)
    {
        User userFound = null;

        try
        {
            User user = userDAO.getUser(login);

            if (user != null)
            {
                System.out.println("Usuário encontrado.");
                if (user.getPassword().equals(password))
                {
                    userFound = user;
                }
                else
                {
                    System.out.println(password);
                    System.out.println(user.getPassword());
                    System.out.println("Senha inválida. Tente novamente.");
                }
            } else
            {
                System.out.println("Usuário não encontrado.");
                return;
            }

            currentUser = userFound;
            initTree();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void externalLogin(String name, String password, LoginInterface.Adapter adapter)
    {
        try {
            User userFound = LoginInterface.getAdapter(adapter).login(name, password);
            if (userFound == null) return;
            currentUser = userFound;
            initTree();
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void logout()
    {
        currentUser = null;
    }

    private void initTree()
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
        Member member = treeController.setTreeCommand(addMemberCommand).run();
        try {
            if(member != null) userDAO.addMember(currentUser, member);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        Member member = treeController.setTreeCommand(updateMemberCommand).run();
        try {
            if(member != null) userDAO.updateMember(currentUser, gender, name, newGender, newName);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public void report(ReportTemplate reportTemplate)
    {
        ReportWriter reportWriter;

        reportWriter = new ReportWriter(reportTemplate);

        reportWriter.writeReport(currentUser);
    }
}
