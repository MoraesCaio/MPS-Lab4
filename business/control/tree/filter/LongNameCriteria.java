package business.control.tree.filter;

import business.model.tree.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class LongNameCriteria implements Filter
{
    @Override
    public List<Member> meetCriteria(List<Member> memberList)
    {
        List<Member> longNameMembers = new ArrayList<>();
        for (Member member : memberList)
        {
            if (member.name.length() >= 7)
            {
                longNameMembers.add(member);
            }
        }
        return longNameMembers;
    }
}
