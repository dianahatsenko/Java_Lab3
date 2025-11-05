
package ua.onlinecourses.model;

import java.util.Objects;
import ua.onlinecourses.exception.InvalidDataException;
import ua.onlinecourses.util.InstructorUtils;
import java.util.logging.Logger;
import java.util.logging.Level;

 

public record Instructor(String firstName, String lastName, int expertise) {
    
    private static final Logger logger = Logger.getLogger(Student.class.getName());

    public Instructor(String firstName, String lastName, int expertise) {
        if (InstructorUtils.isValidName(firstName) && InstructorUtils.isValidName(lastName) && InstructorUtils.isValidExpertise(expertise)) {
        this.firstName=firstName;
        this.lastName=lastName;
        this.expertise=expertise;  
        } else {
            throw new InvalidDataException("Invalid parameters");
        }
        logger.log(Level.INFO, "Instructor created successfully: {0}, {1}, {2}",
                new Object[]{firstName, lastName, expertise});
        
    }

    
    static Instructor createInstructor(String firstName, String lastName, int expertise) {
        if (InstructorUtils.isValidName(firstName) && InstructorUtils.isValidName(lastName) && InstructorUtils.isValidExpertise(expertise) ) {
            logger.log(Level.INFO, "Instructor created successfully: {0}, {1}, {2}",
                new Object[]{firstName, lastName, expertise});
            return new Instructor(firstName, lastName, expertise);
        }
        throw new InvalidDataException("Invalid parameters");
    }
    
    public String getFullName() {
        if (firstName.length() < 3 || lastName.length() < 3) {
            String errorMsg = "Cannot create full name";
            throw new InvalidDataException(errorMsg);
        }
        String fullName = firstName.substring(0, 3).toUpperCase()  + lastName.substring(0, 3).toUpperCase() + "-" + expertise ;   
        return fullName;
    }

  
}
