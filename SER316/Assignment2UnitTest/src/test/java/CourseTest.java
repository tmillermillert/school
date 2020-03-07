import main.java.*;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.Before;

import org.junit.Test;

public class CourseTest {
    Course fiveStudentsCurve;
    Course fiveStudentsNoCurve;
    Course zeroStudentsNoCurve;
    Course dropStudentSuccess;
    Course dropStudentFailure;
    Course addStudentSuccess;
    Course addStudentFailure;

    @Before
    public void setUp() throws Exception {
    }
    /*
     * countOccurencesLetterGrades
     * Node/Edge Path
    [199,200,201,202,203,204,205,207,208,209,213,214,
     215,213,214,216,217,213,214,216,218,219,213,214,216,
     218,220,221,213,214,216,218,220,222,223,213,230]
     */
    @Test
    public void noCurvingFiveStudents() {
        // Five Students
        fiveStudentsNoCurve = new Course("SER316");
        fiveStudentsNoCurve.set_points("A", 99);
        fiveStudentsNoCurve.set_points("B", 85);
        fiveStudentsNoCurve.set_points("C", 60);
        fiveStudentsNoCurve.set_points("D", 36);
        fiveStudentsNoCurve.set_points("F", 10);
        Map<String, Integer> ans = fiveStudentsNoCurve.countOccurencesLetterGrades(false);
        assertTrue(ans.get("A") == 1);
        assertTrue(ans.get("B") == 1);
        assertTrue(ans.get("C") == 1);
        assertTrue(ans.get("D") == 1);
        assertTrue(ans.get("F") == 1);
    }
    
    /*
     * countOccurencesLetterGrades
     * Node/Edge Path
    [199,200,201,202,203,204,205,207,226,227,228,227,230]
	*/
    @Test
    public void curvingFiveStudents() {
        // Five Students
        fiveStudentsCurve = new Course("SER316");
        fiveStudentsCurve.set_points("A", 99);
        fiveStudentsCurve.set_points("B", 85);
        fiveStudentsCurve.set_points("C", 60);
        fiveStudentsCurve.set_points("D", 36);
        fiveStudentsCurve.set_points("F", 10);
        Map<String, Integer> ans = fiveStudentsCurve.countOccurencesLetterGrades(true);
        assertTrue(ans.get("A") == 1);
        assertTrue(ans.get("B") == 1);
        assertTrue(ans.get("C") == 1);
        assertTrue(ans.get("D") == 1);
        assertTrue(ans.get("F") == 1);
    }
    
    /*
     * countOccurencesLetterGrades
     * Node/Edge Path
    [199,200,201,202,203,204,205,207,208,209,210]
	*/
    @Test
    public void noCurvingZeroStudents() {
        // Zero Students
    	Map<String, Integer> ans = null;
        zeroStudentsNoCurve = new Course("SER316");
        ans = zeroStudentsNoCurve.countOccurencesLetterGrades(false);
        assertTrue(ans.get("A") == 0);
        assertTrue(ans.get("B") == 0);
        assertTrue(ans.get("C") == 0);
        assertTrue(ans.get("D") == 0);
        assertTrue(ans.get("F") == 0);
    }
    
    /*
     * Test Course.dropStudent
     * Success Case
     */
    @Test
    public void dropStudentTestSuccess() {
    	dropStudentSuccess = new Course("SER316");
    	Student s = new Student("mtmille5",null);
    	dropStudentSuccess.addStudent(s);
    	assertTrue(dropStudentSuccess.dropStudent("mtmille5") == true);
    }
    /*
     * Test Course.dropStudent
     * Failure Case
     */
    @Test
    public void dropStudentTestFailure() {
    	dropStudentFailure = new Course("SER316");
    	assertTrue(dropStudentFailure.dropStudent("mtmille5") == false);
    }
    
    /*
     * Test Course.addStudent
     * Success Case
     */
    @Test
    public void addStudentTestSuccess(){
    	addStudentSuccess = new Course("SER316");
    	Student s = new Student("mtmille5",null);
    	assertTrue(addStudentSuccess.addStudent(s) == true);
    }
    
    /*
     * Test Course.addStudent
     * Failure Case
     */
    @Test
    public void addStudentTestFailure(){
    	addStudentFailure = new Course("SER316");
    	Student s = new Student("mtmille5",null);
    	addStudentFailure.addStudent(s);
    	assertTrue(addStudentFailure.addStudent(s) == false);
    	
    	
    }
}
