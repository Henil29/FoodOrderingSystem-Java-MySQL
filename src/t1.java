import java.sql.*;
import java.util.Scanner;

import test.Restaurant;
import test.user;

class t1
{
    public static void main(String[] args)throws Exception {
        Scanner sc=new Scanner(System.in);
        Restaurant t1=new Restaurant();
        user u1=new user();
        String dbURL="jdbc:mysql://localhost:3306/zomato";
        String dbUser="root";
        String dbpass="";

        Connection con=DriverManager.getConnection(dbURL, dbUser, dbpass);

        // String sqlres="select *from restaurant";
        // Statement stres=con.createStatement();
        // ResultSet rsres=stres.executeQuery(sqlres);
        // while(rsres.next())
        // {
        //     String res_name=rsres.getString("res_name");
        //     t1.add(res_name);
        // }

        // t1.display();
        // String restaurantname=sc.next();
        // boolean checkb=t1.check(restaurantname);

        // if(checkb==true)
        // {
        //     System.out.println("true");
        // }
        // else
        // {
        //     System.out.println("False");
        // }
        
        // System.out.println("Enter res name:");
        // String res_name=sc.next();
        // System.out.println(t1.check(res_name));
        
        // user u1=new user();
        // String sqluser="select*from user";
        // Statement stuser=con.createStatement();
        // ResultSet rsuser=stuser.executeQuery(sqluser);

        // while(rsuser.next())
        // {
        //     String user_name=rsuser.getString("user_name");
        //     u1.add(user_name);
        // }

        // u1.display();

        // System.out.println("Enter user:");
        // String user=sc.next();

        // if(u1.check(user))
        // {
        //     System.out.println("Done");
        // }
        // else
        // {
        //     System.out.println("Not");
        // }
        // int amount=0;
        // System.out.println("Res name:");
        // String res_nane=sc.next();
        // System.out.println("Dish name:");
        // String dish_name=sc.next();
        // String res="select dish_price as "+amount+" from "+res_nane+" where dish_name=?";
        // PreparedStatement pr=con.prepareStatement(res);
        // pr.setString(1, dish_name);
        // ResultSet rs=pr.executeQuery();
        
        // if(rs.next())
        // {
        //     amount=rs.getInt("dish_price");
        //     System.out.println("Yes");
        // }
        // else
        // {
        //     System.out.println("not");
        // }

        // System.out.println(amount);

        // String username="b1";
        //  int amount=0;
         //Statement st=con.createStatement();
        // String get_price="select amount from user where user_name=?";
        // PreparedStatement pst=con.prepareStatement(get_price);
        // pst.setString(1, "b1");
        // pst.executeQuery();
        // ResultSet get_rs=pst.executeQuery(get_price);

        // while(get_rs.next())
        // {
        //     amount=get_rs.getInt("amount");
        // }
        // System.out.println(amount);

        // System.out.println("a");
        // String s=sc.next();
        
        // String sqlres="select amount from user where user_name=?";
        // PreparedStatement pst=con.prepareStatement(sqlres);
        // pst.setString(1, s);
        // ResultSet rsres=pst.executeQuery();
        // while(rsres.next())
        // {
        //     int amount=rsres.getInt(1);
        //     System.out.println(amount);
        // }

                                        // String sql="{call addUser(?,?,?)}";
                                        // // Statement st=con.createStatement();
                                        // CallableStatement cs=con.prepareCall(sql);
                                        // cs.setString(1, username);
                                        // cs.setString(2, password);
                                        // cs.setString(3, email);
                                        // System.out.println("User added successfully");
                                        // System.out.println();
                                        // cs.execute();
                                        // int amount=0;
                                        // String username="b1";
                                        // String sql="{call get_amount(?,?)}";
                                        // CallableStatement cs=con.prepareCall(sql);
                                        // cs.setString(1, username);
                                        // cs.execute();
                                        // System.out.println();
                                        // cs.getInt(2,amount);

                                        // System.out.println("Enter username: ");
                                        // String username=sc.next();
                                        // System.out.println();
                                        
    
                                            // String st_user="select user_name,user_email,amount from user where user_name=?";
                                            // PreparedStatement ps_user=con.prepareStatement(st_user);
                                            // ps_user.setString(1, username);
                                            // ResultSet rs_user=ps_user.executeQuery();
    
                                            // while(rs_user.next())
                                            // {
                                            //     System.out.println(rs_user.getString(1));
                                            //     System.out.println(rs_user.getString(2));
                                            //     System.out.println(rs_user.getInt(3));
                                            // }
                                        // String restaurantname="g1";
                                        // System.out.println("Enter your password:");
                                        // int pass=sc.nextInt();
                                        // int main_pass=0;
                                        // String check_pass="select res_pass from restaurant where res_name=?";
                                        // PreparedStatement ps_pass=con.prepareStatement(check_pass);
                                        // ps_pass.setString(1, restaurantname);
                                        // ResultSet rs_pass=ps_pass.executeQuery();

                                        // while(rs_pass.next())
                                        // {
                                        //     main_pass=rs_pass.getInt(1);
                                        // }

                                        // if(pass==main_pass)
                                        // {
                                        //     System.out.println("Pass");
                                        // }
                                        // int amount=0;
                                        // String username="b1";

                                        // String sql_amount="select amount from user where user_name=?";
                                        // PreparedStatement ps_amount=con.prepareStatement(sql_amount);
                                        // ps_amount.setString(1, username);
                                        // ResultSet rs_user=ps_amount.executeQuery();
                                        // while(rs_user.next())
                                        // {
                                        //     amount=rs_user.getInt(1);
                                        // }

                                        // user u1=new user();
                                        String sqluser="select*from users";
                                        Statement stuser=con.createStatement();
                                        ResultSet rsuser=stuser.executeQuery(sqluser);
                                        String user_name="";
                                        while(rsuser.next())
                                        {
                                            user_name=rsuser.getString("user_name");
                                            System.out.println(user_name);
                                            u1.add(user_name);
                                        }

                                        System.out.println(u1.check(user_name));

    }
}