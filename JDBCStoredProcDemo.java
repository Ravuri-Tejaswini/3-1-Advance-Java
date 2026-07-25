import java.sql.*;

public class JDBCStoredProcDemo {

    public static void main(String[] args) {

        // Database connection details
        String url = "jdbc:mysql://localhost:3306/lab3";
        String user = "testuser";
        String password = "Test@12345";

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Create connection
            Connection conn = DriverManager.getConnection(
                    url,
                    user,
                    password
            );

            System.out.println("Connected Successfully.");

            // Call stored procedure get_salary_by_id
            CallableStatement getSalaryStmt =
                    conn.prepareCall("{call get_salary_by_id(?, ?)}");

            // Input Employee ID
            getSalaryStmt.setInt(1, 101);

            // Output parameter
            getSalaryStmt.registerOutParameter(2, Types.DECIMAL);

            // Execute procedure
            getSalaryStmt.execute();

            // Get output salary
            double salary = getSalaryStmt.getDouble(2);

            System.out.println(
                "Salary for Employee ID 101 is: " + salary
            );

            // Close resources
            getSalaryStmt.close();
            conn.close();

        } catch (ClassNotFoundException e) {
            System.out.println("JDBC Driver not found.");
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
