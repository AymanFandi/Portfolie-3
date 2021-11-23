package StudentCourse;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.SQLException;

public class Main extends Application {

    public static void main(String[] args) {
        String url = "jdbc:sqlite:C:/Users/45535/IdeaProjects/Portfolie-3/Student-Course.sql";
        CourseModel CM = new CourseModel(url);
        try {
            CM.connect();
            CM.CreateStatement();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                CM.close();
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }

        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws Exception {

        String url = "jdbc:sqlite:C:/Users/45535/IdeaProjects/Portfolie-3/Student-Course.sql";
        CourseModel CM = new CourseModel(url);
        primaryStage.setTitle("Student - Course Finder");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("Course.fxml"));
        Parent root = loader.load();
        CourseController control = loader.getController();
        control.setDbConnection(CM);
        new CourseView(control);
        primaryStage.setScene(new Scene(root, 700, 600));
        primaryStage.show();
    }
}