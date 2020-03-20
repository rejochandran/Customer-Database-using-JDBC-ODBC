import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.*;
import java.util.*;
import java.sql.*; 
import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*; 


public class CustomerDB {
//test
	
	public static void main(String[] argv) throws Exception {		
		String url = "jdbc:odbc:custo"; //custo is the database name created in this example
		Connection connection = DriverManager.getConnection(url, "", "");
		Statement stmt = connection.createStatement();
		Scanner inp = new Scanner(System.in);		
		Scanner inp1 = new Scanner(System.in);
		Scanner inp2 = new Scanner(System.in);	
		int cust_id ;
		ResultSet rs1;	
		String id_name ;
		int flag=1,choice;
		try {
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return;
		}
		/** Console UI */
		while(flag == 1)
		{
			System.out.println("\n============== Customer Database ==============\n\n");
			System.out.println("1. Add Customer\n2.Update Customer\n3.Add CreditPoints\n4.Display Database\n\n");
			System.out.print("Enter choice: ");
			choice = inp.nextInt();
			System.out.print("\n\n");		
			switch(choice)
			{
				case 1:

				String name,area;
				int price,phone,cp=1;
				String sql;
				System.out.print("Enter Name :");
				name = inp2.nextLine();
				System.out.print("Gross Price :");
				price = inp1.nextInt();
				System.out.print("Area :");
				area = inp2.nextLine();
				boolean test=true;
				do{
					if(area.length()>10)
				{ 
					System.out.println("Enter shorter Area Name");
					area=inp2.nextLine();
				}
				if(area.length()<10)
				{
					test=false;
				}

				}while(test);
				System.out.print("Phone :");
				phone = inp2.nextInt();
				sql="INSERT INTO cust(name,grossprice,area,phone,creditpoints) VALUES('"+name+"',"+price+",'"+area+"',"+phone+",0)";
				int rs = stmt.executeUpdate(sql);
				System.out.println("Entry Added");
				break;
				case 2:
				System.out.print("Enter Customer id to update : ");
				cust_id = inp.nextInt();
				sql = "SELECT name FROM cust WHERE id = "+cust_id;
				rs1 = stmt.executeQuery(sql);
				rs1.next();
				id_name = rs1.getString("name");
				System.out.print("\n\nUPDATING DETAILS OF : "+id_name+"\n\n");
				System.out.println("What do you want to update ?\n\n");
				System.out.println("1. Name\n2.Gross Price\n3.Area\n4.Phone\n5.Credit Points\n ");
				System.out.print("Enter Choice : ");
				int cho = inp.nextInt();
				int rs3;
				String new_name,new_area;
				int new_price,new_phone,new_cp;
				switch(cho)
				{
					case 1:
					System.out.println("\n\n------EDITING NAME------");
					System.out.print("Enter customer's new name : ");
					new_name = inp2.nextLine();
					sql="UPDATE cust SET name = '"+ new_name+"' WHERE ID = "+cust_id;
					rs3 = stmt.executeUpdate(sql);
					System.out.println("\n\n------UPDATE SUCCESSFUL------");
					break;
					case 2:
					System.out.println("\n\n------EDITING GROSS PRICE------");
					System.out.print("Enter customer's new grossprice : ");
					new_price = inp.nextInt();
					sql="UPDATE cust SET grossprice = "+ new_price+" WHERE ID = "+cust_id;
					rs3 = stmt.executeUpdate(sql);
					System.out.println("\n\n------UPDATE SUCCESSFUL------");
					break;
					case 3:
					System.out.println("\n\n------EDITING AREA------");
					System.out.print("Enter customer's new area : ");
					new_area = inp.nextLine();
					sql="UPDATE cust SET area = '"+ new_area +"' WHERE ID = "+cust_id;
					rs3 = stmt.executeUpdate(sql);
					System.out.println("\n\n------UPDATE SUCCESSFUL------");
					break;
					case 4:
					System.out.println("\n\n------EDITING PHONE------");
					System.out.print("Enter customer's new phone : ");
					new_phone = inp.nextInt();
					sql="UPDATE cust SET phone = "+ new_phone +" WHERE ID = "+cust_id;
					rs3 = stmt.executeUpdate(sql);
					System.out.println("\n\n------UPDATE SUCCESSFUL------");
					break;
					case 5:
					System.out.println("\n\n------EDITING CREDIT POINTS------");
					System.out.print("Enter customer's new Credit Points : ");
					new_cp = inp.nextInt();
					sql="UPDATE cust SET creditpoints = "+ new_cp +" WHERE ID = "+cust_id;
					rs3 = stmt.executeUpdate(sql);
					System.out.println("\n\n------UPDATE SUCCESSFUL------");
					break;
				}
				break;
				case 3:
				System.out.print("Enter Customer id to add Credit Points : ");
				cust_id = inp1.nextInt();
				sql = "SELECT name FROM cust WHERE id = "+cust_id;
				rs1 = stmt.executeQuery(sql);
				rs1.next();
				id_name = rs1.getString("name");
				System.out.print("\n\nEnter Credit Points to add for "+id_name+" : ");
				int addcr = inp2.nextInt();
					//================
				sql = "SELECT creditpoints FROM cust WHERE id = "+cust_id;
				rs1 = stmt.executeQuery(sql);
				rs1.next();
				int curr_pts = rs1.getInt("creditpoints");
				int update_crp = addcr + curr_pts;
				sql="UPDATE cust SET creditpoints = "+ update_crp +" WHERE ID = "+cust_id;
				rs3 = stmt.executeUpdate(sql);	
						//================
				System.out.println("\n\n------ADDED CREDITPOINTS------\n\n");
				break;
				case 4:
				try {
					int ida;
					String name1="";
					int price1;
					String area1;
					int phone1=0,cp1=0;
					sql = "SELECT * FROM cust";
					rs1 = stmt.executeQuery(sql);
					System.out.println("ID\tName\tPrice\tArea\t\tPhone\t\tCredit Points");
					while(rs1.next()){
						
						ida  = rs1.getInt("id");
						name1 = rs1.getString("name");
						price1 = rs1.getInt("grossprice");
						area1 = 	rs1.getString("area");
						phone1 = rs1.getInt("phone");
						cp1 = rs1.getInt("creditpoints");
						System.out.println(ida+"\t"+name1+"\t"+price1+"\t"+area1+"\t"+phone1+"\t\t"+cp1);      		
					}				
				} catch (SQLException e) {
					System.out.println("Connection Failed! Check output console");
					e.printStackTrace();
					return;
				}
				break;
				default:
				flag=0;break;
			}

		}	
	}
}

