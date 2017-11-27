package business.control.tree.filter;

import business.model.tree.Member;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class OrCriteria implements Filter
{
    private Filter firstCriteria;
    private Filter secondCriteria;

    public OrCriteria(Filter firstCriteria, Filter secondCriteria)
    {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    public List<Member> meetCriteria(List<Member> memberList)
    {
        List<Member> meetFirstCriteriaMembers = firstCriteria.meetCriteria(memberList);
        List<Member> meetSecondCriteriaMembers = secondCriteria.meetCriteria(memberList);

        for (Member member : meetSecondCriteriaMembers)
        {
            if (!meetFirstCriteriaMembers.contains(member))
            {
                meetFirstCriteriaMembers.add(member);
            }
        }

        return meetFirstCriteriaMembers;
    }
}
