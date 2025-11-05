
package ua.onlinecourses.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;
import ua.onlinecourses.exception.InvalidDataException;
import ua.onlinecourses.util.StudentUtils;


public record Student(String firstName, String lastName, String email, LocalDate enrollmentDate) {
    
       private static final Logger logger = Logger.getLogger(Student.class.getName()); 

    
    public Student(String firstName, String lastName, String email, LocalDate enrollmentDate) {
        if (StudentUtils.isValidName(firstName) && StudentUtils.isValidName(lastName) && StudentUtils.isValidEmail(email) && StudentUtils.isValidEnrollmentDate(enrollmentDate)) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enrollmentDate = enrollmentDate;
        } else {
            throw new InvalidDataException("Invalid parameters");
        } 
        logger.log(Level.INFO, "Student created successfully: {0}, {1}, {2}, {3}",
                new Object[]{firstName, lastName, email, enrollmentDate});
    }
  

    static Student createStudent(String firstName, String lastName, String email, LocalDate enrollmentDate) {
        if (StudentUtils.isValidName(firstName) &&
                StudentUtils.isValidName(lastName) &&
                StudentUtils.isValidEmail(email) &&
                StudentUtils.isValidEnrollmentDate(enrollmentDate)) {
            logger.log(Level.INFO, "Student created successfully: {0}, {1}, {2}, {3}",
                new Object[]{firstName, lastName, email, enrollmentDate});
            return new Student(firstName,  lastName,  email,  enrollmentDate);
        }
        throw new InvalidDataException("Invalid parameters");
    }
    
    public String getFullName() {
        if (firstName.length() < 3 || lastName.length() < 3 || email.length() < 3) {
            String errorMsg = "Cannot create full name";
            throw new InvalidDataException(errorMsg);
        }
        String fullName = firstName.substring(0, 3).toUpperCase()  + lastName.substring(0, 3).toUpperCase() + "-" + email.substring(0, 3).toUpperCase() + "-" + enrollmentDate.toString();   
        return fullName;
    }
    
   
}
