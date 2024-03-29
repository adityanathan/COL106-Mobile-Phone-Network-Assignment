class mylinkedlist
{
    protected node head;
    protected int size;

    mylinkedlist()
    {
        this.head=null;
        this.size=0;
    }

    public int getSize()
    {
        return this.size;
    }

//mylinkedlist.contains - returns true if element present else false.
    public boolean contains(Object o)
    {
        node marker=this.head;
        while(marker!=null)
        {
            if(marker.getData()==o)
            {
                return true;
            }
            marker=marker.getNext();
        }
        return false;
    }

    public void add(Object o)
    {
        node newest = new node(o);
        node marker=this.head;
        if(marker==null)
        {
            this.head=newest;
        }
        else
        {
            while(marker.getNext()!=null)
            {
                marker=marker.getNext();
            }
            marker.setNext(newest);
        }
        this.size++;
    }

//mylinkedlist.remove - return true if element present and removed else return false.
    public boolean remove(Object o)
    {
        if(!this.contains(o))
        {
            return false;
        }
        else
        {
            node element_to_be_deleted=null;
            node marker=this.head;
            while(marker!=null)
            {
                if(marker.getData()==o)
                {
                    element_to_be_deleted = marker;
                    break;
                }
                marker=marker.getNext();
            }
            marker=this.head;
            while(marker!=null)
            {
                if(this.head==element_to_be_deleted)
                {
                    this.head=this.head.getNext();
                    element_to_be_deleted=null;
                    break;
                }
                else if(marker.getNext()==element_to_be_deleted)
                {
                    marker.setNext(element_to_be_deleted.getNext());
                    element_to_be_deleted=null;
                    break;
                }
            }
            this.size--;
            return true;
        }
    }

//mylinkedlist.get - return data element if present, else return null.
    public Object get(int i)
    {
        node marker = this.head;
        if(i==0)
        {
            return marker.getData();
        }
        else if(i<this.size)
        {
            while(i>0)
            {
                marker=marker.getNext();
                i--;
            }
            return marker.getData();
        }

        return null;
    }

    public mylinkedlist getUnion(mylinkedlist b)
    {
        int i;
        mylinkedlist result = new mylinkedlist();
        for(i=0; i<this.getSize(); i++)
        {
            result.add(this.get(i));
        }
        for(i=0; i<b.getSize(); i++)
        {
            if(!result.contains(b.get(i)))
            {
                result.add(b.get(i));
            }
        }
        return result;
    }
}
