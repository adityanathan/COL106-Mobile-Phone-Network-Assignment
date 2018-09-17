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
        return "Invalid Input";
    }
}
