	import main.java.*;
	import org.junit.After;
	import org.junit.Before;
	import org.junit.Test;
	import org.junit.runner.RunWith;
	import org.junit.runners.Parameterized;
	import org.junit.runners.Parameterized.Parameters;

	import java.lang.reflect.Constructor;
	import java.util.Arrays;
	import java.util.Collection;
	import java.util.HashMap;
	import java.util.Map;

	import main.java.Course;

	import static org.junit.Assert.*;

	@RunWith(Parameterized.class)
	public class BlackboxAssign3 {
	    private Class<Course> classUnderTest;

	    @SuppressWarnings("unchecked")
	    public BlackboxAssign3(Object classUnderTest) {
	        this.classUnderTest = (Class<Course>) classUnderTest;
	    }

	    @Parameters
	    public static Collection<Object[]> courseGradesUnderTest() {
	        Object[][] classes = {
	                {Course.class}
	        };
	        return Arrays.asList(classes);
	    }

	    private Course createCourse(String name) throws Exception {
	        Constructor<Course> constructor = classUnderTest.getConstructor(String.class);
	        return constructor.newInstance(name);
	    }

	    Course oneStudent;
	    HashMap<String, String> oneStudentExpected = new HashMap<String, String>();

	    Course happyDayGradeBoundary;
	    HashMap<String, String> happyDayGradeBoundaryExpected = new HashMap<String, String>();

	    Course oneStudentOver100;
	    HashMap<String, String> oneStudentOver100Expected = new HashMap<String, String>();
	    
	    Course oneStudentZero;
	    HashMap<String, String> oneStudentZeroExpected = new HashMap<String, String>();
	    
	    Course twoStudentZeroBoundary;
	    HashMap<String, String> twoStudentZeroBoundaryExpected = new HashMap<String, String>();
	    
	    Course fiveStudentNoCurve;
	    HashMap<String, String> fiveStudentNoCurveExpected = new HashMap<String, String>();
	    
	    Course fiveStudentCurve;
	    HashMap<String, String> fiveStudentCurveExpected = new HashMap<String, String>();
	    
	    Course fiveStudentCurveReverse;
	    HashMap<String, String> fiveStudentCurveReverseExpected = new HashMap<String, String>();
	    
	    Course fiveStudentBigCurve;
	    HashMap<String, String> fiveStudentBigCurveExpected = new HashMap<String, String>();
	    
	    Course fiveStudentOneGood;
	    HashMap<String, String> fiveStudentOneGoodExpected = new HashMap<String, String>();

	    
	    /*
	    Course fiveStudentAllNegative;
	    HashMap<String, String> fiveStudentAllNegativeExpected = new HashMap<String, String>();
	    */
	    
	    Course NegativeCurve;
	    HashMap<String, String> NegativeCurveExpected = new HashMap<String, String>();
	    
	    Course fiveStudentsOne101;
	    HashMap<String, String> fiveStudentsOne101Expected = new HashMap<String, String>();
	    
	    Course fiveStudentCurveShuffle;
	    HashMap<String, String> fiveStudentCurveShuffleExpected = new HashMap<String, String>();
	    
	    Course fiveStudentMiddle;
	    HashMap<String, String> fiveStudentMiddleExpected = new HashMap<String, String>();
	    
	    Course empty;
	    HashMap<String, String> emptyExpected = new HashMap<String, String>();
	    
	    Course big;
	    HashMap<String, String> bigExpected = new HashMap<String, String>();
	    @Before
	    public void setUp() throws Exception {

	        // One Student


	        // all courses should be created like this


	        // Course created with two Students having
	        oneStudent = createCourse("SER316");

	        // this would be the expected result after the method countOccurencesLetterGrades is called
	        oneStudent.set_points("Hanna", 50);
	        oneStudentExpected.put("Hanna", "A");

	        // Happy Day Case Grade Boundaries
	        // Four Students mix of grades
	        happyDayGradeBoundary = createCourse("SER315");
	        happyDayGradeBoundary.set_points("100"  , 100);  //A
	        happyDayGradeBoundary.set_points(">89"  , 90);  //A
	        happyDayGradeBoundary.set_points(">79"  , 89);  //B
	        happyDayGradeBoundary.set_points(">59"  , 79);  //C
	        happyDayGradeBoundary.set_points(">35"  , 59);  //D
	        happyDayGradeBoundary.set_points(">=35" , 35);  //F

	        happyDayGradeBoundaryExpected.put("100"  , "A");
	        happyDayGradeBoundaryExpected.put(">89"  , "A");
	        happyDayGradeBoundaryExpected.put(">79"  , "B");
	        happyDayGradeBoundaryExpected.put(">59"  , "C");
	        happyDayGradeBoundaryExpected.put(">35"  , "D");
	        happyDayGradeBoundaryExpected.put(">=35" , "F");
	        
	        //OneStudentOver100
	        oneStudentOver100 = createCourse("SER316");
	        oneStudentOver100.set_points("Hanna", 101);
	        oneStudentOver100Expected.put("Hanna", "A");
	        
	        //OneStudentZero
	        oneStudentZero = createCourse("SER316");
	        oneStudentZero.set_points("Hanna", 0);
	        oneStudentZeroExpected.put("Hanna", "A");
	        
	        //TwoStudentZeroBoundary
	        twoStudentZeroBoundary = createCourse("SER316");
	        
	        twoStudentZeroBoundary.set_points("Hanna1", 0);
	        twoStudentZeroBoundaryExpected.put("Hanna1", "A");
	        
	        twoStudentZeroBoundary.set_points("Hanna2", 0);
	        twoStudentZeroBoundaryExpected.put("Hanna2", "A");
	        
	        //fiveStudentNoCurve
	        fiveStudentNoCurve = createCourse("SER316");
	        
	        fiveStudentNoCurve.set_points("Hanna1", 100);
	        fiveStudentNoCurveExpected.put("Hanna1", "A");
	        
	        fiveStudentNoCurve.set_points("Hanna2", 89);
	        fiveStudentNoCurveExpected.put("Hanna2", "B");

	        fiveStudentNoCurve.set_points("Hanna3", 79);
	        fiveStudentNoCurveExpected.put("Hanna3", "C");
	        
	        fiveStudentNoCurve.set_points("Hanna4", 59);
	        fiveStudentNoCurveExpected.put("Hanna4", "D");
	        
	        fiveStudentNoCurve.set_points("Hanna5", 35);
	        fiveStudentNoCurveExpected.put("Hanna5", "F");
	        
	        //fiveStudentCurve
	        fiveStudentCurve = createCourse("SER316");
	        
	        fiveStudentCurve.set_points("Hanna1", 99);
	        fiveStudentCurveExpected.put("Hanna1", "A");
	        
	        fiveStudentCurve.set_points("Hanna2", 89);
	        fiveStudentCurveExpected.put("Hanna2", "A");

	        fiveStudentCurve.set_points("Hanna3", 79);
	        fiveStudentCurveExpected.put("Hanna3", "B");
	        
	        fiveStudentCurve.set_points("Hanna4", 59);
	        fiveStudentCurveExpected.put("Hanna4", "C");
	        
	        fiveStudentCurve.set_points("Hanna5", 35);
	        fiveStudentCurveExpected.put("Hanna5", "D");
	        
	        //fiveStudentCurveReverse
	        fiveStudentCurveReverse = createCourse("SER316");
	        
	        fiveStudentCurveReverse.set_points("Hanna1", 35);
	        fiveStudentCurveReverseExpected.put("Hanna1", "D");
	        
	        fiveStudentCurveReverse.set_points("Hanna2", 59);
	        fiveStudentCurveReverseExpected.put("Hanna2", "C");

	        fiveStudentCurveReverse.set_points("Hanna3", 79);
	        fiveStudentCurveReverseExpected.put("Hanna3", "B");
	        
	        fiveStudentCurveReverse.set_points("Hanna4", 89);
	        fiveStudentCurveReverseExpected.put("Hanna4", "A");
	        
	        fiveStudentCurveReverse.set_points("Hanna5", 99);
	        fiveStudentCurveReverseExpected.put("Hanna5", "A");
	        
	        //fiveStudentBigCurve
	        fiveStudentBigCurve = createCourse("SER316");
	        
	        fiveStudentBigCurve.set_points("Hanna1", 49);
	        fiveStudentBigCurveExpected.put("Hanna1", "A");
	        
	        fiveStudentBigCurve.set_points("Hanna2", 39);
	        fiveStudentBigCurveExpected.put("Hanna2", "A");

	        fiveStudentBigCurve.set_points("Hanna3", 29);
	        fiveStudentBigCurveExpected.put("Hanna3", "B");
	        
	        fiveStudentBigCurve.set_points("Hanna4", 9);
	        fiveStudentBigCurveExpected.put("Hanna4", "C");
	        
	        fiveStudentBigCurve.set_points("Hanna5", -15);
	        fiveStudentBigCurveExpected.put("Hanna5", "D");
	        
	        //fiveStudentOneGood
	        fiveStudentOneGood = createCourse("SER316");
	        
	        fiveStudentOneGood.set_points("Hanna1", 100);
	        fiveStudentOneGoodExpected.put("Hanna1", "A");
	        
	        fiveStudentOneGood.set_points("Hanna2", 39);
	        fiveStudentOneGoodExpected.put("Hanna2", "D");

	        fiveStudentOneGood.set_points("Hanna3", 29);
	        fiveStudentOneGoodExpected.put("Hanna3", "F");
	        
	        fiveStudentOneGood.set_points("Hanna4", 9);
	        fiveStudentOneGoodExpected.put("Hanna4", "F");
	        
	        fiveStudentOneGood.set_points("Hanna5", -15);
	        fiveStudentOneGoodExpected.put("Hanna5", "F");
	        
	        /*fiveStudentAllNegative
	        fiveStudentAllNegative = createCourse("SER316");
	        
	        fiveStudentAllNegative.set_points("Hanna1", -100);
	        fiveStudentAllNegativeExpected.put("Hanna1", "F");
	        
	        fiveStudentAllNegative.set_points("Hanna2", -89);
	        fiveStudentAllNegativeExpected.put("Hanna2", "D");

	        fiveStudentAllNegative.set_points("Hanna3", -79);
	        fiveStudentAllNegativeExpected.put("Hanna3", "D");
	        
	        fiveStudentAllNegative.set_points("Hanna4", -59);
	        fiveStudentAllNegativeExpected.put("Hanna4", "C");
	        
	        fiveStudentAllNegative.set_points("Hanna5", -35);
	        fiveStudentAllNegativeExpected.put("Hanna5", "A");
	        */
	        
	        //NegativeCurve
	        NegativeCurve = createCourse("SER316");
	        
	        NegativeCurve.set_points("Hanna1", 101);
	        NegativeCurveExpected.put("Hanna1", "A");
	        
	        NegativeCurve.set_points("Hanna2", 90);
	        NegativeCurveExpected.put("Hanna2", "A");

	        NegativeCurve.set_points("Hanna3", 80);
	        NegativeCurveExpected.put("Hanna3", "B");
	        
	        NegativeCurve.set_points("Hanna4", 60);
	        NegativeCurveExpected.put("Hanna4", "C");
	        
	        NegativeCurve.set_points("Hanna5", 36);
	        NegativeCurveExpected.put("Hanna5", "D");
	        
	        //fiveStudentsOne101
	        fiveStudentsOne101 = createCourse("SER316");
	        
	        fiveStudentsOne101.set_points("Hanna1", 101);
	        fiveStudentsOne101Expected.put("Hanna1", "A");
	        
	        fiveStudentsOne101.set_points("Hanna2", 89);
	        fiveStudentsOne101Expected.put("Hanna2", "B");

	        fiveStudentsOne101.set_points("Hanna3", 79);
	        fiveStudentsOne101Expected.put("Hanna3", "C");
	        
	        fiveStudentsOne101.set_points("Hanna4", 59);
	        fiveStudentsOne101Expected.put("Hanna4", "D");
	        
	        fiveStudentsOne101.set_points("Hanna5", 35);
	        fiveStudentsOne101Expected.put("Hanna5", "F");
	        
	        //fiveStudentCurveShuffle
	        fiveStudentCurveShuffle = createCourse("SER316");
	        
	        fiveStudentCurveShuffle.set_points("Hanna1", 35);
	        fiveStudentCurveShuffleExpected.put("Hanna1", "D");
	        
	        fiveStudentCurveShuffle.set_points("Hanna2", 59);
	        fiveStudentCurveShuffleExpected.put("Hanna2", "C");
	        
	        fiveStudentCurveShuffle.set_points("Hanna5", 99);
	        fiveStudentCurveShuffleExpected.put("Hanna5", "A");

	        fiveStudentCurveShuffle.set_points("Hanna3", 79);
	        fiveStudentCurveShuffleExpected.put("Hanna3", "B");
	        
	        fiveStudentCurveShuffle.set_points("Hanna4", 89);
	        fiveStudentCurveShuffleExpected.put("Hanna4", "A");
	        
	        //fiveStudentMiddle
	        fiveStudentMiddle = createCourse("SER316");
	        
	        fiveStudentMiddle.set_points("Hanna1", 85);
	        fiveStudentMiddleExpected.put("Hanna1", "A");
	        
	        fiveStudentMiddle.set_points("Hanna2", 74);
	        fiveStudentMiddleExpected.put("Hanna2", "B");
	        
	        fiveStudentMiddle.set_points("Hanna5", 33);
	        fiveStudentMiddleExpected.put("Hanna5", "D");

	        fiveStudentMiddle.set_points("Hanna3", 84);
	        fiveStudentMiddleExpected.put("Hanna3", "A");
	        
	        fiveStudentMiddle.set_points("Hanna4", 0);
	        fiveStudentMiddleExpected.put("Hanna4", "F");
	        
	        //empty
	        empty = createCourse("SER316");
	        
	        //big
	        big = createCourse("SER316");
	        for (int i = 0; i < 10000; i++) {
	            big.set_points("Hanna"+i, 50);
	            bigExpected.put("Hanna"+i, "A");
	        }
	        big.set_points("anna", 0);
	        bigExpected.put("anna", "D");

	    }

	    @After
	    public void tearDown() throws Exception {
	    }

	    // sample test
	    @Test
	    public void oneStudent() {
	        Map<String, String> ans = oneStudent.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(oneStudentExpected));
	    }

	    // sample test2
	    @Test
	    public void happyDayGradeBoundaries() {
	        Map<String, String> ans = happyDayGradeBoundary.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(happyDayGradeBoundaryExpected));
	    }
	    
	    @Test
	    public void oneStudentOver100() {
	    	System.out.println("Start of oneStudentOver100");
	    	
	        Map<String, String> ans = oneStudentOver100.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(oneStudentOver100Expected));
	        
	        System.out.println("End of oneStudentOver100");
	    	
	    }
	    
	    @Test
	    public void oneStudentZero() {
	    	System.out.println("Start of oneStudentZero");
	    	
	        Map<String, String> ans = oneStudentZero.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(oneStudentZeroExpected));
	        
	        System.out.println("End of oneStudentZero");
	    	
	    }
	    
	    @Test
	    public void twoStudentZeroBoundary() {
	    	System.out.println("Start of twoStudentZeroBoundary");
	    	
	        Map<String, String> ans = twoStudentZeroBoundary.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(twoStudentZeroBoundaryExpected));
	        
	        System.out.println("End of twoStudentZeroBoundary");
	    	
	    }
	    
	    @Test
	    public void fiveStudentNoCurve() {
	    	System.out.println("Start of fiveStudentNoCurve");
	    	
	        Map<String, String> ans = fiveStudentNoCurve.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentNoCurveExpected));
	        
	        System.out.println("End of fiveStudentNoCurve");
	    	
	    }
	    
	    @Test
	    public void fiveStudentCurve() {
	    	System.out.println("Start of fiveStudentCurve");
	    	
	        Map<String, String> ans = fiveStudentCurve.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentCurveExpected));
	        
	        System.out.println("End of fiveStudentCurve");
	    	
	    }
	    
	    @Test
	    public void fiveStudentCurveReverse() {
	    	System.out.println("Start of fiveStudentCurveReverse");
	    	
	        Map<String, String> ans = fiveStudentCurveReverse.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentCurveReverseExpected));
	        
	        System.out.println("End of fiveStudentCurveReverse");
	    	
	    }
	    
	    @Test
	    public void fiveStudentBigCurve() {
	    	System.out.println("Start of fiveStudentBigCurve");
	    	
	        Map<String, String> ans = fiveStudentBigCurve.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentBigCurveExpected));
	        
	        System.out.println("End of fiveStudentBigCurve");
	    	
	    }
	    
	    @Test
	    public void fiveStudentOneGood() {
	    	System.out.println("Start of fiveStudentOneGood");
	    	
	        Map<String, String> ans = fiveStudentOneGood.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentOneGoodExpected));
	        
	        System.out.println("End of fiveStudentOneGood");
	    	
	    }
	    /*
	    @Test
	    public void fiveStudentAllNegative() {
	    	System.out.println("Start of fiveStudentAllNegative");
	    	
	        Map<String, String> ans = fiveStudentAllNegative.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentAllNegativeExpected));
	        
	        System.out.println("End of fiveStudentAllNegative");
	    	
	    }
	    */
	    @Test
	    public void negativeCurve() {
	    	System.out.println("Start of NegativeCurve");
	    	
	        Map<String, String> ans = NegativeCurve.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(NegativeCurveExpected));
	        
	        System.out.println("End of NegativeCurve");
	    	
	    }
	    
	    @Test
	    public void fiveStudentsOne101() {
	    	System.out.println("Start of fiveStudentsOne101");
	    	
	        Map<String, String> ans = fiveStudentsOne101.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentsOne101Expected));
	        
	        System.out.println("End of fiveStudentsOne101");
	    	
	    }

	    
	    @Test
	    public void fiveStudentCurveShuffle() {
	    	System.out.println("Start of fiveStudentCurveShuffle");
	    	
	        Map<String, String> ans = fiveStudentCurveShuffle.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentCurveShuffleExpected));
	        
	        System.out.println("End of fiveStudentCurveShuffle");
	    	
	    }
	    
	    @Test
	    public void fiveStudentMiddle() {
	    	System.out.println("Start of fiveStudentMiddle");
	    	
	        Map<String, String> ans = fiveStudentMiddle.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(fiveStudentMiddleExpected));
	        
	        System.out.println("End of fiveStudentMiddle");
	    	
	    }
	    
	    @Test
	    public void empty() {
	    	System.out.println("Start of empty");
	    	
	        Map<String, String> ans = empty.curveLetterGrades();
	        for(Map.Entry<String, String> e : ans.entrySet())
	            System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(emptyExpected));
	        
	        System.out.println("End of empty");
	    	
	    }
	    
	    @Test
	    public void big() {
	    	System.out.println("Start of big");
	    	
	        Map<String, String> ans = big.curveLetterGrades();
	        //for(Map.Entry<String, String> e : ans.entrySet())
	            //System.out.println(e.getKey() + " " + e.getValue());
	        assertTrue(ans.equals(bigExpected));
	        
	        System.out.println("End of big");
	    	
	    }
	}
