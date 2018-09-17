


public class checker
{
    public static void main(String args[])
    {
        Myset obj = new Myset();
        Myset obj2 = new Myset();
        Myset obj3 = new Myset();
        Myset obj4 = new Myset();
        System.out.println(obj.IsEmpty());
        obj.Insert(1);
        obj.Insert(2);
        obj.Insert(3);
        obj.Insert(4);
        obj2.Insert(1);
        obj2.Insert(2);
        obj2.Insert(3);
        obj2.Insert(4);
        System.out.println();
        for(int i=1;i<5;i++)
        {
            System.out.println(obj2.IsMember(i));
        }
        obj.Delete(2);
        System.out.println();
        for(int i=1;i<5;i++)
        {
            System.out.println(obj.IsMember(i));
        }
        obj3=obj.Union(obj2);
        obj4=obj.Intersection(obj2);
        System.out.println();
        for(int i=1;i<5;i++)
        {
            System.out.println(obj3.IsMember(i));
        }
        System.out.println();
        for(int i=1;i<5;i++)
        {
            System.out.println(obj4.IsMember(i));
        }
    }
}

/*
class Myset
{
    public LinkedList<Object> LL =new LinkedList<Object>();
    public Boolean IsEmpty()
    {
        return this.LL.size()==0;
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
        this.LL.remove(o);
    }
    public LinkedList<Object> ListCopy()
    {
        LinkedList<Object> out = new LinkedList<Object>();
        for(int i=0;i<this.LL.size();i++)
        {
            out.add(this.LL.get(i));
        }
        return out;
    }
    public Myset Union(Myset a)
    {
        Myset output=new Myset();
        LinkedList<Object> List_of_a=new LinkedList<Object>();
        List_of_a=a.ListCopy();
        for(int i=0;i<this.LL.size();i++)
        {
            output.Insert(LL.get(i));
        }
        for(int i=0;i<List_of_a.size();i++)
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
        LinkedList<Object> List_of_a=new LinkedList<Object>();
        List_of_a=a.ListCopy();
        for(int i=0;i<this.LL.size();i++)
        {
            if(this.IsMember(List_of_a.get(i)))
            {
                output.Insert(List_of_a.get(i));
            }
        }
        return output;
    }
}
*/
/*
class LinkedList<E>
{
    private static class node<E>
    {
        private node<E> prev;
        private node<E> next;
        private E ele;
        public node(node<E> p, E e, node<E> n)
        {
            prev=p;
            ele=e;
            next=n;
        }
        public E getElement(){return ele;}
        public node<E> getPrev(){return prev;}
        public node<E> getNext(){return next;}
        public void setPrev(node<E> p){prev=p;}
        public void setNext(node<E> n){next=n;}
    }

    private node<E> header;
    private node<E> trailer;
    private int size=0;

    public LinkedList()
    {
        header = new node<>(null, null, null);
        trailer = new node<>(null, header, null);
        header.setNext(trailer);
    }

    private void insertBetween(node<E> predecessor, E e, node<E> successor)
    {
        node<E> newest=new node<>(predecessor,e,successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    public int size()
    {
        return this.size;
    }

    public boolean contains(E e)
    {
        node<E> marker = header.getNext();
        for(int i=1;i<=size;i++)
        {
            if(marker.getElement()==e)
            {return true;}
        }
        return false;
    }

    public void add(E e)
    {
        insertBetween(trailer.getPrev(),e,trailer);
    }

    public void remove(E e)
    {
        node<E> marker = header.getNext();
        if(header.getNext()==trailer)
        {
            throw new Exception("Set is empty. Cannot delete.");
        }
        for(i=1;i<=size;i++)
        {
            if(marker.getElement()==e)
            {
                marker.getPrev().setNext(marker.getNext());
                marker.getNext().setPrev(marker.getPrev());
                marker=null;
                return;
            }
        }
        throw new Exception("Cannot find element.");
    }

    public void get(int i)
    {
        int j=0;
        node<E> marker = header.getNext();
        while(j<i)
        {
            marker=marker.getNext();
            j++;
        }
        return marker.getElement();
    }

}




class Myset
{
    protected LinkedList<Object> LL =new LinkedList<Object>();
    public Boolean IsEmpty()
    {
        return this.LL.size()==0;
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
            this.LL.remove(o);
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
        }

    }
    public LinkedList<Object> ListCopy()
    {
        LinkedList<Object> out = new LinkedList<Object>();
        for(int i=0;i<this.LL.size();i++)
        {
            out.add(this.LL.get(i));
        }
        return out;
    }
    public Myset Union(Myset a)
    {
        Myset output=new Myset();
        LinkedList<Object> List_of_a=new LinkedList<Object>();
        List_of_a=a.ListCopy();
        for(int i=0;i<this.LL.size();i++)
        {
            output.Insert(LL.get(i));
        }
        for(int i=0;i<List_of_a.size();i++)
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
        LinkedList<Object> List_of_a=new LinkedList<Object>();
        List_of_a=a.ListCopy();
        for(int i=0;i<this.LL.size();i++)
        {
            if(this.IsMember(List_of_a.get(i)))
            {
                output.Insert(List_of_a.get(i));
            }
        }
        return output;
    }
}
*/
