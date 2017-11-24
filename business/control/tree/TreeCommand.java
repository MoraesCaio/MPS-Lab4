package business.control.tree;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

/**
 * Created by caiomoraes on 21/11/17.
 */
public abstract class TreeCommand
{
    GenealogicalTree genealogicalTree;
    public Member memberInfo;

    public abstract Member execute();

    public GenealogicalTree getGenealogicalTree()
    {
        return genealogicalTree;
    }

    public TreeCommand(GenealogicalTree genealogicalTree, Member memberInfo)
    {
        this.genealogicalTree = genealogicalTree;
        this.memberInfo = memberInfo;
    }

    public TreeCommand(GenealogicalTree genealogicalTree)
    {
        this(genealogicalTree, new Member());
    }

    public TreeCommand()
    {
        this(new GenealogicalTree());
    }
}
