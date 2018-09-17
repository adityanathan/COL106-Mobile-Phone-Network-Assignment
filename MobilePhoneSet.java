class MobilePhoneSet
{
    private Myset phoneSet;
    private Exchange base_station;

    MobilePhoneSet()
    {
        this.phoneSet=new Myset();
    }

    public void InsertMobilePhone(MobilePhone b)
    {
        this.phoneSet.Insert(b);
        b.setMobilePhoneSet(this);
    }

    public void DeleteMobilePhone(MobilePhone b)
    {
        this.phoneSet.Delete(b);
        b.setMobilePhoneSet(null);
    }

    public Myset getPhoneSet()
    {
        return this.phoneSet;
    }

    public boolean containsMobilePhone(MobilePhone a)
    {
        return this.phoneSet.IsMember(a);
    }

    public void union(MobilePhoneSet a)
    {
        this.phoneSet= this.phoneSet.Union(a.getPhoneSet());
    }

    public void setBaseStation(Exchange b)
    {
        this.base_station=b;
    }

    public Exchange getBaseStation()
    {
        return this.base_station;
    }
}
