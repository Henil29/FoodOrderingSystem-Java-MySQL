package test;

public class user {
    
    class node{

        String data;
        node next;

        public node(String data)
        {
            this.data = data;
            this.next = null;
        }
    }
    node head=null;

    public void add(String data)
    {
        node n=new node(data);
        if(head==null)
        {
            head=n;
        }
        else
        {
            node temp=head;

            while (temp.next!=null) {
                temp=temp.next;
            }
            temp.next=n;
        }
    }
    public void change(String old,String data)
    {

        if (head == null) {
            throw new RuntimeException("List is empty");
        }

        node temp=head;

        while(temp.data!=old)
        {
            temp=temp.next;
        }
        temp.data=data;
    }
    public boolean check(String data)
    {

        if (head == null || data == null) {
            return false;
        }
        
        node temp=head;

        while(temp!=null)
        {
            if(temp.data.equals(data))
            {
                return true;
            }
            temp=temp.next;
        }
        
        return false;
    }

    public void delete(String data)
    {
        node temp=head;

        if(head==null)
        {
            throw new RuntimeException("List is empty");
        }

        while(temp.next!=null)
        {
            if(temp.next.data.equals(data))
            {
                temp.next=temp.next.next;
                return;
            }
            temp=temp.next;
        }
    }

    public void display() {
        node temp = head;
        int i=1;
        while (temp != null) {
            System.out.println(i+"."+temp.data);
            temp = temp.next;
            i++;
        }
        System.out.println();
    }
}
