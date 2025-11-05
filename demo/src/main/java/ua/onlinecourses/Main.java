package ua.onlinecourses;

import ua.onlinecourses.exception.InvalidDataException;
import ua.onlinecourses.model.*;
import ua.onlinecourses.parser.CourseFileParser;
import ua.onlinecourses.parser.InstructorFileParser;
import ua.onlinecourses.parser.ModuleFileParser;
import ua.onlinecourses.parser.StudentFileParser;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.logging.Level;

public class Main {
    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        // Demonstrate logging setup
        logger.log(Level.INFO, "Starting demonstration of online courses system");

        // Demonstrate manual object creation with valid data
        try {
            logger.log(Level.INFO, "Creating valid Student object");
            Student student = new Student("John", "Doe", "john.doe@example.com", LocalDate.now());
            logger.log(Level.INFO, "Student full name: {0}", student.getFullName());

            logger.log(Level.INFO, "Creating valid Course object");
            Course course = new Course("Java Basics", "Introduction to Java Programming", 3, LocalDate.now());
            logger.log(Level.INFO, "Course full name: {0}", course.getFullName());

            logger.log(Level.INFO, "Creating valid Instructor object");
            Instructor instructor = new Instructor("Jane", "Smith", 10);
            logger.log(Level.INFO, "Instructor full name: {0}", instructor.getFullName());

            logger.log(Level.INFO, "Creating valid Module object");
            myModule module = new myModule("Module 1", "Content for Module 1");
            logger.log(Level.INFO, "Module full name: {0}", module.getFullName());

            logger.log(Level.INFO, "Creating valid Assignment object");
            Assignment assignment = new Assignment(module, LocalDate.now().plusDays(7), 50, Mark.GOOD);
            logger.log(Level.INFO, "Assignment mark message: {0}", assignment.getMark());

        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, "Invalid data during object creation: {0}", e.getMessage());
        }

        // Demonstrate invalid creation to show exception handling
        try {
            logger.log(Level.WARNING, "Attempting invalid Student creation (invalid email)");
            Student invalidStudent = new Student("Alice", "Wonder", "invalid-email", LocalDate.now());
        } catch (InvalidDataException e) {
            logger.log(Level.WARNING, "Handled InvalidDataException: {0}", e.getMessage());
        }

        // Read data from CSV files using parsers
        String studentsFile = "C:\\Users\\dinag\\OneDrive\\Desktop\\algoritm\\lab1Java\\src\\ua\\onlinecourses\\students.csv";
        String coursesFile = "C:\\Users\\dinag\\OneDrive\\Desktop\\algoritm\\lab1Java\\src\\ua\\onlinecourses\\courses.csv";
        String instructorsFile = "C:\\Users\\dinag\\OneDrive\\Desktop\\algoritm\\lab1Java\\src\\ua\\onlinecourses\\instructors.csv";
        String modulesFile = "C:\\Users\\dinag\\OneDrive\\Desktop\\algoritm\\lab1Java\\src\\ua\\onlinecourses\\modules.csv";

        // Parse Students
        try {
            logger.log(Level.INFO, "Parsing students from {0}", studentsFile);
            List<Student> students = StudentFileParser.parseFromCSV(studentsFile);
            logger.log(Level.INFO, "Parsed {0} students successfully", students.size());
            if (!students.isEmpty()) {
                logger.log(Level.INFO, "First student full name: {0}", students.get(0).getFullName());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while parsing {0}: {1}", new Object[]{studentsFile, e.getMessage()});
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, "InvalidDataException while parsing {0}: {1}", new Object[]{studentsFile, e.getMessage()});
        }

        // Parse Courses
        try {
            logger.log(Level.INFO, "Parsing courses from {0}", coursesFile);
            List<Course> courses = CourseFileParser.parseFromCSV(coursesFile);
            logger.log(Level.INFO, "Parsed {0} courses successfully", courses.size());
            if (!courses.isEmpty()) {
                logger.log(Level.INFO, "First course full name: {0}", courses.get(0).getFullName());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while parsing {0}: {1}", new Object[]{coursesFile, e.getMessage()});
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, "InvalidDataException while parsing {0}: {1}", new Object[]{coursesFile, e.getMessage()});
        }

        // Parse Instructors
        try {
            logger.log(Level.INFO, "Parsing instructors from {0}", instructorsFile);
            List<Instructor> instructors = InstructorFileParser.parseFromCSV(instructorsFile);
            logger.log(Level.INFO, "Parsed {0} instructors successfully", instructors.size());
            if (!instructors.isEmpty()) {
                logger.log(Level.INFO, "First instructor full name: {0}", instructors.get(0).getFullName());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while parsing {0}: {1}", new Object[]{instructorsFile, e.getMessage()});
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, "InvalidDataException while parsing {0}: {1}", new Object[]{instructorsFile, e.getMessage()});
        }

        // Parse Modules
        try {
            logger.log(Level.INFO, "Parsing modules from {0}", modulesFile);
            List<myModule> modules = ModuleFileParser.parseFromCSV(modulesFile);
            logger.log(Level.INFO, "Parsed {0} modules successfully", modules.size());
            if (!modules.isEmpty()) {
                logger.log(Level.INFO, "First module full name: {0}", modules.get(0).getFullName());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while parsing {0}: {1}", new Object[]{modulesFile, e.getMessage()});
        } catch (InvalidDataException e) {
            logger.log(Level.SEVERE, "InvalidDataException while parsing {0}: {1}", new Object[]{modulesFile, e.getMessage()});
        }

        logger.log(Level.INFO, "Demonstration completed");
    }
}