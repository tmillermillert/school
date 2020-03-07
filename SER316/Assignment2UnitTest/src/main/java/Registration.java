package main.java;


/**
 * The Registration class is used to store student registration information to a course.
 * @author Marcus Miller
 * 
 *
 */
public class Registration {

    private int points;
    private Student student;
    private Course course;

    public Registration(int points, Student student, Course course) {
        this.points = points;
        this.student = student;
        this.course = course;
    }

    /**
     * @return the points
     */
    public int getPoints() {
        return points;
    }

    /**
     * @param points the points to set
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return the student
     */
    public Student getStudent() {
        return student;
    }

    /**
     * @param student the student to set
     */
    public void setStudent(Student student) {
        this.student = student;
    }

    /**
     * @return the course
     */
    public Course getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Course course) {
        this.course = course;
    }
    
    

}






