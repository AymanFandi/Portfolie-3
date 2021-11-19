package StudentCourse.models;

import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CourseInformation {
    SimpleIntegerProperty CourseID;
    SimpleStringProperty CourseName;
    SimpleFloatProperty CourseAvg;

    public CourseInformation(int CourseID, String CourseName, float CourseAvg)
    {
        this.CourseID = new SimpleIntegerProperty(CourseID);
        this.CourseName = new SimpleStringProperty(CourseName);
        this.CourseAvg = new SimpleFloatProperty(CourseAvg);
    }

    // setters and getters for the information about the course

    public int getCourseID()
    {
        return this.CourseID.get();
    }

    public void setCourseID(int StudentCourseID)
    {
        this.CourseID.set(StudentCourseID);
    }

    public String getCourseName()
    {
        return this.CourseName.get();
    }

    public void setCourseName(String StudentCourseName)
    {
        this.CourseName.set(StudentCourseName);
    }

    public float getCourseAvg()
    {
        return this.CourseAvg.get();
    }

    public void setCourseAvg(float StudentGrade)
    {
        this.CourseAvg.set(StudentGrade);
    }
}
