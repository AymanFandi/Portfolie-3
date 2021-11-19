package StudentCourse.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class StudentCourseInformation {
    SimpleIntegerProperty StudentCourseID;
    SimpleStringProperty StudentCourseName;
    String StudentGrade;

    public StudentCourseInformation(int StudentCourseID, String StudentCourseName, String StudentGrade)
    {
        this.StudentCourseID = new SimpleIntegerProperty(StudentCourseID);
        this.StudentCourseName = new SimpleStringProperty(StudentCourseName);
        this.StudentGrade = StudentGrade;
    }

    // setters and getters for the information on student courses
    public int getStudentCourseID()
    {
        return this.StudentCourseID.get();
    }

    public void setStudentCourseID(int StudentCourseID)
    {
        this.StudentCourseID.set(StudentCourseID);
    }

    public String getStudentCourseName()
    {
        return this.StudentCourseName.get();
    }

    public void setStudentCourseName(String StudentCourseName) { this.StudentCourseName.set(StudentCourseName);}

    public String getStudentGrade()
    {
        return this.StudentGrade;
    }

    public void setStudentGrade(String StudentGrade)
    {
        this.StudentGrade = StudentGrade;
    }
}
