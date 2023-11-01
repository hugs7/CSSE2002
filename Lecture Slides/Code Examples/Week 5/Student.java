package javadoc;

import java.util.ArrayList;

/** Represent a student enrolled at a university. */
public class Student {
    /** The student's name */
    private String name;

    /** The student's age */
    private int age;

    /** Records the course codes for the courses which the student is enrolled in */
    ArrayList<String> enrolledCourses;

    /**
     * Initialises the student's name, age and course enrolments.
     * @param name the student's name
     * @param age the student's age
     * */
    public Student(String name, int age) {
        this.name = name;
        this.age = age;

        enrolledCourses = new ArrayList<String>();
    }

    /** Return's the student's name.
     * @return the student's name
     */
    public String getName() {
        return this.name;
    }

    /** Return's the student's age.
     * @return the student's age
     */
    public int getAge() {
        return this.age;
    }
}
