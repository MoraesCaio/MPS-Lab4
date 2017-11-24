package business.control.tree;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

/**
 * Created by caiomoraes on 21/11/17.
 */
public class UpdateMemberCommand extends TreeCommand
{
    public Member memberNewInfo;

    @Override
    public Member execute()
    {
        Member foundMember = genealogicalTree.searchMember(memberInfo);
        if (foundMember == null)
        {
            System.out.println("Member not found (" + memberInfo + ")!");
            return null;
        }

        foundMember.name = memberNewInfo.name;
        foundMember.gender = memberNewInfo.gender;
        return foundMember;
    }


    /*CONSTRUCTORS*/
    public UpdateMemberCommand(GenealogicalTree genealogicalTree, Member memberInfo, Member memberNewInfo)
    {
        super(genealogicalTree, memberInfo);
        this.memberNewInfo = memberNewInfo;
    }

    public UpdateMemberCommand(GenealogicalTree genealogicalTree, Member memberInfo)
    {
        this(genealogicalTree, memberInfo, new Member());
    }

    public UpdateMemberCommand(GenealogicalTree genealogicalTree)
    {
        this(genealogicalTree, new Member());
    }

    public UpdateMemberCommand()
    {
        this(new GenealogicalTree());
    }
}
