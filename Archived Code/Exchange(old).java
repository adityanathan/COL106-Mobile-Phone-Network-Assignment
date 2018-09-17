import java.util.*;

public class Exchange
{
    private Exchange parent;
    private mylinkedlist children;
    private int exchange_id;
    private MobilePhoneSet leaves;

    Exchange(int number)
    {
        this.parent=null;
        this.children = new mylinkedlist();
        this.exchange_id=number;
        this.leaves=null;
    }

    public int getId()
    {
        return this.exchange_id;
    }

    public void setId(int id)
    {
        this.exchange_id=id;
    }

    public Exchange parent()
    {
        return this.parent;
    }

    public void setParent(Exchange p)
    {
        this.parent=p;
    }

    public int numChildren()
    {
        return this.children.size();
    }

    public void addChild(Exchange c)
    {
        this.children.
    }

    public void removeChild(Exchange c)
    {
        this.children.remove(c);
    }

    public mylinkedlist getChildren()
    {
        return this.children;
    }

    public Exchange child(int i)
    {
        return this.children.get(i);
    }

    public boolean isRoot()
    {
        return (this.parent==null);
    }

    public boolean isLeaf()
    {
        return (this.children.size()==0);
    }

    public void addMobilePhoneSet(MobilePhoneSet o)
    {
        if(this.isLeaf())
        {
            this.leaves=o;
        }
    }

    public void removeMobilePhoneSet()
    {
        this.leaves=null;
    }

    /*public RoutingMap subtree(int i)
    {

    }*/

    public MobilePhoneSet residentSet()
    {
        if(this.isLeaf())
        {
            return this.leaves;
        }
        return null;
    }
}

// You haven't ensured that a node cannot have both exchange children and      MobilePhoneSet.
