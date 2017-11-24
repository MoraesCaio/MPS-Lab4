package business.model.tree;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GenealogicalTree implements Serializable
{
    private List<Member> memberList;
    private TreeCareTaker treeCareTaker;

    /*CONSTRUCTORS*/
    public GenealogicalTree(List<Member> memberList)
    {
        this.memberList = memberList;
        this.treeCareTaker = new TreeCareTaker(this);
    }

    public GenealogicalTree()
    {
        this(new ArrayList<Member>());
    }


    /*METHODS*/

    /**
     * Returns the reference of a member that matches memberInfo parameter object (NULLABLE!).
     */
    public Member searchMember(Member memberInfo)
    {
        for (Member member : memberList)
        {
            if (member.equals(memberInfo))
            {
                return member;
            }
        }
        return null;
    }

    public List<Member> getMemberList()
    {
        return memberList;
    }

    public void setMemberList(List<Member> memberList)
    {
        this.memberList = memberList;
    }

    public TreeCareTaker getTreeCareTaker()
    {
        return treeCareTaker;
    }

    public GenealogicalTree copy()
    {
        GenealogicalTree copyTree = new GenealogicalTree();
        for (Member member : memberList)
        {
            copyTree.memberList.add(member.copy());
        }
        return copyTree;
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder("TREE:\n");
        for (Member member : memberList)
        {
            sb.append("\t" + member);
            if (memberList.indexOf(member) == memberList.size() - 1)
            {
                sb.append(".\n\n");
                break;
            }
            sb.append(",\n");
        }
        return sb.toString();
    }
}
