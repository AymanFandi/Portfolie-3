package StudentCourse;
import StudentCourse.models.Cbox;
import StudentCourse.models.CourseInformation;
import StudentCourse.models.StudentCourseInformation;
import java.sql.*;
import java.util.ArrayList;
import static java.sql.DriverManager.getConnection;


public class CourseModel {

    Connection connection = null;
    String url;
    Statement stmt = null;
    ResultSet rs = null;

    CourseModel(String url) {
        this.url = url;
    }


    public void connect() throws SQLException {
        connection = getConnection(url);
    }


    public void close() throws SQLException {
        if (connection != null)
            connection.close();
        try {
            String url = "jdbc:sqlite:C:/Users/45535/IdeaProjects/Portfolie-3/Student-Course.sql";
            connection = getConnection(url);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        }
    }

    public void CreateStatement() throws SQLException {
        this.stmt = connection.createStatement();
    }


    public ArrayList<Cbox> SQLQueryStudentNames() {
        ArrayList<Cbox> StudentNames = new ArrayList<>();
        String sql = """
                Select
                       UserID,        case when Last_Name is not null
                           then (first_name || ' ' || Last_Name)
                           else First_Name
                           end as Full_Name
                From
                     user where User_TypeID is not 2;""";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {

                int id = Integer.parseInt(rs.getString(1));
                String FullName = rs.getString(2);

                StudentNames.add(new Cbox(id, FullName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return StudentNames;
    }


    public ArrayList<Cbox> SQLQueryCourses() {
        ArrayList<Cbox> Courses = new ArrayList<>();
        String sql = "Select CourseID, c.Course_Name\n" +
                "    From Courses as c;";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                int ID = Integer.parseInt(rs.getString(1));
                String CourseName = rs.getString(2);
                Courses.add(new Cbox(ID, CourseName));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Courses;
    }


    public ArrayList<CourseInformation> SQLQueryCourse(int CourseID) {
        ArrayList<CourseInformation> Courses = new ArrayList<>();
        String sql = "Select sc.CourseID, c.Course_Name , CASE WHEN avg(sc.grade) IS NULL THEN 0 ELSE avg(sc.grade) END as average\n" +
                "    From Courses as c, Student_Courses as sc\n" +
                "WHERE (sc.CourseID) = " + CourseID + ";";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                int courseID = Integer.parseInt(rs.getString(1));
                System.out.println(courseID);
                String CourseTitle = rs.getString(2);
                float CourseAvg = Float.parseFloat(rs.getString(3));
                Courses.add(new CourseInformation(courseID, CourseTitle, CourseAvg));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return Courses;
    }


    public ArrayList<StudentCourseInformation> SQLQueryStudentCourses(int StudentID) {
        ArrayList<StudentCourseInformation> courses = new ArrayList<>();
        String sql = "SELECT\n" +
                "    sc.Student_CourseID,\n" +
                "    c.Course_Name,\n" +
                "    sc.grade\n" +
                "FROM\n" +
                "    user as u\n" +
                "INNER JOIN\n" +
                "    Student_Courses as sc on u.UserID = sc.UserID\n" +
                "INNER JOIN\n" +
                "    Courses as c on sc.CourseID = c.CourseID\n" +
                "WHERE u.UserID = '" + StudentID + "'";
        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                int StudentCourseID = Integer.parseInt(rs.getString(1));
                String CourseName = rs.getString(2);
                String CourseGrade = rs.getString(3);
                System.out.println(StudentCourseID);
                courses.add(new StudentCourseInformation(StudentCourseID, CourseName, CourseGrade));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return courses;
    }


    public float SQLQueryStudentCoursesAverage(int UserId) {
        String sql = "SELECT\n" +
                "    CASE WHEN avg(sc.grade) IS NULL THEN 0 ELSE avg(sc.grade) END as 'Average'\n" +
                "FROM\n" +
                "    user as u\n" +
                "INNER JOIN\n" +
                "    Student_Courses as sc on u.UserID = sc.UserID\n" +
                "WHERE u.UserID = " + UserId + "\n";
        float average = 0;

        try {
            rs = stmt.executeQuery(sql);
            while (rs != null && rs.next()) {
                average = Float.parseFloat(rs.getString(1));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return average;
    }

    public void SQLQueryUpdateStudentCourseGrade(int StudentCourseID, float newGrade)
    {
        String sql = "UPDATE Student_Courses\n" +
                "    SET grade = " + newGrade + "\n" +
                "WHERE Student_CourseID = "+ StudentCourseID +"\n";

        try{
            rs = stmt.executeQuery(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}