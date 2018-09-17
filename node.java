class node
{
    protected Object data;
    protected node next;
    //protected int id;

    node(Object o)
    {
        this.data=o;
        this.next=null;
    }

    public Object getData()
    {
        return this.data;
    }

    public node getNext()
    {
        return this.next;
    }

    public void setNext(node newest)
    {
        this.next=newest;
    }

/*    public void getId()
    {
        return this.id;
    }

    public void setId(int i)
    {
        this.id=i;
    }
    */
}
