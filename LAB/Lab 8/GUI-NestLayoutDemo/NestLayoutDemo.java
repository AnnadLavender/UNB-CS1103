import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.geometry.Pos;

public class NestLayoutDemo extends Application
{
  public void start(Stage primaryStage)
  {
    primaryStage.setTitle("Nest Layout Demo");
    // Create a pane to hold 4 buttons:
    Button button1 =new Button("Button #1");
    Button button2 =new Button("A Second Button");
    Button button3 =new Button("Another Button");
    Button button4 =new Button("Long-Named Button Four");

    FlowPane topPane = new FlowPane(button1, button2, button3, button4);
    topPane.setAlignment(Pos.CENTER);
    topPane.setHgap(10);
    // ------------------------------------------------------------------------
    // Create a pane to hold 5 Checboxes:
    CheckBox checkBox1 = new CheckBox("Option #1");
    CheckBox checkBox2 = new CheckBox("A Second, Longer Option");
    CheckBox checkBox3 = new CheckBox("Option #3");
    CheckBox checkBox4 = new CheckBox("Option Four");
    CheckBox checkBox5 = new CheckBox("The Last Option");

    GridPane centerPane = new GridPane();
    centerPane.add(checkBox1,0,0); //Column 0, row 0
    centerPane.add(checkBox2,0,1); //Column 0, row 1
    centerPane.add(checkBox3,1,0); //Column 1, row 0
    centerPane.add(checkBox4,1,1); //Column 1, row 1
    centerPane.add(checkBox5,2,0); //Column 2, row 0
    centerPane.setAlignment(Pos.CENTER);
    centerPane.setHgap(20);
    centerPane.setVgap(20);
    // ------------------------------------------------------------------------
    // Putting it all together:
    BorderPane mainPane = new BorderPane();
    mainPane.setTop(topPane);
    mainPane.setCenter(centerPane);

    Scene scene = new Scene(mainPane, 600, 200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }
}
