package business.control.tree.filter;

import business.model.tree.Member;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class ShortNameCriteria implements Filter
{
    @Override
    public List<Member> meetCriteria(List<Member> memberList)
    {
        List<Member> shortNameMembers = new ArrayList<>();
        for (Member member : memberList)
        {
            int length = member.name.length();
            if (length != 0 && length < 7)
            {
                shortNameMembers.add(member);
            }
        }
        return shortNameMembers;
    }
}
