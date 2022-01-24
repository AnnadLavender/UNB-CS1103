import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

class AcceptStudent
{
    public static void main(String[] args)
    {
        if(args.length<1)
        {
		System.err.println("Usage: AcceptStudent <studentName> <studentEmail> <studentAverage>");
        }
        String studentName = args[0];
        String studentEmail = args[1];
        String studentAverage =args[2];

        AcceptStudent student = new AcceptStudent();
        Connection conn = student.openConnection();

        try
        {
            String query = "{CALL AcceptStudent(?,?,?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, studentName);
            stmt.setString(2, studentEmail);
            stmt.setString(3, studentAverage);

            ResultSet result =stmt.executeQuery();
	    System.out.println("Succesfully added");

        }
        catch(SQLException e)
        {
            System.err.println(e.getMessage());
        }
        student.closeConnection(conn);
    }

    private Connection openConnection()
    {
        // openConnection method
        // OBVIOUSLY you will fill in your own info in place of "<>" values, right?
        final String url = "jdbc:mysql://cs1103.cs.unb.ca:3306/anguyen5";
        final String user = "anguyen5";
        final String password = "tKSHQP58";
        Connection conn = null;
        try
        {
            conn = DriverManager.getConnection(url, user, password);
        }
        catch (Exception e)
        {
            System.err.printf("Couldn't open a connection: (%s)", e.getMessage());
        }
        return conn;
    }

    private void closeConnection(Connection conn)
    {
        // closeConnection method
        try
        {
            conn.close();
        }
        catch (Exception e)
        {
            System.err.printf("Couldn't close connection: (%s)", e.getMessage());
        }
    }
}
