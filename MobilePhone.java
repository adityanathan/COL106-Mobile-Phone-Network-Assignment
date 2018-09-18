class MobilePhone
{
    protected int phoneNumber;
    protected boolean stat;
    private MobilePhoneSet set;
    public static mylinkedlist all_phones = new mylinkedlist();

    MobilePhone(int number)
    {
        this.phoneNumber=number;
        this.stat=false;
        this.set=null;
        all_phones.add(this);
    }
    public int number()
    {
        return this.phoneNumber;
    }
    public boolean status()
    {
        return this.stat;
    }
    public void switchOn()
    {
        this.stat=true;
    }
    public void switchOff()
    {
        this.stat=false;
    }

    public void setMobilePhoneSet(MobilePhoneSet m)
    {
        this.set=m;
    }

    public MobilePhoneSet getMobilePhoneSet()
    {
        return this.set;
    }

    public Exchange location()
    {
        return this.set.getBaseStation();
    }

    public static MobilePhone findPhone(int b)
    {
        MobilePhone m=null;
        int j;
        for(j=0; j<MobilePhone.all_phones.getSize(); j++)
        {
            if(((MobilePhone) MobilePhone.all_phones.get(j)).number()==b)
            {
                m=(MobilePhone) MobilePhone.all_phones.get(j);
                break;
            }
        }
        return m;
    }

    public static boolean isPhoneThere(MobilePhone m)
    {
        int j;
        for(j=0; j<all_phones.getSize(); j++)
        {
            if(all_phones.get(j)==m)
            {
                return true;
            }
        }
        return false;
    }
}
