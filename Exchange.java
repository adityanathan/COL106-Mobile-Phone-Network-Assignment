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
        this.children.add(c);
        this.resident_set=this.residentSet();
    }

    public void removeChild(Exchange c)
    {
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
}
