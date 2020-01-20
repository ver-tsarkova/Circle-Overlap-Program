/**
* CircleOverlap.java
* A JavaFX program that presents 20 circles (use the JavaFX Circle class), each with a random radius and location.
* If a circle does not overlap any other circle, fill the circle with color orange. Fill overlapping circles with
* a translucent blue (alpha value of 0.3).
*/
import java.util.Random;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class CircleOverlap extends Application
{
  @Override
  public void start(Stage primaryStage)
  {
    // Creating a Random number generator object.
    Random random = new Random();
    // Setting the size of the window.
    int windowWidth = 500;
    int windowHeight = 500;
    // Creates an array of Circle objects.
    Circle[] array = new Circle[20];

    for (int i = 0; i < array.length; i++)
    {
      // Generating a value between 10 and 50 for radius.
      int radius = random.nextInt(41) + 10;
      // Generating a random x, y coordinates, ensuring that the
      // circle fits within the window.
      int centerX = random.nextInt(windowWidth - 2 * radius) + radius;
      int centerY = random.nextInt(windowHeight - 2 * radius) + radius;
      // Creating Circle object.
      Circle circle = new Circle();
      circle.setCenterX(centerX);
      circle.setCenterY(centerY);
      circle.setRadius(radius);

      array[i] = circle;
      // Flag to check if circle is overlapping any previous circle.
      boolean isOverlapping = false;

      for (int j = 0; j < i; j++)
      {
        // Finding x, y and radius of current circle under check.
        double x2 = array[j].getCenterX();
        double dx = x2 - centerX;
        double y2 = array[j].getCenterY();
        double dy = y2 - centerY;
        double r2 = array[j].getRadius();
        // Finding distance between this circle and the circle under check.
        double distance = Math.sqrt((dx * dx) + (dy * dy));

        if (distance <= (radius + r2))
        {
          // Overlapping, setting transclucent blue color.
          Paint c = new Color(0, 0, 1.0, 0.3);
          array[i].setFill(c);
          isOverlapping = true;

          // Changing the color of other circles.
          array[j].setFill(c);

        }
      }
      if (!isOverlapping)
      {
        // Not overlapping, setting orange color.
        array[i].setFill(Color.ORANGE);
      }
    }
    // Creating scene with all the circles.
    Pane root = new Pane();
    root.getChildren().addAll(array);
    Scene scene = new Scene(root, windowWidth, windowHeight);
    primaryStage.setScene(scene);
    primaryStage.setTitle("");
    primaryStage.show();
  }
  public static void main(String[] args)
  {
    launch(args);
  }
}
