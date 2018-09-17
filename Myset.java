public class Myset
{
    public mylinkedlist LL;

    Myset()
    {
        LL = new mylinkedlist();
    }

    public Boolean IsEmpty()
    {
        return this.LL.getSize()==0;
    }
    public Boolean IsMember(Object o)
    {
        return this.LL.contains(o);
    }
    public void Insert(Object o)
    {
        this.LL.add(o);
    }
    public void Delete(Object o)
    {
        try
        {
            boolean flag=this.LL.remove(o);
            if(!flag)
            {
                throw new Exception("Does not contain element.");
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
    public mylinkedlist ListCopy()
    {
        mylinkedlist out = new mylinkedlist();
        for(int i=0;i<this.LL.getSize();i++)
        {
            out.add(this.LL.get(i));
        }
        return out;
    }
    public Myset Union(Myset a)
    {
        Myset output=new Myset();
        mylinkedlist List_of_a=new mylinkedlist();
        List_of_a=a.ListCopy();
        for(int i=0;i<this.LL.getSize();i++)
        {
            output.Insert(LL.get(i));
        }
        for(int i=0;i<List_of_a.getSize();i++)
        {
            if(!this.IsMember(List_of_a.get(i)))
            {
                output.Insert(List_of_a.get(i));
            }
        }
        return output;
    }
    public Myset Intersection(Myset a)
    {
        Myset output=new Myset();
        mylinkedlist List_of_a=new mylinkedlist();
        List_of_a=a.ListCopy();
        for(int i=0;i<List_of_a.getSize();i++)
        {
            if(this.IsMember(List_of_a.get(i)))
            {
                output.Insert(List_of_a.get(i));
            }
        }
        return output;
    }
}
