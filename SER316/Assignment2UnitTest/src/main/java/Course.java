package main.java;

/**
 * class for managing course statistics
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Course {
    public String AVERAGE_GRADES_NO_MAX_OR_MIN_MSG = "Average Grades without max and without min: ";
    //public Map<String, Integer> points = new HashMap<>(); // maps student names (asurite) to their points
    private String _name; // course name
    private int maxPoints;
    public List<Registration> registrations = new ArrayList<Registration>();


    public Course(String name) {
        this(name, 100);

    }

    public Course(String name, int maxPoints) {
        this.setName(name);
        this.maxPoints = maxPoints;
    }

    public String getName() {
        return _name;
    }

    public void setName(String name) {
        this._name = name;
    }


    public void printCourseStats() {
        System.out.printf("%s%d%n", this.AVERAGE_GRADES_NO_MAX_OR_MIN_MSG, 
        		this.calculateAverageWithoutMinWithoutMax());
    }


    public double calculateAverageWithoutMinWithoutMax() throws NullPointerException {
        List<Integer> collection = new ArrayList<Integer>();
        for (Registration registration: registrations) {
            collection.add(registration.getPoints());
        }
        

        int counter = 0;
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        if(collection.size() == 1)
            return collection.get(0);

        else if(collection.size() == 2 ){
            return (double)(collection.get(0) + collection.get(1))/2;
        }
        else {
            int allPoints = 0;
            for(int point: collection){
                if (point >= 0) {

                    counter++;
                    if (point < min){
                        min = point;
                    }
                    if (point > max){
                        max = point;
                    }
                    allPoints = allPoints + point;
                }
            }

            int totalPoints = allPoints-max-min;
            return totalPoints/(double)(counter-2);

        }
    }

    // REACH at least 95% Code Coverage (assign 3)
    // drop a student from course.
    public boolean dropStudent(String asurite) {
        for (Registration registration: registrations) {
            if(asurite.equals(registration.getStudent().getAsurite())){
                return registrations.remove(registration);
            }
        }
        return false;
        //boolean removeFromPoints = points.remove(asurite) != null;
        //boolean removeFromStudents = students.remove(new Student(asurite, null));

        /*SER316-start*/
        //return removeFromPoints && removeFromStudents;
        //return removeFromPoints == removeFromStudents;
        //We want both points and the student to be removed. The test case above
        //fails if neither were removed. 
        /*SER316-start*/
    }

    // REACH at least 95% Code coverage  (assign 3)
    // Students should only be added when they are not yet in the course (names (asurite member) needs to be unique)
    //List<Student> students  = new ArrayList<Student>();
    public boolean addStudent(Student s) {
        for (Registration registration: registrations) {
            if(registration.getStudent().getAsurite().equals(s.getAsurite())) {
                return false;
            }
        }
        Registration registration = new Registration(0, s, this);
        return registrations.add(registration);
        //if(students != null && points.putIfAbsent(s.getAsurite(), -1) == null){
        	/*SER316-start*/
            //students.add(s); removed so we don't add a student twice 
            /*SER316-end*/
            //return students.add(s);
            
        //}
        //return false;
    }

    public void set_points(String name, int points) {
        for(Registration registration: registrations) {
            if(registration.getStudent().getAsurite().equals(name)) {
                registration.setPoints(points);
                return;
            }
        }
        Registration registration = new Registration(points, new Student(name, null), this);
        registrations.add(registration);
       
        
        //if(!this.points.containsKey(name))
          //  addStudent(new Student(name, null));
        //this.points.put(name, points);
    }

    public List<Registration> getPoints(){
        return registrations;
        //return points;
    }

    public int getStudent_Points(String student) {
        for(Registration registration: registrations) {
            if (registration.getStudent().getAsurite().equals(student)) {
                return registration.getPoints();
            }
        }
        return 0;
        //return points.get(student);
    }

    public int getStudent_Points(Student student) {
        for(Registration registration: registrations) {
            if(registration.getStudent() == student) {
                return registration.getPoints();
            }
        }
        return 0;
        //return points.get(student.getAsurite());
    }

    public int getMaxPoints() {
        return maxPoints;
    }

    public void setMaxPoints(int maxPoints) {
        this.maxPoints = maxPoints;
    }


    public List<Student> getStudents(){
        List<Student> students = new ArrayList<Student>();
        for(Registration registration: registrations) {
            students.add(registration.getStudent());
        }
        return students;
    }

    public List<Double> calculatePercentiles(List<Integer> collection) throws NullPointerException{
        if (collection == null) {
            throw new NullPointerException();
        }


        int maxMarks = calculateMax();
        System.out.println("Test: " + maxMarks);
        double eachPercentile = 0.0;
        List<Double> percentileList = new ArrayList<Double>();

        for (int element : collection) {

            if (element > 0) {

                eachPercentile = (double)(element / maxMarks  * 100);
                percentileList.add(eachPercentile);
            }
        }
        System.out.println(percentileList);
        return percentileList;
    }

    public int calculateMax() throws NullPointerException {
        List<Integer> collection = new ArrayList<Integer>();
        for (Registration registration: registrations) {
            collection.add(registration.getPoints());
        }

        if(collection.size() == 1) {
            return -1;
        }

        int max = Integer.MIN_VALUE;

        for(int point: collection) {
            if (point >= 0) {
                if (point > max) {
                    max = point;
                }
            }
        }

        return max;
    }

    
    /**
     * This is where you create your node flow graph and write your White Box test. 
     * Calculates final grades either with curve or without  (assign 3)
     * <p>
     * Calculation is based on points member and maxPoints from Course.
     * Will call curve if input is true. 
     *  * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * 
     * @input curved if true curving is done if not curving is ommitted
     * 
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException
     */
    public Map<String, Integer> countOccurencesLetterGrades(boolean curved){
        HashMap<String, Integer> occur = new HashMap<String, Integer>();
        occur.put("A", 0);
        occur.put("B", 0);
        occur.put("C", 0);
        occur.put("D", 0);
        occur.put("F", 0);

        if(!curved) {
            List<Integer> collection = new ArrayList<Integer>();
            for (Registration registration: registrations) {
                collection.add(registration.getPoints());
            }
            if (collection.isEmpty()) {
                return occur;
            }

            for (double value : collection) {
                if ((double)value/maxPoints*100 > 89.0) {
                    occur.put("A", occur.get("A") + 1);
                } else if ((double)value/maxPoints * 100 > /*SER316-start*/ 79.0 /*80.0 && value/maxPoints <= 89.0*//*SER316-end*/) {//corrected boundaries
                    occur.put("B", occur.get("B") + 1);
                } else if ((double)value/maxPoints * 100 > /*SER316-start*/ 59.0/*50.0 && value/maxPoints <= 65*//*SER316-end*/) { //corrected boundaries
                    occur.put("C", occur.get("C") + 1);
                } else if ((double)value/maxPoints*100 > 35.0 /*SER316-start*//*&& value/maxPoints <= 50.0*//*SER316-end*/) { //corrected boundaries
                    occur.put("D", occur.get("D") + 1);
                } else {
                    occur.put("F", occur.get("F") + 1);
                }
            }
        } else {
            for(String grade : curveLetterGrades().values())
                occur.put(grade, occur.get(/*SER316-start*/grade/*occur*//*SER316-end*/) + 1);//get should take a String as argument not an ArrayList
        }
        return occur;

    }

    /** This will be needed for assignment 3 (do not change in assignment 2)
     * Calculates final grades including a curve and returns final letter grade
     * of each student.
     * <p>
     * Calculation is based on points member inherited from Course.
     * Curve is calculated by adding the positive difference between the student
     * with the highest non-negative points and maxPoints to all scores.
     * Grading Scale:
     * >  89% -> A
     * >  79% -> B
     * >  59% -> C
     * >  35% -> D
     * <= 35% -> F
     * <p>
     * eg.let points = [Alice:15, Bill:30, Cathy:45, Joe:70, Jane:80] and maxPoints = 100,
     * curve would be 100 - 80 = 20.
     * Adjusted points would be = [Alice:35, Bill:50, Cathy:65, Joe:90, Jane:100].
     * Adjusted percentages would be = [35%, 50%, 65%, 90%, 100%].
     * Returned HashMap points would be = [Alice:F, Bill:D, Cathy:C, Joe:A, Jane:A].
     *
     * @return hashmap with final letter grades for students based on curving `points`.
     * @throws NullPointerException
     */
    public Map<String, String> curveLetterGrades() throws NullPointerException { //TODO verify no side effect with points.
    	int curve = 0;
    	int OUTOF = 100;
    	int maxPoints = Integer.MIN_VALUE;
    	int A = 89, B = 79, C = 59, D = 35;
        Map<String, Integer> pointsWithCurve = new HashMap<String, Integer>();
    	//Determine curve 	
    	for (Registration registration: registrations) {
    	    pointsWithCurve.put(registration.getStudent().getAsurite(), registration.getPoints());
    		if (registration.getPoints() >= 0 && registration.getPoints() > maxPoints) {
    			maxPoints = registration.getPoints();
    			if (maxPoints >= OUTOF) {
    				curve = 0;
    			}
    			else {
    				curve = OUTOF - maxPoints;
    			}
    		}
    	}
    	//clone points and add curve
    	//convert curvedPoints into <string,string> hashMap
    	Map<String, String> gradesWithCurveString = new HashMap<String, String>();
    	for (Map.Entry<String,Integer> entry : pointsWithCurve.entrySet()) {
    	    pointsWithCurve.put(entry.getKey(), entry.getValue() + curve);
    		if (entry.getValue() > A) {
    			gradesWithCurveString.put(entry.getKey(), "A");
    		}
    		else if (entry.getValue() > B) {
    			gradesWithCurveString.put(entry.getKey(), "B");
    		}
    		else if (entry.getValue() > C) {
    			gradesWithCurveString.put(entry.getKey(), "C");
    		}
    		else if (entry.getValue() > D) {
    			gradesWithCurveString.put(entry.getKey(), "D");
    		}
    		else {
    			gradesWithCurveString.put(entry.getKey(), "F");
    		}
    	}
    	return gradesWithCurveString;
    }
}