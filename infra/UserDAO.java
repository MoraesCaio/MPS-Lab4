package infra;

import business.model.User;
import business.model.tree.Member;

public interface UserDAO
{
    void saveUser(User user)  throws Exception;
    User getUser(String login)  throws Exception;
    void addMember(User user, Member member) throws Exception;
    void updateMember(User user, String gender, String name, String newGender, String newName) throws Exception;
}
