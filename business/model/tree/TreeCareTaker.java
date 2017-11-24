package business.model.tree;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by caiomoraes on 22/11/17.
 */
public class TreeCareTaker implements Serializable
{
    private GenealogicalTree genealogicalTree;
    private GenealogicalTree previousState;


    public TreeCareTaker(GenealogicalTree genealogicalTree)
    {
        this.genealogicalTree = genealogicalTree;
    }

    public void saveState()
    {
        previousState = genealogicalTree.copy();
    }

    public void restoreState()
    {
        if (previousState == null)
        {
            genealogicalTree.setMemberList(new ArrayList<Member>());
        }
        else
        {
            genealogicalTree.setMemberList(previousState.getMemberList());
        }
    }
}
