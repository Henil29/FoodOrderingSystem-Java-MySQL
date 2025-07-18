import java.util.*;
import java.io.*;
import java.sql.*;
import test.*;
// import function.*;

public class zomato
{
    public static void main(String[] args)throws Exception {
        Scanner sc=new Scanner(System.in);
        String dbURL="jdbc:mysql://localhost:3306/zomato";
        String dbuser="root";
        String dbpass="";
        
        Connection con=DriverManager.getConnection(dbURL, dbuser, dbpass);

        //restaurant linklist
        Restaurant t1=new Restaurant();
        String sqlres="select *from restaurant";
        Statement stres=con.createStatement();
        ResultSet rsres=stres.executeQuery(sqlres);
        while(rsres.next())
        {
            String res_name=rsres.getString("res_name");
            t1.add(res_name);
        }

        //user linklist

        user u1=new user();
        String sqluser="select*from users";
        Statement stuser=con.createStatement();
        ResultSet rsuser=stuser.executeQuery(sqluser);

        while(rsuser.next())
        {
            String user_name=rsuser.getString("user_name");
            u1.add(user_name);
        }

        order o1=new order();
        dish d1=new dish();
        if(con!=null)
        {
            boolean b=true;
            while(b)
            {
                System.out.println();
                System.out.println("----------------------------");
                System.out.println("1. user Management");
                System.out.println("2. Restaurant Management");
                System.out.println("3. Menu Management");
                System.out.println("4. Order Management ");
                System.out.println("5. Exit");
                System.out.println("----------------------------");
                System.out.println();
                System.out.println("Enter your choice: ");
                int choice=sc.nextInt();
                switch(choice)
                {
                    case 1:
                    {
                        boolean b11=true;
                        while(b11)
                        {   
                            System.out.println("----------------------------");
                            System.out.println("1 for  Add user");
                            System.out.println("2 for  Delete user");
                            System.out.println("3 for  Update user");
                            System.out.println("4 for  See details");
                            System.out.println("5 for exit");
                            System.out.println("----------------------------");

                            System.out.println();
                            System.out.println("Enter your choice: ");
                            int choice1=sc.nextInt();
                            switch(choice1)
                            {
                                case 1:
                                {
                                    System.out.println("Enter username: ");
                                    String username=sc.next();

                                    if(u1.check(username))
                                    {
                                        System.out.println("Please enter diffrent user name");
                                    }
                                    else
                                    {

                                        u1.add(username);

                                        File f=new File(username+".txt");
                                        f.createNewFile();

                                        System.out.println("Enter password: ");
                                        String password=sc.next();

                                        System.out.println("Enter email: ");
                                        String email=sc.next();
 
                                        String sql="{call adduser(?,?,?)}";
                                        // Statement st=con.createStatement();
                                        CallableStatement cs=con.prepareCall(sql);
                                        cs.setString(1, username);
                                        cs.setString(2, password);
                                        cs.setString(3, email);
                                        System.out.println("user added successfully");
                                        System.out.println();
                                        cs.execute();
                                    }
                                    break;
                                }

                                case 2:
                                {
                                    System.out.println("Enter username: ");
                                    String username=sc.next();
                                    
                                    if(u1.check(username))
                                    {

                                        System.out.println("Enter your password:");
                                        int pass=sc.nextInt();
                                        int main_pass=0;
                                        String check_pass="select user_pass from users where user_name=?";
                                        PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                        ps_pass.setString(1, username);
                                        ResultSet rs_pass=ps_pass.executeQuery();

                                        while(rs_pass.next())
                                        {
                                            main_pass=rs_pass.getInt(1);
                                        }
                                        if(main_pass==pass)
                                        {
                                            File delFile=new File(username+".txt");
                                            delFile.delete();
                                            String sql="delete from users where user_name=?";
                                            PreparedStatement ps=con.prepareStatement(sql);
                                            ps.setString(1, username);
                                            ps.executeUpdate();
                                            
                                            System.out.println("user deleted successfully");
                                            System.out.println();
                                        }
                                        else
                                        {
                                            System.out.println("Please enter correct password");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("Please Enter correct user name");
                                    }
                                        
                                    break;
                                }
                                case 3:
                                {
                                    boolean b12=true;
                                    while(b12)
                                    {
                                        System.out.println("----------------------------");
                                        System.out.println("Upadate user Data");
                                        System.out.println("----------------------------");
                                        System.out.println("what do you want to upadate?");
                                        System.out.println("1 for update user name");
                                        System.out.println("2 for update password");
                                        System.out.println("3 for update email");
                                        System.out.println("4 for exit");
                                        System.out.println("----------------------------");
                                        System.out.println();
                                        System.out.println("Enter choice");
                                        int choice3=sc.nextInt();

                                        switch (choice3) 
                                        {
                                            case 1:
                                            {
                                                System.out.println("Enter username: ");
                                                String username1=sc.next();
                                                System.out.println();
                                                boolean check_name=u1.check(username1);
                                                if(check_name)
                                                {
                                                    System.out.println("Enter your password:");
                                                    int pass=sc.nextInt();
                                                    int main_pass=0;

                                                    String check_pass="select user_pass from users where user_name=?";
                                                    PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                    ps_pass.setString(1, username1);
                                                    ResultSet rs_pass=ps_pass.executeQuery();

                                                    while(rs_pass.next())
                                                    {
                                                        main_pass=rs_pass.getInt(1);
                                                    }

                                                    if(main_pass==pass)
                                                    {

                                                        System.out.println("Enter new username: ");
                                                        String newusername=sc.next();
                                                        if(u1.check(newusername))
                                                        {
                                                            System.out.println("Please enter different name");
                                                        }
                                                        else
                                                        {

                                                            File oldFile=new File(username1+".txt");
                                                            File newFile=new File(newusername+".txt");

                                                            oldFile.renameTo(newFile);
                            
                                                            String sql="update users set user_name=? where user_name=?";
                                                            PreparedStatement ps=con.prepareStatement(sql);
                                                            ps.setString(1, newusername);
                                                            ps.setString(2, username1);
                                                            ps.executeUpdate();
                                                            
                                                            System.out.println("user name updated successfully");
                                                            System.out.println();
                                                        }
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Please enter correct password");
                                                    }
                                                }
                                                else
                                                {
                                                    System.out.println("Enter correct user name");
                                                }
                                                
                                            }
                                            break;
                                            case 2:
                                            {
                                                
                                                System.out.println("Enter username: ");
                                                String username=sc.next();
                                                System.out.println();

                                                if(u1.check(username))
                                                {
                                                    
                                                    System.out.println("Enter your password:");
                                                    int pass=sc.nextInt();
                                                    int main_pass=0;

                                                    String check_pass="select user_pass from users where user_name=?";
                                                    PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                    ps_pass.setString(1, username);
                                                    ResultSet rs_pass=ps_pass.executeQuery();
                                                    
                                                    while(rs_pass.next())
                                                    {
                                                        main_pass=rs_pass.getInt(1);
                                                    }

                                                    if(main_pass==pass)
                                                    {
                                                        System.out.println("Enter new password: ");
                                                        int newpassword=sc.nextInt();
                                                        
                                                        String sql="update users set user_pass=? where user_name=?";
                                                        PreparedStatement ps=con.prepareStatement(sql);
                                                        ps.setInt(1, newpassword);
                                                        ps.setString(2, username);
                                                        ps.executeUpdate();
                                                        System.out.println("Password updated successfully");
                                                        System.out.println();
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Please enter correct password");
                                                    }
                                                }
                                                else
                                                {
                                                    System.out.println("Enter correct user name");
                                                }
                                                
                                            }
                                            break;
                                            case 3:
                                            {
                                                
                                                System.out.println("Enter username: ");
                                                String username=sc.next();
                                                System.out.println();
                                                if(u1.check(username))
                                                {

                                                    System.out.println("Enter your password:");
                                                    int pass=sc.nextInt();
                                                    int main_pass=0;

                                                    String check_pass="select user_pass from user where user_name=?";
                                                    PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                    ps_pass.setString(1, username);
                                                    ResultSet rs_pass=ps_pass.executeQuery();

                                                    while(rs_pass.next())
                                                    {
                                                        main_pass=rs_pass.getInt(1);
                                                    }
                                                    if(main_pass==pass)
                                                    {

                                                        System.out.println("Enter new email: ");
                                                        String newemail=sc.next();
                                            
                                                        String sql="update user set user_email=? where user_name=?";

                                                        PreparedStatement ps=con.prepareStatement(sql);
                                                        ps.setString(2, username);
                                                        ps.setString(1, newemail);
                                                        ps.executeUpdate();

                                                        System.out.println("user added successfully");
                                                        System.out.println();
                                                    }
                                                    else
                                                    {
                                                        System.out.println("Please enter correct password");
                                                    }
                                                }
                                                else
                                                {
                                                    System.out.println("Enter correct user name");
                                                }

                                                break;
                                            }
                                            case 4:
                                            b12=false;
                                            break;
                                            default:
                                            b12=false;
                                            break;
                                        }
                                        
                                            
                                    }
                                }
                                break;

                                case 4:
                                {
                                    System.out.println("Enter username: ");
                                    String username=sc.next();
                                    System.out.println();
                                    if(u1.check(username))
                                    {

                                        String st_user="select user_name,user_email,amount from users where user_name=?";
                                            PreparedStatement ps_user=con.prepareStatement(st_user);
                                            ps_user.setString(1, username);
                                            ResultSet rs_user=ps_user.executeQuery();
    
                                            while(rs_user.next())
                                            {
                                                System.out.println("Your user name is    :"+rs_user.getString(1));
                                                System.out.println("Your email is        :"+rs_user.getString(2));
                                                System.out.println("Your total amount is :"+rs_user.getInt(3));
                                            }
                                        
                                    }
                                    else
                                    {
                                        System.out.println("Enter correct user name");
                                    }
                                }
                                break;

                                case 5:
                                {
                                    b11=false;
                                }
                                break;
                                default:
                                {
                                    b11=false;
                                }
                                break;
                            }
                        }
                    }
                    break;


                    //Restaurant Management


                    case 2:
                    {
                        boolean b21=true;
                        while(b21)
                        {
                            System.out.println("Restaurant Management");
                            System.out.println("----------------------------");
                            System.out.println("1. Add Restaurant");
                            System.out.println("2. Update Restaurant");
                            System.out.println("3. Delete Restaurant");
                            System.out.println("4. See Restaurant details");
                            System.out.println("5. Exit");
                            System.out.println("----------------------------");
                            System.out.println("Enter your choice: ");
                            int choice4=sc.nextInt();
                            System.out.println();
                            switch(choice4)
                            {
                                case 1:
                                {
                                    System.out.println("Add Restaurant");
                                    System.out.println("----------------------------");
                                    System.out.println("Enter Restaurant Name: ");
                                    String restaurantname=sc.next();

                                    if(t1.check(restaurantname))
                                    {

                                        System.out.println("please enter diffrent name");
                                        
                                    }
                                    else
                                    {
                                        t1.add(restaurantname);
                                        
                                        System.out.println("Enter Restaurant Address: ");
                                        String restaurantaddress=sc.next();
                                        
                                        System.out.println("Enter Restaurant Phone Number: ");
                                        String restaurantphone=sc.next();
                                        System.out.println("Enter Pasword: ");
                                        int pass=sc.nextInt();
                                        System.out.println("----------------------------");

                                        Statement st=con.createStatement();
                                        String sql="insert into restaurant(res_name,res_pass,res_address,res_no) values(?,?,?,?)";
                                        PreparedStatement ps=con.prepareStatement(sql);
                                        ps.setString(1, restaurantname);
                                        ps.setInt(2, pass);
                                        ps.setString(3, restaurantaddress);
                                        ps.setString(4, restaurantphone);
                                        ps.executeUpdate();
                                        
                                        String sql1="CREATE TABLE "+restaurantname+" (dish_id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,dish_name varchar(255) NOT NULL,dish_price int(10) not null)";
                                        st.executeUpdate(sql1);
                                        System.out.println("Restaurant added successfully");
                                        System.out.println();
                                    }
                                }
                                break;

                                case 2:
                                {
                                    boolean b22=true;
                                    while(b22)
                                    {
                                        System.out.println("Update Restaurant");
                                        System.out.println("----------------------------");
                                        
                                                System.out.println();
                                                System.out.println("----------------------------");
                                                System.out.println("1. for change restaurant name");
                                                System.out.println("2. for change restaurant address");
                                                System.out.println("3. for change restaurant mobile number");
                                                System.out.println("4. for exit");
                                                System.out.println("----------------------------");
                                                System.out.println("Enter your choice: ");
                                                int choice5=sc.nextInt();
        
                                                switch (choice5) {
                                                    case 1:
                                                    {
                                                        System.out.println("Enter Restaurant Name: ");
                                                        String restaurantname=sc.next();
                                                        
                                                        if(t1.check(restaurantname))
                                                        {
                                                            System.out.println("Enter your password:");
                                                            int pass=sc.nextInt();
                                                            int main_pass=0;
                                                            String check_pass="select res_pass from restaurant where res_name=?";
                                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                            ps_pass.setString(1, restaurantname);
                                                            ResultSet rs_pass=ps_pass.executeQuery();

                                                            while(rs_pass.next())
                                                            {
                                                                main_pass=rs_pass.getInt(1);
                                                            }
                                                            if(main_pass==pass)
                                                            {
                                                                System.out.println("Enter new restaurant name: ");
                                                                String newrestaurantname=sc.next();
                                                    
                                                                //t1.change(restaurantname, newrestaurantname);
                                                                
                                                                String sql="update restaurant set res_name=? where res_name=?"; 
                                                                PreparedStatement ps=con.prepareStatement(sql);
                                                                ps.setString(1, newrestaurantname);
                                                                ps.setString(2, restaurantname);
                                                                ps.executeUpdate();
                
                                                                String sql1="RENAME TABLE "+ restaurantname +" TO "+newrestaurantname; 
                                                                PreparedStatement ps1=con.prepareStatement(sql1);
                                                                    ps1.execute();
                
                                                                    System.out.println("Restaurant name updated successfully");
                                                                    System.out.println();
                                                                }
                                                                else
                                                                {
                                                                    System.out.println("Please enter correct password");
                                                                }  
                                                            }
                                                            else
                                                            {
                                                                System.out.println("This restaurant is not in database");
                                                            }
                                                        
                                                    }
                                                    break;
        
                                                    case 2:
                                                    {
                                                        System.out.println("Enter Restaurant Name: ");
                                                        String restaurantname=sc.next();
                                                        
                                                        if(t1.check(restaurantname))
                                                        {
                                                            System.out.println("Enter your password:");
                                                            int pass=sc.nextInt();
                                                            int main_pass=0;
                                                            String check_pass="select res_pass from restaurant where res_name=?";
                                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                            ps_pass.setString(1, restaurantname);
                                                            ResultSet rs_pass=ps_pass.executeQuery();

                                                            while(rs_pass.next())
                                                            {
                                                                main_pass=rs_pass.getInt(1);
                                                            }
                                                            if(main_pass==pass)
                                                            {
                                                                System.out.println("Enter new restaurant address: ");
                                                                String newrestaurantaddress=sc.next();
                                                                String sql="update restaurant set res_address=? where res_name=?";
                
                                                                PreparedStatement ps=con.prepareStatement(sql); 
                                                                ps.setString(1, newrestaurantaddress);
                                                                ps.setString(2, restaurantname);
                                                                ps.executeUpdate();
                                                                System.out.println("Restaurant address updated successfully");
                                                                System.out.println();
                                                            }
                                                            else
                                                            {
                                                                System.out.println("Please enter correct password");
                                                            }  
                                                        }
                                                        else
                                                        {
                                                            System.out.println("This restaurant is not in database");
                                                        }
                                                        
                                                    }
                                                    break;
        
                                                    case 3:
                                                    {

                                                        System.out.println("Enter Restaurant Name: ");
                                                        String restaurantname=sc.next();
                                                        
                                                        if(t1.check(restaurantname))
                                                        {
                                                            System.out.println("Enter your password:");
                                                            int pass=sc.nextInt();
                                                            int main_pass=0;
                                                            String check_pass="select res_pass from restaurant where res_name=?";
                                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                                            ps_pass.setString(1, restaurantname);
                                                            ResultSet rs_pass=ps_pass.executeQuery();

                                                            while(rs_pass.next())
                                                            {
                                                                main_pass=rs_pass.getInt(1);
                                                            }
                                                            if(main_pass==pass)
                                                            {
                                                                System.out.println("Enter new restaurant mobile number: ");
                                                                int newrestaurantmobile=sc.nextInt();
                                                                String sql="update restaurant set res_mobile=? where res_name=?";
                                                                
                                                                PreparedStatement ps=con.prepareStatement(sql);
                                                                ps.setInt(1, newrestaurantmobile);
                                                                ps.setString(2, restaurantname);
                                                                ps.executeUpdate();
                                                                System.out.println("Restaurant mobile number updated successfully");
                                                                System.out.println();
                                                            }
                                                            else
                                                            {
                                                                System.out.println("Please enter correct password");
                                                            }  
                                                        }
                                                        else
                                                        {
                                                            System.out.println("This restaurant is not in database");
                                                        }
                                                    }
                                                    break;
                                                    
                                                    case 4:
                                                    {
                                                        b22=false;
                                                    }
                                                    break;
                                                    default:
                                                        
                                                    break;
                                                }
                                            
                                    }
                                }
                                break;

                                case 3:
                                {

                                    System.out.println("Delete Restaurant");
                                    System.out.println("----------------------------");
                                    System.out.println("Enter Restaurant Name: ");
                                    String restaurantname=sc.next();

                                    if(t1.check(restaurantname))
                                    {
                                        System.out.println("Enter your password:");
                                        int pass=sc.nextInt();
                                        int main_pass=0;
                                        String check_pass="select res_pass from restaurant where res_name=?";
                                        PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                        ps_pass.setString(1, restaurantname);
                                        ResultSet rs_pass=ps_pass.executeQuery();

                                        while(rs_pass.next())
                                        {
                                            main_pass=rs_pass.getInt(1);
                                        }

                                        if(main_pass==pass)
                                        {
                                            t1.delete(restaurantname);
        
                                            System.out.println("----------------------------");
                                            String sql="delete from user where user_name=?";
                                            PreparedStatement ps=con.prepareStatement(sql);
                                            ps.setString(1, restaurantname);
                                            ps.executeUpdate();
        
                                            String sql1="drop table "+restaurantname;
                                            PreparedStatement ps1=con.prepareStatement(sql1);
                                            ps1.execute();
                                            System.out.println("Restaurant deleted successfully");
        
                                            System.out.println();
                                        }
                                        else
                                        {
                                            System.out.println("Please enter correct password");
                                        }
                                    }
                                    else
                                    {
                                        System.out.println("This restaurant is not in database");
                                    }
                                    
                                }
                                break;
                                case 4:
                                {
                                    System.out.println("Enter Restaurant name: ");
                                    String restaurantname=sc.next();
                                    System.out.println();
                                    if(t1.check(restaurantname))
                                    {

                                        String st_user="select res_name,res_address,res_no from restaurant where res_name=?";
                                        PreparedStatement ps_user=con.prepareStatement(st_user);
                                        ps_user.setString(1, restaurantname);
                                        ResultSet rs_user=ps_user.executeQuery();

                                        while(rs_user.next())
                                        {
                                            System.out.println("Restaurant name is    :"+rs_user.getString(1));
                                            System.out.println("Restaurant address is :"+rs_user.getString(2));
                                            System.out.println("Mobile no is          :"+rs_user.getInt(3));
                                        }
                                        
                                    }
                                    else
                                    {
                                        System.out.println("Enter correct restaurant name");
                                    }
                                }

                                case 5:
                                {
                                    b21=false;
                                }
                                break;
                                default:
                                    b21=false;
                                break;
                            }
                        }
                    }
                    break;

                    //Menu Management

                    case 3:
                    {
                        boolean b31=true;
                        while(b31)
                        {
                            System.out.println("Menu Management");
                            System.out.println("----------------------------");
                            System.out.println();
                            System.out.println("1. Add Dishes");
                            System.out.println("2. Delete Dishes");
                            System.out.println("3. Update");
                            System.out.println("4. exit");
                            System.out.println("----------------------------");
                            System.out.println("Enter your choice: ");
                            int choice7=sc.nextInt();

                                switch(choice7)
                                {
                                    case 1:
                                    {
                                        System.out.println("Enter Restaurant Name: ");
                                        String restaurantname=sc.next();
                                        if(t1.check(restaurantname))
                                        {
                                            System.out.println("Enter your password:");
                                            int pass=sc.nextInt();
                                            int main_pass=0;
                                            String check_pass="select res_pass from restaurant where res_name=?";
                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                            ps_pass.setString(1, restaurantname);
                                            ResultSet rs_pass=ps_pass.executeQuery();

                                            while(rs_pass.next())
                                            {
                                                main_pass=rs_pass.getInt(1);
                                            }

                                            if(pass==main_pass)
                                            {
                                                System.out.println("Add Dishes");
                                                System.out.println("----------------------------");
                                                boolean b5=true;

                                                while(b5)
                                                {
                                                    System.out.println("Enter dish name:");
                                                    String dishname=sc.next();
                                                    
                                                    System.out.println("Enter dish price:");
                                                    int dishprice=sc.nextInt();

                                                    String sql3="insert into "+restaurantname+" (dish_name,dish_price) values(?,?)";
                                                    PreparedStatement ps1=con.prepareStatement(sql3);
                                                    ps1.setString(1, dishname);
                                                    ps1.setInt(2, dishprice);
                                                    ps1.executeUpdate();

                                                    System.out.println("Dish insertes successfully");
                                                    System.out.println();

                                                    System.out.println("Do you want to enter more dishes?");
                                                    String ans=sc.next();

                                                    if(ans.equalsIgnoreCase("yes"))
                                                    {
                                                        b5=true;
                                                    }
                                                    else
                                                    {
                                                        b5=false;
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Please enter correct password");
                                            }
                                        }

                                        else
                                        {
                                            System.out.println("This restaurant is not in database");
                                        }
                                    }
                                    break;

                                    case 2:
                                    {
                                        System.out.println("Enter Restaurant Name: ");
                                        String restaurantname=sc.next();

                                        if(t1.check(restaurantname))
                                        {
                                            System.out.println("Enter your password:");
                                            int pass=sc.nextInt();
                                            int main_pass=0;
                                            String check_pass="select res_pass from restaurant where res_name=?";
                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                            ps_pass.setString(1, restaurantname);
                                            ResultSet rs_pass=ps_pass.executeQuery();

                                            while(rs_pass.next())
                                            {
                                                main_pass=rs_pass.getInt(1);
                                            }

                                            if(pass==main_pass)
                                            {
                                                boolean b32=true;
                                                while(b32)
                                                {
                                                    System.out.println("1. Change dish name:");
                                                    System.out.println("2. Change dish price: ");
                                                    System.out.println("3. Exit");
                                                    System.out.println();
                                                    System.out.println("Enter Choice:");
                                                    int choice6=sc.nextInt();

                                                    
                                                    switch(choice6)
                                                    {
                                                        case 1:
                                                        {
                                                            System.out.println("Enter old dish name:");
                                                            String dishname1=sc.next();
                                                            
                                                            System.out.println("Enter new dish name: ");
                                                            String newdishname=sc.next();
                                                            String sql="update "+restaurantname+" set dish_name=? where dish_name="+dishname1;
                                                            PreparedStatement ps=con.prepareStatement(sql);
                                                            ps.setString(1, newdishname);
                                                            
                                                        }
                                                        break;

                                                        case 2:
                                                        {
                                                            System.out.println("Enter dish name: ");
                                                            String dishname=sc.next();

                                                            
                                                                System.out.println("Enter new price:");
                                                                int newprice=sc.nextInt();
                                                                String sql="update "+restaurantname+" set dish_price=? where dish_name="+dishname;
                                                                PreparedStatement ps=con.prepareStatement(sql);
                                                                ps.setInt(1, newprice);
                                                            
                                                        }
                                                        break;
                                                        case 3:
                                                        {
                                                            b32=false;
                                                        }
                                                        break;

                                                        default:
                                                        b32=false;
                                                        break;
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Please enter correct password");
                                            }
                                        }

                                        else
                                        {
                                            System.out.println("This restaurant is not in database");
                                        }
                                    }
                                    break;
                                    case 3:
                                    {
                                        System.out.println("Enter Restaurant Name: ");
                                        String restaurantname=sc.next(); 
                                        if(t1.check(restaurantname))
                                        {
                                            System.out.println("Enter your password:");
                                            int pass=sc.nextInt();
                                            int main_pass=0;
                                            String check_pass="select res_pass from restaurant where res_name=?";
                                            PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                            ps_pass.setString(1, restaurantname);
                                            ResultSet rs_pass=ps_pass.executeQuery();

                                            while(rs_pass.next())
                                            {
                                                main_pass=rs_pass.getInt(1);
                                            }

                                            if(pass==main_pass)
                                            {
                                                System.out.println("Delet Dishes");
                                                System.out.println("----------------------------");
                                                boolean b5=true;

                                                while(b5)
                                                {
                                                    System.out.println("1 delete particular dish");
                                                    System.out.println("2. Delete all dishes");
                                                    System.out.println("3. Exit");
                                                    System.out.println("Enter your choice: ");
                                                    int choice8=sc.nextInt();

                                                    switch(choice8)
                                                    {
                                                        case 1:
                                                        {
                                                            System.out.println("Enter dish name:");
                                                            String dishname=sc.next();

                                                            String sql="delete from "+restaurantname+" where dish_name=?";
                                                            PreparedStatement ps=con.prepareStatement(sql);
                                                            ps.setString(1, dishname);
                                                            ps.executeUpdate();
                                                            System.out.println("Dish deleted successfully");
                                                            System.out.println();
                                                        }
                                                        break;
                                                        case 2:
                                                        {
                                                            String sql="delete from "+restaurantname;
                                                            PreparedStatement ps=con.prepareStatement(sql);
                                                            ps.executeUpdate();
                                                            System.out.println("All dishes deleted successfully");
                                                            System.out.println();
                                                        }
                                                        break;
                                                        case 3:
                                                        {
                                                            b5=false;
                                                        }
                                                        break;
                                                        default:
                                                            b5=false;
                                                        break;
                                                    }
                                                }
                                            }
                                            else
                                            {
                                                System.out.println("Please enter correct password");
                                            }
                                        }

                                        else
                                        {
                                            System.out.println("This restaurant is not in database");
                                        }
                                    }
                                    break;
                                    case 4:
                                        b31=false;
                                    break;

                                    default:
                                        b31=false;
                                    break;
                                }
                        
                        }
                    }
                    break;

                    //order management

                    case 4:
                    {
                        System.out.println("Order Management");
                        System.out.println("----------------------------");

                        System.out.println("Enter user name:");
                        String username=sc.next();
                        
                        if(u1.check(username))
                        {
                            System.out.println();
                            System.out.println("-----------Notice-----------");
                            System.out.println("Once you place an order, it cannot be canceled, so please order carefully.");
                            System.out.println();

                            System.out.println("Restaurant Name list:");
                            System.out.println();
                            
                            t1.display();

                            System.out.println("----------------------------");
                            System.out.println();
                            System.out.println("Enter restaurant name:");
                            String res_name=sc.next();

                            
                            String sql1="select *from "+res_name;
                            Statement st1=con.createStatement();
                            ResultSet rs1=st1.executeQuery(sql1);

                            System.out.println("Menu list:");
                            System.out.println();
                            
                            while(rs1.next())
                            {
                                String dish_name=rs1.getString("dish_name");
                                int dish_price=rs1.getInt("dish_price");

                                System.out.println(dish_name+" :> "+dish_price );
                            }
                            System.out.println("----------------------------");
                            System.out.println();
                            String dish_name="";
                            int quantity=0;
                            boolean add_dish=true;
                            HashMap<String,Integer> h1=new HashMap<>();
                            while(add_dish)
                            {
                                System.out.println("Enter dish name:");
                                dish_name=sc.next();

                                String check_dish="select *from "+res_name;
                                Statement str_dish=con.createStatement();
                                ResultSet res_dish=str_dish.executeQuery(check_dish);
                                while(res_dish.next())
                                {
                                    String dishname=rsres.getString("dish_name");
                                    d1.add(dishname);
                                }                    

                                boolean dish_check=d1.check(dish_name);
                                if(dish_check)
                                {
                                    System.out.println("Enter quantity:");
                                    quantity=sc.nextInt();
                                    
                                    h1.put(dish_name, quantity);
                                    
                                    System.out.println("Do you want to add anything?:");
                                    String ans=sc.next();
                                    
                                    if(ans.equalsIgnoreCase("no"))
                                    {
                                        add_dish=false;
                                    }
                                }
                                else
                                {
                                    System.out.println("Please enter valid dish");
                                }

                            }
                            System.out.println(">> Your order list is<<");
                            
                            for(String name : h1.keySet())
                            {
                                String key=name.toString();
                                String value=h1.get(key).toString();

                                System.out.println(key+"  "+value);
                            }

                            o1.message(username);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                // handle the exception
                            }
                            System.out.println("Your order has been successfully placed.");

                            //user total amount
                            int amount=0;
                            String sql_amount="select amount from users where user_name=?";
                            PreparedStatement ps_amount=con.prepareStatement(sql_amount);
                            ps_amount.setString(1, username);
                            ResultSet rs_user=ps_amount.executeQuery();
                            while(rs_user.next())
                            {
                                amount=rs_user.getInt(1);
                            }

                            //dish price
                            
                            int price=0;
                            String sql_price="select dish_price from "+res_name+" where dish_name=?";
                            PreparedStatement ps_price=con.prepareStatement(sql_price);
                            ps_price.setString(1, dish_name);
                            ResultSet rs_price=ps_price.executeQuery();
                            while(rs_price.next())
                            {
                                price=rs_price.getInt(1);
                            }
                            
                            int total_amount=amount+(price*quantity);
                            String set_price="update users set amount=? where user_name=?";
                            PreparedStatement set_ps=con.prepareStatement(set_price);
                            set_ps.setInt(1, total_amount);
                            set_ps.setString(2, username);
                            set_ps.executeUpdate();

                            File fdish=new File(username+".txt");
                            FileWriter fr=new FileWriter(fdish);
                            BufferedWriter br=new BufferedWriter(fr);
                            br.write("Restaurant: "+res_name+" Dish name: "+dish_name+" Quantity: "+quantity+" Total Amount: "+total_amount);
                            br.newLine();
                            br.close();

                            System.out.println("Your total amount is: "+total_amount);
                            System.out.println();
                            System.out.println("1 for upi");
                            System.out.println("2 for Cash");
                            System.out.println("3 for card");
                            System.out.println("Please enter your payment methode");
                            int payment=sc.nextInt();

                            switch (payment) {
                                case 1:
                                    System.out.println("Please enter your upi id:-");
                                    String upi_id=sc.next();
                                    String upi_sql="update users set user_data=? where user_name=?";
                                    PreparedStatement upi_ps=con.prepareStatement(upi_sql);
                                    upi_ps.setString(1, upi_id);
                                    
                                    String methode="UPI";
                                    String paymet_methode="Update users set user_methode=? where user_name=?";
                                    PreparedStatement pay_ps=con.prepareStatement(paymet_methode);
                                    pay_ps.setString(1, methode);
                                    pay_ps.setString(2, username);
                                    pay_ps.executeUpdate();

                                    System.out.println("Payment Success full");
                                    break;

                                case 2:

                                    String methode1="Cash";
                                    String paymet_methode1="Update users set user_methode=? where user_name=?";
                                    PreparedStatement pay_ps1=con.prepareStatement(paymet_methode1);
                                    pay_ps1.setString(1, methode1);
                                    pay_ps1.setString(2, username);
                                    pay_ps1.executeUpdate();

                                    System.out.println("Payment Success full");
                                break;

                                case 3:
                                    System.out.println("Please enter your card no:-");
                                    String upi_id2=sc.next();
                                    String upi_sql2="update users set user_data=? where user_name=?";
                                    PreparedStatement upi_ps2=con.prepareStatement(upi_sql2);
                                    upi_ps2.setString(1, upi_id2);
                                    upi_ps2.executeUpdate();

                                    String methode2="UPI";
                                    String paymet_methode2="Update users set user_methode=? where user_name=?";
                                    PreparedStatement pay_ps2=con.prepareStatement(paymet_methode2);
                                    pay_ps2.setString(1, methode2);
                                    pay_ps2.setString(2, username);
                                    pay_ps2.executeUpdate();

                                    System.out.println("Payment Success full");
                                break;
                            
                                default:
                                    break;
                            }
                            

                        }
                        else
                        {
                            System.out.println("Please enter correct user name");
                        }
                
                    }
                    break;

                    case 5:
                    {
                        b=false;
                    }
                    break;

                    default:
                    b=false;
                    break;
                }
            }
        }
    }
}