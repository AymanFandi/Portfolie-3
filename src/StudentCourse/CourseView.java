package StudentCourse;

import javafx.collections.ObservableList;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import StudentCourse.models.Cbox;
import StudentCourse.models.CourseInformation;
import StudentCourse.models.StudentCourseInformation;

public class CourseView {

    StudentCourse.CourseController control;

    public CourseView(CourseController control) {

        this.control = control;

        CreateAndConfigure();
    }


    public void CreateAndConfigure() {
        this.fillStudentsCbox();
        this.fillCoursesCbox();
        this.initStudentCoursesTableColumns();
        this.setFindStudentButtonAction();
        this.initCoursesTableColumns();
        this.setFindCourseButtonAction();
    }


    public void fillStudentsCbox()
    {
        ObservableList<Cbox> StudentNames = this.control.getStudents();
        this.control.StudentSearch.setItems(StudentNames);
        this.control.StudentSearch.getSelectionModel().selectFirst();
        this.control.StudentSearch.setStyle("-fx-font: 13px \"Times New Roman\";");
        this.control.formatComboInfo(this.control.StudentSearch);
        System.out.println(this.control.StudentSearch.getValue().getID());
    }


    public void fillCoursesCbox()
    {
        ObservableList<Cbox> CourseTitle = this.control.getCourses();
        this.control.CourseSearch.setItems(CourseTitle);
        this.control.CourseSearch.getSelectionModel().selectFirst();
        this.control.CourseSearch.setStyle("-fx-font: 13px \"Times New Roman\";");
        this.control.formatComboInfo(this.control.CourseSearch);
        System.out.println(this.control.CourseSearch.getValue().getID());
    }


    public void setFindStudentButtonAction()
    {
        this.control.ShowStudent.setOnAction(actionEvent -> {
            ObservableList<StudentCourseInformation> data = control.getStudentInfo(control.StudentSearch.getValue().getID());
            control.FullName.setText(control.StudentSearch.getValue().getName());
            control.AVGGrade.setText(String.valueOf(control.getCourseAverage(control.StudentSearch.getValue().getID())));
            control.StudentCoursesTBL.setItems(data);
        });
    }


    public void setFindCourseButtonAction()
    {
        this.control.ShowCourse.setOnAction(actionEvent -> {
            ObservableList<CourseInformation> data = control.getCourseInfo(control.CourseSearch.getValue().getID());
            control.CoursesTBL.setItems(data);
            System.out.println(control.CourseSearch.getValue().getID());
        });
    }


    public void initStudentCoursesTableColumns()
    {
        this.control.StudentCourseID.setCellValueFactory(new PropertyValueFactory<>("StudentCourseID"));
        this.control.StudentCourseName.setCellValueFactory(new PropertyValueFactory<>("StudentCourseName"));
        this.control.StudentGrade.setCellValueFactory(new PropertyValueFactory<>("StudentGrade"));
        this.control.StudentGrade.setCellFactory(TextFieldTableCell.forTableColumn());
        this.control.StudentCoursesTBL.setEditable(true);
        this.control.StudentGrade.setEditable(true);
    }


    public void initCoursesTableColumns()
    {
        this.control.CourseID.setCellValueFactory(new PropertyValueFactory<>("CourseID"));
        this.control.CourseName.setCellValueFactory(new PropertyValueFactory<>("CourseName"));
        this.control.CourseAvg.setCellValueFactory(new PropertyValueFactory<>("CourseAvg"));
    }
}
