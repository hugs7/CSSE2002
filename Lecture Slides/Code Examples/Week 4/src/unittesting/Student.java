package unittesting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Represents a student enrolled at the University of Queensland.
 */
public class Student {
    /** The student's full name */
    private String name;

    /** An eight digit number unique for this student. e.g. 12345678 */
    private int studentNumber; // could be a String, but is an int for this example

    /** The student's age in years */
    private int age;

    /** The courses which the student is currently enrolled in.
     * Courses are represented via their course code.
     * e.g "CSSE2002", "CSSE7023", "CSSE1001"
     */
    private ArrayList<String> enrolledCourses;

    /** The courses which the student has previously passed.
     * Courses are represented via their course code, and the grade which the student achieved.
     * e.g.
     * "CSSE2002",5
     * "CSSE1001",6
     */
    private HashMap<String, Integer> completedCourses;

    /** The number of credit points received for each completed course */
    /* In reality some courses are more than 2 credit points.
    We are just using 2 for all courses here for example simplicity */
    public static int creditPointsPerCourse = 2;


    /**
     *
     * @param name the student's name
     * @param studentNumber the student's unique ID number
     */
    public Student(String name, int studentNumber, int age) {
        this.name = name;
        this.studentNumber = studentNumber;
        this.age = age;
        this.enrolledCourses = new ArrayList<String>();
        this.completedCourses = new HashMap<String, Integer>();
    }


    /**
     * Returns the student's name.
     * @return student's name
     */
    public String getName() {
        return this.name;
    }
    // ^^relatively simple method. Could have one unit test.
    // Good idea to test it for several students though.

    /**
     * Returns the student number.
     * @return student's unique ID number
     */
    public int getStudentNumber() {
        return this.studentNumber;
    }
    // ^^relatively simple method. Could have one unit test.
    // Good idea to test it for several students though.

    /**
     * Returns the student age.
     * @return student's age
     */
    public int getAge() {
        return this.age;
    }
    // ^^relatively simple method. Could have one unit test.
    // Good idea to test it for several students though.

    /**
     * Sets the student's age.
     */
    public void setAge(int age) {
        this.age = age;
    }
    // ^^relatively simple method. Could have one unit test.
    // Good idea to test it for several students though.

    /**
     * Returns the courses the student is enrolled in.
     * @return list of course codes representing the courses the student is enrolled in
     */
    public ArrayList<String> getEnrolledCourses() {
        return this.enrolledCourses;
    }
    // ^^relatively simple method. Does not need many unit tests.
    // Can test for when there are 0 courses, 1 course, and multiple courses.

    /**
     * Returns the completed courses.
     * @return student's unique ID number
     */
    public HashMap<String, Integer> getCompletedCourses() {
        return this.completedCourses;
    }
    // ^^relatively simple method. Does not need many unit tests.
    // Can test for when there are 0 courses, 1 course, and multiple courses.

    /**
     * Adds a course to the list of enrolled courses.
     * @param courseCode the course code to add
     */
    public void addEnrolledCourse(String courseCode) {
        this.enrolledCourses.add(courseCode);

    }
    // ^^should at minimum test when there are 0 courses, 1 course, and multiple courses.

    /**
     * Adds an entry to the the list of completed courses.
     * @param courseCode the course code
     * @param grade the grade the student achieved
     */
    public void addCompletedCourse(String courseCode, int grade) {
        this.completedCourses.put(courseCode, grade);

    }
    // ^^should at minimum test when there are 0 courses, 1 course, and multiple courses.

    /**
     * Returns a string representation of the student.
     * Format of the string is:
     * name|studentNumber|enrolledCourses
     * enrolledCourses are the course codes separated by commas. If the student is not enrolled
     * in any courses, then it says NONE instead.
     * e.g.
     * Alice|12345678|CSSE2002,CSSE2010,MATH2302,
     * or
     * George|09876543|CSSE7023,ENGG1300,
     * or
     * Jiandong|11223344|NONE
     */
    @Override
    public String toString() {
        String result = "";
        result = result + this.name + "|";
        result = result + this.studentNumber + "|";
        if (this.enrolledCourses.size() > 0) { // if the student is enrolled in courses
            // iterate over course codes and add them to the string
            for (String courseCode :this.enrolledCourses) {
                result = result + courseCode + ",";
            }
        }
        else { // if the student is not enrolled in courses
            result = result + "NONE";
        }
        return result;
    }
    // ^^this will require several unit tests.
    // We can test for when there are 0 courses, 1 course, and multiple courses.

    /**
     * Calculates the Grade Point Average, based on the grades received in completed courses.
     * GPA is equal to the sum of the grades received in completed courses, divided by the
     * number of completed courses.
     * If the student has not completed any courses, return 0.
     * @return students' GPA
     */
    public double calculateGPA() {
        int numCoursesCompleted = this.completedCourses.size();
        if (numCoursesCompleted == 0) {
            return 0;
        }

        int sumOfGrades = 0;

        // Getting an iterator
        Iterator it = completedCourses.entrySet().iterator();
        // Iterate through completed courses. Add grades to sum of grades.
        while (it.hasNext()) {
            // retrieve the next entry set
            Map.Entry mapElement = (Map.Entry)it.next();
            // retrieve the value from the entry set
            int grade = (int) mapElement.getValue();
            // add the grade to the sum of grades
            sumOfGrades = sumOfGrades + grade;
        }

        // need to cast to double so decimal points are not lost
        double gpa = (double)sumOfGrades / (double)numCoursesCompleted;
        return gpa;
    }
    // ^^Can test for when there are 0 courses, 1 course, and multiple courses.

    /**
     * Calculates the number of credit points which the student has achieved.
     * This is equal to the multiplied by the number of courses which have been completed.
     */
    public int creditPointsAchieved() {
        return creditPointsPerCourse * this.completedCourses.size();
    }
    // ^^should at minimum test when there are 0 courses completed, 1 course completed,
    // and multiple courses completed.
}
