import java.sql.*;

class UResultSet {

    public static void main(String[] args) throws Exception {

        String url = "jdbc:mysql://localhost:3306/testdb";
        String user = "testuser";
        String password = "Test@12345";

        try {

            // Load MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to MySQL database
            Connection con = DriverManager.getConnection(url, user, password);

            // Create scrollable and updatable ResultSet
            Statement st = con.createStatement(
                    ResultSet.TYPE_SCROLL_SENSITIVE,
                    ResultSet.CONCUR_UPDATABLE
            );

            // Execute query to fetch student records
            ResultSet rs = st.executeQuery("SELECT * FROM student");


            // -------------------------
            // DELETE the last row
            // -------------------------

            rs.last();              // Move cursor to last record
            rs.deleteRow();         // Delete current row

            System.out.println("Last student record deleted successfully.");


            // -------------------------
            // INSERT a new row
            // -------------------------

            rs.moveToInsertRow();   // Move cursor to insert row

            rs.updateInt("RollNo", 105);
            rs.updateString("Name", "John Doe");
            rs.updateString("Address", "Hyderabad");

            rs.insertRow();         // Insert new record

            System.out.println("New student record inserted successfully.");


            // Close connection
            con.close();

        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
