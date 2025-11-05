
package ua.onlinecourses.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;
import java.util.logging.Level;
import ua.onlinecourses.exception.InvalidDataException;

import ua.onlinecourses.util.AssignmentUtils;

public record Assignment( myModule module,LocalDate dueDate,int maxPoints, Mark mark){
    
     private static final Logger logger = Logger.getLogger(Student.class.getName());

    

    public Assignment(myModule module, LocalDate dueDate, int maxPoints, Mark mark) {
        if (AssignmentUtils.isValidMaxPoints(maxPoints) && AssignmentUtils.isValidDueDate(dueDate)) {
        this.module = module;
        this.dueDate = dueDate;
        this.maxPoints=maxPoints;
        this.mark=mark;
    }    else {
            throw new InvalidDataException("Invalid parameters");
        }  
         logger.log(Level.INFO, "Assignment created successfully: {0}, {1}, {2}, {3}",
                new Object[]{module, dueDate, maxPoints, mark});

    }

    
    static Assignment createAssignment(myModule module, LocalDate dueDate, int maxPoints, Mark mark) {
        if (AssignmentUtils.isValidDueDate(dueDate) && AssignmentUtils.isValidMaxPoints(maxPoints)) {
            logger.log(Level.INFO, "Assignment created successfully: {0}, {1}, {2}, {3}",
                new Object[]{module, dueDate, maxPoints, mark});
            return new Assignment(module, dueDate, maxPoints, mark);
        }
        throw new InvalidDataException("Invalid parameters");
    }
    
    public String getMark(){
        
        return switch(this.mark){
            case EXCELLENT -> "Your mark is excelent";
            case GOOD -> "Your mark is good.";
            case PASSED -> "You passed the exam";
            case SATISFACTORY -> "Your mark is satisfactory.";
            case LOW -> "Your mark is low.";
            case NOT_PASSED -> "You did not pass the exam.";
            default -> "Exam has not happend.";
        };
    }
    
    
 
    
}
