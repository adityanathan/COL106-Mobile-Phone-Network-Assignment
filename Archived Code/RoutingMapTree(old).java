public class RoutingMapTree
{
    private Exchange root;

    RoutingMapTree()
    {
        root = new Exchange(0);
    }

    private boolean preorder(Exchange r, Exchange a)
    {
        boolean flag=false;
        if(r.getId()==a.getId())
        {
            return true;
        }
        else
        {
            for(int i=0;i<r.numChildren();i++)
            {
                flag=preorder(r.child(i),a);
                if(flag)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean containsNode(Exchange a)
    {
        return preorder(this.root, a);
    }

    public int depth(Exchange a)
    {
        if(a.isRoot())
        {
            return 0;
        }
        else
        {
            return (1+depth(a.parent()));
        }
    }
}
