import java.sql.*;
import java.util.Scanner;

public class Employee {

    static final String URL = "jdbc:mysql://localhost:3306/employee_db";
    static final String USER = "root";
    static final String PASSWORD = "Mysql@123"; // Replace with your MySQL password

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASSWORD);

            Statement st = con.createStatement();

            st.executeUpdate("CREATE TABLE IF NOT EXISTS employee("
                    + "id INT PRIMARY KEY,"
                    + "name VARCHAR(50),"
                    + "salary DOUBLE)");

            int choice;

            do {

                System.out.println("\n===== EMPLOYEE MENU =====");
                System.out.println("1. Insert");
                System.out.println("2. Display");
                System.out.println("3. Update");
                System.out.println("4. Delete");
                System.out.println("5. Exit");
                System.out.print("Enter Choice : ");

                choice = sc.nextInt();

                switch(choice){

                    case 1:

                        System.out.print("Enter ID : ");
                        int id = sc.nextInt();

                        sc.nextLine();

                        System.out.print("Enter Name : ");
                        String name = sc.nextLine();

                        System.out.print("Enter Salary : ");
                        double salary = sc.nextDouble();

                        PreparedStatement ps = con.prepareStatement(
                                "INSERT INTO employee VALUES(?,?,?)");

                        ps.setInt(1,id);
                        ps.setString(2,name);
                        ps.setDouble(3,salary);

                        ps.executeUpdate();

                        System.out.println("Record Inserted Successfully.");

                        break;

                    case 2:

                        ResultSet rs = st.executeQuery("SELECT * FROM employee");

                        System.out.println("\nID\tNAME\tSALARY");

                        while(rs.next()){

                            System.out.println(
                                    rs.getInt("id") + "\t"
                                    + rs.getString("name") + "\t"
                                    + rs.getDouble("salary"));
                        }

                        break;

                    case 3:

                        System.out.print("Enter Employee ID : ");
                        int uid = sc.nextInt();

                        System.out.print("Enter New Salary : ");
                        double newsalary = sc.nextDouble();

                        PreparedStatement ps2 = con.prepareStatement(
                                "UPDATE employee SET salary=? WHERE id=?");

                        ps2.setDouble(1, newsalary);
                        ps2.setInt(2, uid);

                        int x = ps2.executeUpdate();

                        if(x>0)
                            System.out.println("Updated Successfully");
                        else
                            System.out.println("Employee Not Found");

                        break;

                    case 4:

                        System.out.print("Enter Employee ID : ");
                        int did = sc.nextInt();

                        PreparedStatement ps3 = con.prepareStatement(
                                "DELETE FROM employee WHERE id=?");

                        ps3.setInt(1,did);

                        int y = ps3.executeUpdate();

                        if(y>0)
                            System.out.println("Deleted Successfully");
                        else
                            System.out.println("Employee Not Found");

                        break;

                    case 5:

                        System.out.println("Thank You");

                        break;

                    default:

                        System.out.println("Invalid Choice");

                }

            }while(choice!=5);

            con.close();

        }
        catch(Exception e){

            e.printStackTrace();

        }

    }

}