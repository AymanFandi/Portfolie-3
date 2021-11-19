package StudentCourse;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.util.StringConverter;
import StudentCourse.models.Cbox;
import StudentCourse.models.CourseInformation;
import StudentCourse.models.StudentCourseInformation;
import java.sql.SQLException;
import java.util.ArrayList;

public class CourseController {
    CourseModel model;

    @FXML
    protected Label FullNameStudent;
    @FXML
    protected ComboBox<Cbox> StudentSearch;
    @FXML
    protected ComboBox<Cbox> CourseSearch;
    @FXML
    protected Button ShowCourse;
    @FXML
    protected TableColumn<CourseInformation, SimpleIntegerProperty> CourseID;
    @FXML
    protected Label AVGGradeStudent;
    @FXML
    protected TextField FullName;
    @FXML
    protected TableColumn<CourseInformation, SimpleFloatProperty> CourseAvg;
    @FXML
    protected Pane UserView;
    @FXML
    protected Label FindStudent;
    @FXML
    protected TableView<CourseInformation> CoursesTBL;
    @FXML
    protected TableView<StudentCourseInformation> StudentCoursesTBL;
    @FXML
    protected TableColumn<StudentCourseInformation, SimpleIntegerProperty> StudentCourseID;
    @FXML
    protected TableColumn<StudentCourseInformation, String> StudentGrade;
    @FXML
    protected TableColumn<CourseInformation, SimpleStringProperty> CourseName;
    @FXML
    protected TextField AVGGrade;
    @FXML
    protected Label FindCourse;
    @FXML
    protected TableColumn<StudentCourseInformation, SimpleStringProperty> StudentCourseName;
    @FXML
    protected Button ShowStudent;

    public CourseController() {

    }

    // connect the controller to the database
    public void setDbConnection(CourseModel model) {
        this.model = model;
        try {
            model.connect();
            model.CreateStatement();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    // get the student names from the SQL query
    public ObservableList<Cbox> getStudents() {
        ArrayList<Cbox> FullNames = model.SQLQueryStudentNames();
        return FXCollections.observableList(FullNames);
    }

    public ObservableList<Cbox> getCourses() {
        ArrayList<Cbox> Courses = model.SQLQueryCourses();
        return FXCollections.observableList(Courses);
    }

    public ObservableList<StudentCourseInformation> getStudentInfo(int StudentID) {
        ArrayList<StudentCourseInformation> StudentCourses = model.SQLQueryStudentCourses(StudentID);
        return FXCollections.observableList(StudentCourses);
    }

    public ObservableList<CourseInformation> getCourseInfo(int CourseID) {
        ArrayList<CourseInformation> CourseInformation = model.SQLQueryCourse(CourseID);
        return FXCollections.observableList(CourseInformation);
    }

    public void formatComboInfo(ComboBox<Cbox> comboBox) {
        comboBox.setConverter(new StringConverter<>() {

            @Override
            public String toString(Cbox object) {
                return object.getName();
            }

            @Override
            public Cbox fromString(String string) {
                return comboBox.getItems().stream().filter(ap ->
                        ap.getName().equals(string)).findFirst().orElse(null);
            }
        });
    }

    public float getCourseAverage(int UserID) {
        return model.SQLQueryStudentCoursesAverage(UserID);
    }

    public void updateStudentCourseGrade(TableColumn.CellEditEvent<StudentCourseInformation, String> StudentCourseInformationStringCellEditEvent) {
        int StudentCourseID = StudentCourseInformationStringCellEditEvent.getRowValue().getStudentCourseID();
        model.SQLQueryUpdateStudentCourseGrade(StudentCourseID, Float.parseFloat(StudentCourseInformationStringCellEditEvent.getNewValue()));
        this.ShowStudent.fire();
        this.ShowCourse.fire();
    }
}
