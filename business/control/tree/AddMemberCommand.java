package business.control.tree;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;


public class AddMemberCommand extends TreeCommand
{
    @Override
    public Member execute()
    {
        Member foundMember = genealogicalTree.searchMember(memberInfo);
        if (foundMember != null)
        {
            return null;
        }

        Member addedMember = memberInfo.copy();
        genealogicalTree.getMemberList().add(addedMember);
        return addedMember;
    }


    /*CONSTRUCTORS*/
    public AddMemberCommand(GenealogicalTree genealogicalTree, Member memberInfo)
    {
        super(genealogicalTree, memberInfo);
    }

    public AddMemberCommand(GenealogicalTree genealogicalTree)
    {
        super(genealogicalTree);
    }

    public AddMemberCommand()
    {
        super();
    }
}
