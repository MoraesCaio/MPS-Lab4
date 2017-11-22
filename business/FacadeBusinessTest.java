package business;

import business.model.User;
import business.model.tree.GenealogicalTree;
import business.model.tree.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.management.MemoryManagerMXBean;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by caiomoraes on 22/11/17.
 */
class FacadeBusinessTest
{
    String[] users = new String[4];
    String[] passwords = new String[3];
    String[] members = new String[3];

    @BeforeEach
    void setUp()
    {
        users[0] = "aaabb";
        users[1] = "cccddd";
        users[2] = "eeefffg";
        users[3] = "demonho";
        passwords[0] = "aaa";
        passwords[1] = "bbbb";
        passwords[2] = "ccccc";
        members[0] = "Joao";
        members[1] = "Maria";
        members[2] = "Jose";
    }


    @Test
    void addUserUserName1()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        facadeBusiness.addUser(users[0], passwords[1]);
        assertEquals(facadeBusiness.currentUser, null);
    }

    @Test
    void addUserOK()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        User user = new User(users[1], passwords[1]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        assertTrue(user.equals(facadeBusiness.currentUser));
    }

    @Test
    void loginUsername1()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        facadeBusiness.login(users[0], passwords[1]);
        assertEquals(facadeBusiness.currentUser, null);
    }

    @Test
    void loginOK()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        User user = new User(users[1], passwords[1]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        facadeBusiness.login(user.getName(), user.getPassword());
        assertTrue(facadeBusiness.currentUser.equals(user));
    }


    @Test
    void addMemberInTree()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        User user = new User(users[2], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        boolean found = false;
        for (Member member1 : facadeBusiness.currentUser.getGenealogicalTree().getMemberList())
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
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        User user = new User(users[2], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        Member member1 = new Member(Member.male, "Mario");
        facadeBusiness.updateMemberInTree("M", member.name, "M", member1.name);
        boolean found = false;
        for (Member m : facadeBusiness.currentUser.getGenealogicalTree().getMemberList())
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
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        User user = new User(users[3], passwords[2]);
        facadeBusiness.addUser(user.getName(), user.getPassword());
        Member member = new Member(Member.male, members[0]);
        facadeBusiness.addMemberInTree("M", member.name);
        Member tempMember = facadeBusiness.searchMemberInTree("M", member.name);
        assertTrue(member.equals(tempMember));
    }

    @Test
    void logout()
    {
        FacadeBusiness facadeBusiness = new FacadeBusiness();
        facadeBusiness.login(users[0], passwords[1]);
        assertEquals(facadeBusiness.currentUser, null);
    }
}