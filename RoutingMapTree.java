import java.util.regex.*;
import java.lang.*;

class RoutingMapTree
{
    private Exchange root;
    private int counter;

    RoutingMapTree()
    {
        this.root = new Exchange(0);
        this.counter=1;
    }

    public Exchange getRoot()
    {
        return this.root;
    }

    public void setRoot(Exchange r)
    {
        this.root=r;
    }

    public void switchOn(MobilePhone a, Exchange b)
    {
        if(!a.status())
        {
            a.switchOn();
            if(b.numChildren()==0)
            {
                b.addMobilePhone(a);
            }
        }
    }

    public void switchOff(MobilePhone a)
    {

        if(a.status())
        {
            a.switchOff();
            a.location().removeMobilePhone(a);
        }
    }

    public String performAction(String actionMessage)
    {
       if(Pattern.matches("addExchange (\\d)+ (\\d)+",actionMessage))
        //"Exchange (\\d)+ (\\d)+ is a regular expression. I'm using java.util.regex library class."
        {
            int j;
            for(j=12; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(12,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));
            Exchange p=Exchange.findExchange(a);
            if(p==null)
            {
                try
                {
                    throw new Exception("Error- No exchange with identifier "+Integer.toString(a));
                }
                catch(Exception ex)
                {
                    return ex.getMessage();
                }
            }

            Exchange newest = new Exchange(b);
            Exchange.all_exchanges.add(newest);
            p.addChild(newest);
            return "";

        }

        else if(Pattern.matches("switchOnMobile (\\d)+ (\\d)+",actionMessage))
        {
            int j;
            for(j=15; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(15,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));
            Exchange p=Exchange.findExchange(b);
            if(p==null)
            {
                try
                {
                    throw new Exception("Error- No exchange with identifier "+Integer.toString(b));
                }
                catch(Exception ex)
                {
                    return ex.getMessage();
                }
            }
            MobilePhone m=MobilePhone.findPhone(a);
            if(!(m==null))
            {
                if(m.status()==true)
                {
                    try
                    {
                        throw new Exception("Error- MobilePhone "+Integer.toString(a)+" is already on");
                    }
                    catch(Exception ex)
                    {
                        return ex.getMessage();
                    }
                }
                else
                {
                    switchOn(m, p);
                }
            }
            else
            {
                m = new MobilePhone(a);
                MobilePhone.all_phones.add(m);
                switchOn(m, p);
            }
            return "";
        }

        else if(Pattern.matches("switchOffMobile (\\d)+",actionMessage))
        {
            int j;
            for(j=16; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(16,j));
            MobilePhone m=MobilePhone.findPhone(a);
            if(!(m==null))
            {
                switchOff(m);
                return "";


            }
            else
            {
                try
                {
                    throw new Exception("Error- No mobile phone with identifier"+Integer.toString(a));
                }
                catch(Exception ex)
                {
                    return ex.getMessage();
                }
            }
        }

        else if(Pattern.matches("queryNthChild (\\d)+ (\\d)+",actionMessage))
        {
            int j;
            for(j=14; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(14,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));

            Exchange p = Exchange.findExchange(a);
            if(p==null)
            {
                try
                {
                    throw new Exception("Error- No exchange with identifier"+Integer.toString(a));
                }
                catch(Exception ex)
                {
                    return ex.getMessage();
                }
            }
            Exchange c;
            try
            {
                if(b<p.numChildren())
                {
                    c = p.child(b);

                }
                else
                    throw new Exception("Error - No "+Integer.toString(b)+" child of Exchange "+Integer.toString(a));
            }
            catch(Exception ex)
            {
                return ex.getMessage();
            }
            return (actionMessage+": "+ Integer.toString(c.getID()));
        }

        else if(Pattern.matches("queryMobilePhoneSet (\\d)+",actionMessage))
        {
            int j;
            for(j=20; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(20,j));

            Exchange p = Exchange.findExchange(a);
            if(p==null)
            {
                try
                {
                    throw new Exception("Error- No exchange with identifier"+Integer.toString(a));
                }
                catch(Exception ex)
                {
                    return ex.getMessage();
                }
            }

            mylinkedlist h = p.residentSet().getPhoneSet().ListCopy();
            String msg;
            try
            {
                msg = Integer.toString(((MobilePhone) h.get(0)).number());
            }
            catch(NullPointerException ex)
            {
                return "There are no mobile phones.";
            }

                for(j=1;j<h.getSize();j++)
                {
                    msg=msg+", "+Integer.toString(((MobilePhone) h.get(j)).number());
                }
                return actionMessage+": "+msg;


        }

        else if(Pattern.matches("findPhone (\\d)+",actionMessage))
        {
            int j;
            for(j=10; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(10,j));

            try
            {
                if(MobilePhone.findPhone(a)==null)
                {
                    throw new Exception("Error - No mobile phone with identifier "+Integer.toString(a)+" found in the network");
                }
                else
                {
                    return actionMessage+": "+Integer.toString(findPhone(MobilePhone.findPhone(a)).getID());
                }

            }
            catch(Exception ex)
            {
                return actionMessage+": "+ex.getMessage();
            }
        }

        else if(Pattern.matches("lowestRouter (\\d)+ (\\d)+",actionMessage))
        {
            int j;
            for(j=13; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(13,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));

            try
            {
                return actionMessage+": "+Integer.toString(lowestRouter(Exchange.findExchange(a),Exchange.findExchange(b)).getID());
            }
            catch(Exception ex)
            {
                return actionMessage+": "+ex.getMessage();
            }
        }

        else if(Pattern.matches("findCallPath (\\d)+ (\\d)+",actionMessage))
        {
            int j;
            for(j=13; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(13,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));

            try
            {
                mylinkedlist result = routeCall(MobilePhone.findPhone(a),MobilePhone.findPhone(b));

                String msg=Integer.toString(((Exchange) result.get(0)).getID());
                for(j=1;j<result.getSize(); j++)
                {
                    msg=msg+", "+Integer.toString(((Exchange) result.get(j)).getID());
                }

                return actionMessage+": "+msg;
            }
            catch(Exception ex)
            {
                return actionMessage+": "+ex.getMessage();
            }
        }

        else if(Pattern.matches("movePhone (\\d)+ (\\d)+",actionMessage))
        {
            int j;
            for(j=10; j<actionMessage.length(); j++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int a = Integer.parseInt(actionMessage.substring(10,j));
            int k=j++;

            for(; k<actionMessage.length(); k++)
            {
                if(!Character.isDigit(actionMessage.charAt(j)))
                {
                    break;
                }
            }

            int b= Integer.parseInt(actionMessage.substring(j,k));

            try
            {
                movePhone(MobilePhone.findPhone(a),Exchange.findExchange(b));
                return "";
            }
            catch(Exception ex)
            {
                return actionMessage+": "+ex.getMessage();
            }
        }

        return "Invalid Input";
    }

    public Exchange findPhone(MobilePhone m) throws Exception
    {
        /*if(MobilePhone.isPhoneThere(m)==false)
        {
            throw new Exception("Error - No mobile phone with identifier "+m.number()+" found in the network");
        }*/
        if(m.status()==false)
        {
            throw new Exception("Error - Mobile phone with identifier "+m.number()+" is currently switched off");
        }
        return m.location();
    }

    public Exchange lowestRouter(Exchange a, Exchange b) throws Exception
    {
        if(!Exchange.containsExchange(a) || !Exchange.containsExchange(b))
        {
            throw new Exception("One or both of the exchanges do not exist.");
        }

        else if(a.depth()!=b.depth())
        {
            throw new Exception("The exchanges are not of the same level");
        }

        else if(a.numChildren()>0 || b.numChildren()>0)
        {
            throw new Exception("The exchanges are not of level 0.");
        }

        else if(a==b)
        {
            return a;
        }
        else
        {
            Exchange p = a.parent();
            while(true)
            {
                if(p.exchangeUnion().contains(b))
                {
                    return p;
                }
                else if(p.getID()==0)
                {
                    return null;
                }
                else
                {
                    p=p.parent();
                }
            }
        }
    }

    public ExchangeList routeCall(MobilePhone a, MobilePhone b) throws Exception
    {
        Exchange x = findPhone(a);
        Exchange y = findPhone(b);
        ExchangeList result = new ExchangeList();
        result.add(x);
        Exchange meeting_point = lowestRouter(x, y);
        while(x!=meeting_point)
        {
            x=x.parent();
            result.add(x);
        }
        ExchangeList temp = new ExchangeList();
        while(y!=meeting_point)
        {
            temp.add(y);
            y=y.parent();
        }

        if(temp.getSize()>0)
        {
            for(int i = temp.getSize()-1 ;i>=0; i--)
            {
                result.add(temp.get(i));
            }
            temp=null;
        }

        return result;
    }

    public void movePhone(MobilePhone a, Exchange b) throws Exception
    {
        if(!a.status())
        {
            throw new Exception("Error - Mobile phone with identifier "+a.number()+" is currently switched off");
        }
        else if(b.numChildren()>0)
        {
            throw new Exception("Exchange "+b.getID()+" is not a base station.");
        }
        else if(MobilePhone.isPhoneThere(a)==false)
        {
            throw new Exception("Error - No mobile phone with identifier "+a.number()+" found in the network");
        }
        switchOff(a);
        switchOn(a,b);
    }


}
