package business.control.tree.filter;

import business.model.tree.Member;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class FemaleCriteria implements Filter
{
    @Override
    public List<Member> meetCriteria(List<Member> memberList)
    {
        List<Member> femaleMembers = new ArrayList<>();
        for (Member member : memberList)
        {
            if (member.gender.equals(Member.female))
            {
                femaleMembers.add(member);
            }
        }
        return femaleMembers;
    }
}
