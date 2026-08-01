import java.sql.*;

public class SResultSet {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@12345";

        try {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create a scrollable, read-only ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY
            );

            // Execute query on Student table
            ResultSet rs = st.executeQuery("SELECT * FROM student");


            System.out.println("Records in Forward Direction:");
            System.out.println("RollNo\tName\t\tAddress");
            System.out.println("--------------------------------");


            while (rs.next()) {

                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t\t" +
                        rs.getString("Address")+ "\t"
                );
            }


            System.out.println("\nRecords in Backward Direction:");
            System.out.println("RollNo\tName\t\tAddress");
            System.out.println("--------------------------------");


            while (rs.previous()) {

                System.out.println(
                        rs.getInt("RollNo") + "\t" +
                        rs.getString("Name") + "\t\t" +
                        rs.getString("Address")
                );
            }


            // Move cursor to first record
            rs.first();

            System.out.println("\nFirst Record:");
            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
            );


            // Move cursor to last record
            rs.last();

            System.out.println("\nLast Record:");
            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
            );


            // Move cursor one row backward from last record
            rs.relative(-1);

            System.out.println("\n2nd Record from Last:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
            );


            // Move cursor to second record
            rs.absolute(2);

            System.out.println("\n2nd Record from Beginning:");

            System.out.println(
                    "Row " + rs.getRow() + ": " +
                    rs.getInt("RollNo") + "\t" +
                    rs.getString("Name") + "\t\t" +
                    rs.getString("Address")
            );


            // Close connection
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
