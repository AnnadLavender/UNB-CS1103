import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

class StudentTranscript
{
  public static void main(String[] args)
  {
    if(args.length < 1)
    {
      System.err.println("Usage: StudentTranscript <studentId>");
    }
    String studentId = args[0];

    StudentTranscript student = new StudentTranscript();
    Connection conn = student.openConnection();

    try
    {
      String query = "{CALL studentTranscript(?)}";
      CallableStatement stmt = conn.prepareCall(query);
      stmt.setString(1,studentId);

      ResultSet result =stmt.executeQuery();
	    System.out.println("courseId\tcourseNum\tcourseName\tletterGrade");

      while(result.next())
      {
        String row = result.getInt(1) + "\t"+result.getString(2) + "\t"+result.getString(3) + "\t"+result.getString(4);
        System.out.println(row);
      }
      System.out.println();
    }
    catch(SQLException e)
    {
      System.err.println(e.getMessage());
      student.closeConnection(conn);
    }
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
