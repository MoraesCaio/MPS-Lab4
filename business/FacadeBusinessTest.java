package business;

import business.control.report.PDFReport;
import business.control.report.ReportTemplate;
import business.control.report.XMLReport;
import business.model.User;
import business.model.tree.Member;

import infra.UserDAOFactory;
import infra.UserDAOFile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FacadeBusinessTest
{
    private String[] users = new String[7];
    private String[] passwords = new String[3];
    private String[] members = new String[3];
    private FacadeBusiness facadeBusiness = new FacadeBusiness(UserDAOFactory.Type.File);


    @BeforeEach
    void setUp()
    {
        users[0] = "aaabb";
        users[1] = "janyelson";
        users[2] = "victor";
        users[3] = "lacerda";
        users[4] = "oliveira";
        users[5] = "luccas";
        users[6] = "rafael";
        passwords[0] = "aaa";
        passwords[1] = "bbbb";
        passwords[2] = "ccccc";
        members[0] = "Joao";
        members[1] = "Maria";
        members[2] = "Jose";

        if (facadeBusiness.userDAO instanceof UserDAOFile)
        {
            UserDAOFile userDAOFile = (UserDAOFile) facadeBusiness.userDAO;
            try
            {
                Files.deleteIfExists(Paths.get(userDAOFile.getRegisterFile()));
            }
            catch (IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
    }


    @Test
    void addUserUserName1()
    {
        facadeBusiness.addUser(users[0], passwords[1]);
        assertEquals(facadeBusiness.getCurrentUser(), null);
    }

    @Test
    void addUserOK()
    {
        User user = new User(users[1], passwords[1]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        assertTrue(user.equals(facadeBusiness.getCurrentUser()));
    }

    @Test
    void loginUsername1()
    {
        facadeBusiness.addUser(users[0], passwords[1]);
        facadeBusiness.logout();
        facadeBusiness.login(users[0], passwords[1]);
        assertEquals(facadeBusiness.getCurrentUser(), null);
    }

    @Test
    void loginOK()
    {
        User user = new User(users[2], passwords[1]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        facadeBusiness.logout();
        facadeBusiness.login(user.getName(), user.getPassword());
        facadeBusiness.report(new PDFReport());
        facadeBusiness.getVersionLogger().setVersion("1.2.3");
        assertTrue(facadeBusiness.getCurrentUser().equals(user));
    }


    @Test
    void addMemberInTree()
    {
        User user = new User(users[3], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        boolean found = false;
        for (Member member1 : facadeBusiness.getCurrentUser().getGenealogicalTree().getMemberList())
        {
            if (member1.equals(member))
            {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void updateMemberInTree()
    {
        User user = new User(users[4], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        Member member1 = new Member(Member.male, "Mario");
        facadeBusiness.updateMemberInTree("M", member.name, "M", member1.name);
        boolean found = false;
        for (Member m : facadeBusiness.getCurrentUser().getGenealogicalTree().getMemberList())
        {
            if (m.equals(member1))
            {
                found = true;
                break;
            }
        }
        assertTrue(found);
    }

    @Test
    void searchMemberInTree()
    {
        User user = new User(users[5], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        Member tempMember = facadeBusiness.searchMemberInTree("M", member.name);
        assertTrue(member.equals(tempMember));
    }

    @Test
    void logout()
    {
        facadeBusiness.addUser(users[0], passwords[1]);
        facadeBusiness.logout();
        facadeBusiness.login(users[0], passwords[1]);
        assertEquals(facadeBusiness.getCurrentUser(), null);
    }
}