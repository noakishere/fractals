//Author Kamyar


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.canvas.Canvas;
import javafx.scene.Group;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;



public class FractalControllerAndView   extends Application
{
   private static int canvasWidth = 900;
   private static int canvasHeight = 700;
   private static int inputOutputPanelWidth = 330;


   private static Color[] shapeColors =
   {
      Color.SLATEGRAY, Color.CHOCOLATE, Color.BROWN, Color.GREEN,
      Color.VIOLET, Color.RED, Color.CYAN, Color.GOLD, Color.PLUM
   };
   // Create a BorderPane as the root node in the scene
   private BorderPane root = new BorderPane();

   // Create a canvas for drawing
   private Canvas canvas = new Canvas(canvasWidth , canvasHeight);
   // create a Graphics Context for use by draw methods
   private final GraphicsContext gc = canvas.getGraphicsContext2D();

  // Create the Scene
   private Scene scene = new Scene(root, canvasWidth + inputOutputPanelWidth, canvasHeight);


   // Create a TextField for the user to enter input
   private TextField inputTxt = new TextField();
   // Create the Label for the input text field
   private Label inputLbl = new Label("Input area:");

   // Create a TextField for the user to enter a recall number
   private TextField ceilingTxt = new TextField();
   // Create the Label for the recall text field
   private Label ceilingLbl = new Label("Ceiling Number");

   // Create a TextArea for the textual Output
   private TextArea output = new TextArea();
   // Create the Label for the output text area
   private Label outputLbl = new Label("Output area:");

   // create a Random object
   private static Random rand = new Random();

   @Override
   public void start(Stage stage)
   {
//      stage.setMaximized(true);
      output.setFont(Font.font("Courier new", 12));
      output.setEditable(false);
      output.setWrapText(true);
      output.setPrefWidth(300);

      ceilingTxt.setPrefWidth(75);
      inputTxt.setPrefWidth(225);

      // Create the clear-Button
      Button clearBtn = new Button("Clear");
      clearBtn.setPrefWidth(320);

      ///////////////////////////////////////////////////////////////
      // Register an EventHandler object with the source of the event

      // register an EventHandler object with the clear button object
      clearBtn.setOnAction((event) -> processClearButtonClick(event));
      // 1 ***** Do not code the method clearInputOutputCanvas() above
      // The code for the method clearInputOutputCanvas() has been provided

      // Note: both text fields use the same event handler
      // register an EventHandler object with the input text field object
      // register an EventHandler object with the ceiling text field object
      inputTxt.setOnAction((event) -> processUserInput());
      ceilingTxt.setOnAction((event) -> processUserInput());
      // 2 ***** Define the method processUserInput() above

      // End of event handler registration
      //////////////////////////////////////////////////////////////

      // set up visual layouts of the controls
      VBox.setVgrow(inputTxt, Priority.ALWAYS);
      // stack the input label and text field into a VBox
      VBox inputVBox = new VBox(inputLbl, inputTxt);

      VBox.setVgrow(ceilingTxt, Priority.ALWAYS);
      // stack the ceiling label and text field into another VBox
      VBox ceilingVBox = new VBox(ceilingLbl, ceilingTxt);

      HBox.setHgrow(inputVBox, Priority.ALWAYS);
      // place the inputVBox and ceilingVBox side by side in an HBox
      HBox inputAndCeilingHBox = new HBox(inputVBox, ceilingVBox);

      VBox.setVgrow(output, Priority.ALWAYS);
      VBox cmdBox = new VBox(outputLbl, output, inputAndCeilingHBox, clearBtn);

      root.setLeft(cmdBox); // place cmdBox in the left panel
      root.setCenter(canvas); // place canvas int the center panel
      clearInputOutputCanvas(); // clear text fields, text area, canvas

      // Set the title of the Stage
      stage.setTitle("Drawing Fractals");
      stage.setScene(scene); // Add the scene to the Stage
      stage.setResizable(false); // disable stage's resizing property
      stage.show(); // Display the Stage
   }

   private void processClearButtonClick(ActionEvent e)
   {
      clearInputOutputCanvas();

   }
   public void clearInputOutputCanvas()
   {
      int size = Math.max((int) canvas.getWidth(), (int) canvas.getHeight());
//      Figure f = new Square(0, 0, Color.WHITE, size);
        gc.setFill(Color.LIGHTYELLOW);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);

        gc.fillRect(0,0,size,size);

      output.clear();
      inputTxt.clear();
      inputTxt.setText("E");
      ceilingTxt.clear();
      ceilingTxt.setText("0");
      output.appendText(
               "Welcome to my fractal application\n"
              + "--------------------------------"
              + "\n"
              + "Input format:\n"
              + "Enter one of the following letters\n"
              + "  n, N for direction north or \n"
              + "  e, E for direction east, or\n"
              + "  s, S for direction south, or\n"
              + "  w, W for direction west, or\n"
              + "       Default direction is east\n"
              + "\n"
              + "Ceiling number format\n"
              + "Enter a non-negative integer\n"
              + "Invalid input sets ceiling to zero\n"
              + "enjoy!\n"
              + "--------------------------------"
      );
   }

   private void processUserInput() {

       String userDirection = inputTxt.getText();
       String userCeiling = ceilingTxt.getText();

      
       output.appendText("\n-> " + userDirection );
       inputTxt.clear();
       if( userDirection.isEmpty() )
       {
           output.setText("\n" + userDirection + "missing Input - nothing to do!" + "\n please try again!! ");
           return;
       }
    if( userCeiling.isEmpty())
    {
        output.setText("\n" + userCeiling + " Missing a direction ! " +"\n" + " click clear to restar!");
        return;
    }
       
       output.appendText(" -> Direction: " + userDirection.toUpperCase() + 
         " Ceiling: " + userCeiling + "\n");

       Scanner directScan = new Scanner(userDirection);

       char direction = directScan.next().trim().toLowerCase().charAt(0);
       
       Scanner ceilingScan = new Scanner(userCeiling);
       
       int ceiling = ceilingScan.nextInt();
       
       int width = (int) canvas.getWidth();//width
       int height = (int) canvas.getHeight();//height

       Fractal fra = new Fractal(width, height, direction, ceiling);
       fra.drawFractal(gc);
       output.appendText(fra.toString());
        
   }
   public static void main(String[] args)
   {
      Application.launch(args);
   }
}
