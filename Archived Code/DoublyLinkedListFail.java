class Myset2<E>
{
    private static node<E>
    {
        private node<E> prev;
        private node<E> next;
        private E ele;
        public node<E>(node<E> p, E e, node<E> n)
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

    public Myset()
    {
        header=new node<>(null,null,null);
        trailer=new node<>(header,null,null);
        header.setNext(trailer);
    }

    public int size(){return size;}

    public E first( )
    {
        if (isEmpty()) return null;
        return header.getNext().getElement();
    }

    public E last( )
    {
        if (isEmpty()) return null;
        return trailer.getPrev().getElement();
    }

    private void insertBetween(node<E> predecessor, E e, node<E> successor)
    {
        node<E> newest=new node<>(predecessor,e,successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    private E remove(node <E> node)
    {
        predecessor=node.getPrev();
        successor=node.getNext();
        predecessor.setNext(successor);
        successor.setNext(predecessor);
        size--;
        return node.getElement();
    }

    public Boolean isEmpty(){return size==0;}

    public Boolean isMember(E e)
    {
        node<E> marker = header.getNext();
        boolean flag=false;
        while(marker!=trailer)
        {
            if(marker.getElement()==e)
            {
                flag=true;
                break;
            }
        }
        return flag;
    }

    public void Insert(E e)
    {
        insertBetween(trailer.getPrev(),e,trailer);
    }

    public Boolean

    public void Delete(E e)
    {
        try
        {
            node<E> marker = header.getNext();
            while(marker!=trailer)
            {
                if(marker.getElement()==e)
                {break;}
            }
            if(marker==trailer)
            {
                throw new Exception("Element not found!");
            }
            else
            {
                remove(marker);
            }
        }
        catch(Exception err)
        {
            System.out.println(err.getMessage())
        }
    }

    public Myset<E> Union(Myset<E> a)
    {
        Myset<E> combined=new Myset<>();
        node<E> marker=header;
        for(int i=1;i<=size;i++)
        {
            combined.Insert(marker.getNext());
        }
        if(a.size()!=0)
        {
            marker=a.first();
            for(i=1;i<=a.size();i++)
            {
                //WARNING: Possible bug here. getElement and getNext are private methods but are being accessed outside their class.
                if(!combined.isMember(marker.getElement()))
                {
                    combined.Insert(marker);
                }
                marker=marker.getNext();
            }
        }

        return combined;

    }

    public Myset<E> Intersection


}
