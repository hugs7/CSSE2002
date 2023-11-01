package unittesting;

import org.junit.Before;
import org.junit.Test;

/* if we don't use the static keyword here, then we will need to say
Assert.assertEquals() instead of just assertEquals(). Either approach is fine */
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *   >>>> READ THIS COMMENT. DO NOT IGNORE! <<<<
 *
 * For all unit tests, it is the developer's responsibility to ensure that the 'expected'
 * value in all unit tests is correct.
 * e.g. For methods which require calculations, you need to manually do the calculation first
 * to get the expected value.
 * The expected values must always be known, and need to be specifically and carefully chosen.
 *
 * Each unit test should test only ONE case for ONE function.
 * You should not have one very complex unit test which tests absolutely everything for a function.
 * Instead, you should have multiple, simpler unit tests.
 *
 * Unit tests should sufficiently test functions, so that we can be sure the function
 * implementation is correct.
 * Methods which are more complicated will require more unit tests.
 * Generally, everything inside a method which can change the result will require the function
 * to have more unit tests which test these alternative outcomes.
 * You often need to consider 'edge cases' in your unit tests.
 *
 * Working out what unit tests to write is challenging. It can be somewhat subjective.
 * However, it is still important that you write rigorous unit tests which ensure your
 * implementation is working correctly.
 * This primarily comes with experience, and takes practice.
 *
 * NEVER use any form of randomisation or random number generation etc. in unit tests.
 * You do not have absolute control over the expected values if any random values are used.
 */
public class StudentTest {

    // the student objects are class variables so all unit tests can use these
    private Student student1;
    private Student student2;


    @Before
    public void setup() {
        // create two student objects.
        // This may be useful if we need to use multiple student objects in one test.
        // (this would be useful in Assignment 2 when using equals() etc.)
        student1 = new Student("Alice", 12345678, 20);
        student2 = new Student("Jiandong", 11223344, 23);
    }


    /* STUDENTS NOTE
     * Q: Why is the order of parameters different in the assertEquals() methods below
     * compared to the lecture video?
     *
     * A: In the lecture video the 'expected' variable was the third parameter, but
     * it should always be the second parameter. So these need to be swapped.
     * See: https://junit.org/junit4/javadoc/4.8/org/junit/Assert.html
     *
     * The tests still work in the video because the same two things are still being compared.
     * But you would notice however, when the unit test fails, the 'Actual' and
     * 'Expected' which JUnit reports are backwards (e.g. 32:17 in video).
     */


    @Test
    public void getNameTest1() {
        assertEquals("The name was incorrect.",
                "Alice", student1.getName());
    }
    @Test
    public void getNameTest2() {
        assertEquals("The name was incorrect.",
                "Jiandong", student2.getName());
    }


    @Test
    public void getStudentNumberTest1() {
        assertEquals("The student number was incorrect.",
                12345678, student1.getStudentNumber());
    }
    @Test
    public void getStudentNumberTest2() {
        assertEquals("The student number name was incorrect.",
                11223344, student2.getStudentNumber());
    }


    // could add unit tests to test getAge() here.


    @Test
    public void setAgeTest() {
        int expected = 21;

        student1.setAge(expected);

        assertEquals("The age was not correct.",
                expected, student1.getAge());
    }


    @Test
    public void addEnrolledCourseOneTest() {
        // set up an arraylist with the expected output
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("CSSE2002");

        student1.addEnrolledCourse("CSSE2002");

        assertEquals("The list of enrolled courses did not match.",
                expected, student1.getEnrolledCourses());
    }
    @Test
    public void addEnrolledCourseMultipleTest() {
        // set up an arraylist with the expected output
        ArrayList<String> expected = new ArrayList<String>();
        expected.add("CSSE2002");
        expected.add("CSSE2010");

        student1.addEnrolledCourse("CSSE2002");
        student1.addEnrolledCourse("CSSE2010");

        assertEquals("The list of enrolled courses did not match.",
                expected, student1.getEnrolledCourses());
    }


    @Test
    public void addCompletedCourseOneCourseTest() {
        // set up a hashmap with the expected output
        HashMap<String, Integer> expected =  new HashMap<String, Integer>();
        expected.put("CSSE2002", 6);

        student1.addCompletedCourse("CSSE2002", 6);

        assertEquals("The completed courses did not match",
                expected, student1.getCompletedCourses());
    }
    @Test
    public void addCompletedCourseMultipleCourseTest() {
        // set up a hashmap with the expected output
        HashMap<String, Integer> expected =  new HashMap<String, Integer>();
        expected.put("CSSE2002", 6);
        expected.put("CSSE1001", 5);

        student1.addCompletedCourse("CSSE2002", 6);
        student1.addCompletedCourse("CSSE1001", 5);

        assertEquals("The completed courses did not match",
                expected, student1.getCompletedCourses());
    }


