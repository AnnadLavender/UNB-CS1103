import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class StudentTranscriptGUI extends Application
{
  private TextField idField;
  private Button show;
  private Button exit;
  private Text response;

  public void start(Stage primaryStage)
  {
    // Opening
    primaryStage.setTitle("Student Transcript");
    // Labels
    Label idLabel = new Label("Student ID:");
    // Text Field
    idField = new TextField();
    idField.setPrefWidth(200);
    // Button
    show = new Button("show");
    show.setOnAction(this::processButton);
    exit = new Button("exit");
    exit.setOnAction(this::processButton);
    // Text
    response = new Text("Input Student ID to show the transcript.");

    GridPane grid1 = new GridPane();
    grid1.setHgap(10);
    grid1.setVgap(10);
    grid1.setPadding(new Insets(10, 10, 10, 10));
    grid1.add(idLabel,0,0);
    grid1.add(idField,1,0);
    grid1.setAlignment(Pos.CENTER);

    GridPane grid2 = new GridPane();
    grid2.setHgap(40);
    grid2.setPadding(new Insets(10, 10, 10, 10));
    grid2.add(show,0,0);
    grid2.add(exit,1,0);
    grid2.setAlignment(Pos.CENTER);

    GridPane main = new GridPane();
    main.setPadding(new Insets(40, 10, 10, 10));
    main.add(grid1,0,0);
    main.add(grid2,0,1);
    main.add(response,0,3);
    main.setAlignment(Pos.CENTER);
    main.setVgap(10);

    // root.setPrefSize(500,200);
    ScrollPane root = new ScrollPane();
    root.setContent(main);
    root.setPannable(true);
    // root.main.setAlignment(Pos.CENTER);

    Scene scene = new Scene(root,350,350);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  public void processButton(ActionEvent event)
  {
    String studentID = idField.getText();
    if(event.getSource()==show)
    {
      StudentTranscriptGUI student = new StudentTranscriptGUI();
      Connection conn = student.openConnection();

      try
      {
        String query = "{CALL studentTranscript(?)}";
        CallableStatement stmt = conn.prepareCall(query);
        stmt.setString(1,studentID);

        ResultSet result =stmt.executeQuery();
  	    // System.out.println("courseId\tcourseNum\tcourseName\tletterGrade");
        String title = "Transcript Detail:";
        String output = "";
        while(result.next())
        {
          String course = "\nCourse ID:\t" + result.getInt(1) +
          "\nCourse Number:\t" + result.getString(2) +
          "\nCourse Name:\t" + result.getString(3) +
          "\nLetter Grade:\t" + result.getString(4) + "\n";
          output+=course;
        }
        response.setText(title + output);
        response.setFill(Color.BLACK);
      }
      catch(SQLException e)
      {
        response.setText(e.getMessage());
        response.setFill(Color.RED);
      }
      student.closeConnection(conn);
    }
    else if(event.getSource()==exit)
    {
      System.exit(1);
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
