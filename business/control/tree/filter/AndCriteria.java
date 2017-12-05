package business.control.tree.filter;

import business.model.tree.Member;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public class AndCriteria implements Filter
{
    private Filter firstCriteria;
    private Filter secondCriteria;

    public AndCriteria(Filter firstCriteria, Filter secondCriteria)
    {
        this.firstCriteria = firstCriteria;
        this.secondCriteria = secondCriteria;
    }

    @Override
    public List<Member> meetCriteria(List<Member> memberList)
    {
        List<Member> meetFirstCriteriaMembers = firstCriteria.meetCriteria(memberList);
        return secondCriteria.meetCriteria(meetFirstCriteriaMembers);
    }
}
