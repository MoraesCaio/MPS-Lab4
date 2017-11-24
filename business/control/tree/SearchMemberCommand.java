package business.control.tree;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

/**
 * Created by caiomoraes on 21/11/17.
 */
public class SearchMemberCommand extends TreeCommand
{
    @Override
    public Member execute()
    {
        return genealogicalTree.searchMember(memberInfo);
    }


    /*CONSTRUCTORS*/
    public SearchMemberCommand(GenealogicalTree genealogicalTree, Member memberInfo)
    {
        super(genealogicalTree, memberInfo);
    }

    public SearchMemberCommand(GenealogicalTree genealogicalTree)
    {
        super(genealogicalTree);
    }

    public SearchMemberCommand()
    {
        super();
    }
}
