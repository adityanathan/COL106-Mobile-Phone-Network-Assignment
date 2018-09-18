class Exchange
{
    private int data;
    private mylinkedlist children=new mylinkedlist();
    private Exchange parent;
    public MobilePhoneSet resident_set=new MobilePhoneSet();
    public MobilePhoneSet mobile_set=new MobilePhoneSet();
    public static mylinkedlist all_exchanges = new mylinkedlist();

    Exchange(int number)
    {
        this.data=number;
        this.parent=null;
        this.mobile_set.setBaseStation(this);
        this.resident_set.setBaseStation(this);
        all_exchanges.add(this);
    }

    public int getID()
    {
        return this.data;
    }

    public Exchange parent()
    {
        return this.parent;
    }

    public void setParent(Exchange p)
    {
        this.parent = p;
    }

    public void addChild(Exchange c)
    {
        c.setParent(this);
        this.children.add(c);
        this.resident_set=this.residentSet();
    }

    public void removeChild(Exchange c)
    {
        c.setParent(null);
        this.children.remove(c);
        this.resident_set=this.residentSet();
    }

    public int numChildren()
    {
        return this.children.getSize();
    }

    public Exchange child(int i)
    {
        return (Exchange) this.children.get(i);
    }

    public Boolean isRoot()
    {
        return this.parent==null;
    }

    public MobilePhoneSet residentSet()
    {
        MobilePhoneSet temp = new MobilePhoneSet();
        if(this.children.getSize()>0)
        {
            for(int i=0;i<this.children.getSize();i++)
            {
                temp.union(((Exchange) this.children.get(i)).residentSet());
            }
        }
        else
        {
            temp.union(this.resident_set);
        }
        return temp;
    }

    public void addMobilePhone(MobilePhone a)
    {
        this.mobile_set.InsertMobilePhone(a);
        this.resident_set.InsertMobilePhone(a);
    }

    public void removeMobilePhone(MobilePhone a)
    {
        this.resident_set.DeleteMobilePhone(a);
        this.mobile_set.DeleteMobilePhone(a);
    }

    public MobilePhoneSet getMobileSet()
    {
        return this.mobile_set;
    }

    public RoutingMapTree subtree(int i)
    {
        RoutingMapTree sub = new RoutingMapTree();
        sub.setRoot(this.child(i));
        return sub;
    }

    public static Exchange findExchange(int b)
    {
        Exchange p=null;
        int j;
        for(j=0;j<Exchange.all_exchanges.getSize();j++)
        {
            if(((Exchange) Exchange.all_exchanges.get(j)).getID()==b)
            {
                p = (Exchange) (Exchange.all_exchanges.get(j));
                break;
            }
        }
        return p;
    }

    //exchangeUnion gets me a collection of all of this exchange's children in mylinkedlist type.
    public mylinkedlist exchangeUnion()
    {
        mylinkedlist result=new mylinkedlist();

        if(this.numChildren()>0)
        {
            for(int i=0; i<this.numChildren(); i++)
            {
                result=result.getUnion(this.child(i).exchangeUnion());
                result.add(this.child(i));
            }
        }
        return result;
    }

    //Searches all_exchanges for a.
    public static boolean containsExchange(Exchange a)
    {
        int i;
        for(i=0; i<all_exchanges.getSize(); i++)
        {
            if(all_exchanges.get(i)==a)
            {
                return true;
            }
        }
        return false;
    }

    //Calculates Depth of the exchange from the root.
    public int depth()
    {
        if(this.getID()==0)
        {
            return 0;
        }
        else
        {
            return (1 + this.parent().depth());
        }
    }
}
