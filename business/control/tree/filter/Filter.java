package business.control.tree.filter;

import business.model.tree.Member;
import java.util.List;

/**
 * Created by caiomoraes on 26/11/17.
 */
public interface Filter
{
    List<Member> meetCriteria(List<Member> memberList);
}
