package business.control.tree;

import business.model.tree.GenealogicalTree;
import business.model.tree.Member;

/**
 * Created by caiomoraes on 21/11/17.
 */
public class TreeController
{
    /*PROPERTIES*/
    private TreeCommand treeCommand;


    /*CONSTRUCTORS*/
    public TreeController(TreeCommand treeCommand)
    {
        this.treeCommand = treeCommand;
    }

    public TreeController()
    {
        this(null);
    }


    /*METHODS*/
    public TreeController setTreeCommand(TreeCommand treeCommand)
    {
        this.treeCommand = treeCommand;
        return this;
    }

    public Member run()
    {
        treeCommand.getGenealogicalTree().getTreeCareTaker().saveState();
        return treeCommand.execute();
    }

    public void undoLastCommand(GenealogicalTree genealogicalTree)
    {
        genealogicalTree.getTreeCareTaker().restoreState();
    }
}