    @Test
    public void toStringNoCourseTest() {
        String expected = "Alice|12345678|NONE";

        assertEquals("The toString() representation was incorrect.",
                expected, student1.toString());
    }
    @Test
    public void toStringOneEnrolledCourseTest() {
        // note the comma at the end. This is specified in the method Javadoc comment
        String expected = "Alice|12345678|CSSE2002,";

        student1.addEnrolledCourse("CSSE2002");

        assertEquals("The toString() representation was incorrect.",
                expected, student1.toString());
    }
    @Test
    public void toStringMultipleEnrolledCourseTest() {
        // note the comma at the end. This is specified in the method Javadoc comment
        String expected = "Alice|12345678|CSSE2002,CSSE2010,MATH2302,";

        student1.addEnrolledCourse("CSSE2002");
        student1.addEnrolledCourse("CSSE2010");
        student1.addEnrolledCourse("MATH2302");

        assertEquals("The toString() representation was incorrect.",
                expected, student1.toString());
    }


    @Test
    public void calculateGPANoCourseTest() {
        double expected = 0; // if there are no courses, there cannot be a GPA.
        double allowance = 0.001; // required in case of slight issues with floating point calculations

        assertEquals("The GPA should be 0 if the student has not completed any courses yet.",
                expected, student1.calculateGPA(), allowance);
    }
    @Test
    public void calculateGPAOneCourseTest() {
        double expected = 5.0; // if there are no courses, there cannot be a GPA.
        double allowance = 0.001; // required in case of slight issues with floating point calculations

        student1.addCompletedCourse("CSSE2002", 5);

        assertEquals("The GPA was not correct.",
                expected, student1.calculateGPA(), allowance);
    }
    @Test
    public void calculateGPATwoCourseTest() {
        double expected = 4.5; // if there are no courses, there cannot be a GPA.
        double allowance = 0.001; // required in case of slight issues with floating point calculations

        student1.addCompletedCourse("CSSE2002", 5);
        student1.addCompletedCourse("CSSE1001", 4);

        assertEquals("The GPA was not correct.",
                expected, student1.calculateGPA(), allowance);
    }
    @Test
    public void calculateGPAMultipleCourseTest() {
        double expected = 5.1667; // if there are no courses, there cannot be a GPA.
        double allowance = 0.001; // required in case of slight issues with floating point calculations

        student1.addCompletedCourse("CSSE2002", 5);
        student1.addCompletedCourse("CSSE1001", 7);
        student1.addCompletedCourse("CSSE2010", 5);
        student1.addCompletedCourse("ENGG1300", 6);
        student1.addCompletedCourse("SCIE1300", 4);
        student1.addCompletedCourse("MATH2302", 4);

        assertEquals("The GPA should be 0 if the student has not completed any courses yet",
                expected, student1.calculateGPA(), allowance);
    }


    @Test
    public void creditPointsAchievedNoCourseTest() {
        int expected = 0;

        assertEquals("The number of credit points was incorrect.",
                expected, student1.creditPointsAchieved());
    }
    @Test
    public void creditPointsAchievedOneCourseTest() {
        int expected = 2; // we know that each completed unit is worth 2 credit points

        // the course code and grade values are not actually important for this unit test,
        // but they must be given
        student1.addCompletedCourse("CSSE2002", 5);

        assertEquals("The number of credit points was incorrect.",
                expected, student1.creditPointsAchieved());
    }
    @Test
    public void creditPointsAchievedTwoCourseTest() {
        int expected = 4; // we know that each completed unit is worth 2 credit points

        // the course code and grade values are not actually important for this unit test,
        // but they must be given
        student1.addCompletedCourse("CSSE2002", 5);
        student1.addCompletedCourse("CSSE1001", 6);

        assertEquals("The number of credit points was incorrect.",
                expected, student1.creditPointsAchieved());
    }
    @Test
    public void creditPointsAchievedMultipleCourseTest() {
        int expected = 8; // we know that each completed unit is worth 2 credit points

        // the course code and grade values are not actually important for this unit test,
        // but they must be given
        student1.addCompletedCourse("CSSE2002", 5);
        student1.addCompletedCourse("CSSE1001", 6);
        student1.addCompletedCourse("CSSE2010", 4);
        student1.addCompletedCourse("ENGG1300", 4);

        assertEquals("The number of credit points was incorrect.",
                expected, student1.creditPointsAchieved());
    }
}