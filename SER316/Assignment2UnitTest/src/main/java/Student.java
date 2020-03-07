package main.java;

import java.util.ArrayList;
import java.util.List;



public class Student {
    private String asurite;
    private Major major;
    private double overall_grade;
    //private List<Course> courses = new ArrayList<Course>();
    private List<Registration> registrations = new ArrayList<Registration>();
    
    public Student(String asurite, Major major){
        this.setAsurite(asurite);
        this.setMajor(major);
        setOverall_grade(0);
    }

    public String getAsurite() {
        return asurite;
    }

    public void setAsurite(String asurite) {
        this.asurite = asurite;
    }

    public Major getMajor() {
        return major;
    }
    
    public boolean register_forCourse(Course course) {
      //course.addStudent(this);
      Registration registration = new Registration(0, this, course);
      return registrations.add(registration);
      //courses.registrations.add(registration);
      //return courses.add(course);
  }

    public void setMajor(Major major) {
        this.major = major;
    }

    public double getOverall_grade() {
        return overall_grade;
    }

    public void setOverall_grade(double overall_grade) {
        this.overall_grade = overall_grade;
    }

    @Override
    public boolean equals(Object other){
        if(other == null) return false;
        if(other == this) return true;
        if(other.getClass() != this.getClass()) return false;
        Student s = (Student) other;
        return this.getAsurite().equals(s.getAsurite());

    }
    
    @Override
    public int hashCode() {
        return this.asurite.hashCode();
    }

}
