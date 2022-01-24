import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.event.ActionEvent;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.util.regex.Pattern;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import java.lang.Math;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.CallableStatement;

public class  AcceptStudentGUI extends Application {

private TextField nameField;
private TextField emailField;
private TextField averageField;
private Text response;

public void start(Stage primaryStage) {
		primaryStage.setTitle("Accept a Student");

		Label nameLabel = new Label ("Name:");
		Label emailLabel = new Label ("Email:");
		Label averageLabel = new Label("Average:");

		nameField = new TextField();
		nameField.setPrefWidth(150);
		nameField.setMaxWidth(300);

		emailField = new TextField();
		emailField.setPrefWidth(150);
		emailField.setMaxWidth(300);

		averageField = new TextField();
		averageField.setPrefWidth(60);
		averageField.setMaxWidth(60);

		Pattern pattern = Pattern.compile("\\d*|\\d+\\,\\d*");
		averageField.textProperty().addListener(new ChangeListener<String>() {
	            @Override
	            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
	                if (!newValue.matches("\\d{0,3}([\\.]\\d{0,2})?")) {
	                    averageField.setText(oldValue);
	                }
	            }
	        });

		Button accept = new Button("Accept");
		accept.setOnAction(this::processAcceptStudent);
		Button quit = new Button("Quit");
    quit.setOnAction(this::quitApp);

		response = new Text("");

		GridPane grid = new GridPane();
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(0, 10, 0, 10));

		// Row 1: Buttons
		grid.add(accept, 0, 0);
		grid.add(quit, 2, 0);
		//Row 2: name
		grid.add(nameLabel, 0, 1);
		grid.add(nameField, 1, 1);
		//Row 3: email
		grid.add(emailLabel, 0, 2);
		grid.add(emailField, 1, 2);
		//Row 4: HS average
		grid.add(averageLabel, 0, 3);
		grid.add(averageField, 1, 3);
		//Row 5: response
		grid.add(response, 0, 4, 2, 4);


		Scene scene = new Scene (grid, 300, 200);

		primaryStage.setScene(scene);
		primaryStage.show();
	}


    public void processAcceptStudent(ActionEvent event)
    {
        //Replace with your code
        String studentName = nameField.getText();
        String studentEmail = emailField.getText();
        String studentAverage =averageField.getText();

        AcceptStudentGUI student = new AcceptStudentGUI();
        Connection conn = student.openConnection();

        try
        {
            String query = "{CALL AcceptStudent(?,?,?)}";
            CallableStatement stmt = conn.prepareCall(query);
            stmt.setString(1, studentName);
            stmt.setString(2, studentEmail);
            stmt.setString(3, studentAverage);

            ResultSet result =stmt.executeQuery();
	        response.setText("Student accepted.");
			response.setFill(Color.GREEN);
        }
        catch(SQLException e)
        {
        	response.setText(e.getMessage());
			response.setFill(Color.RED);
        }
        student.closeConnection(conn);
    }

	public void quitApp(ActionEvent event){
			Platform.exit();
			System.exit(0);
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
