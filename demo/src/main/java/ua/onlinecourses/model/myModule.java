
package ua.onlinecourses.model;

import java.util.Objects;
import ua.onlinecourses.exception.InvalidDataException;
import ua.onlinecourses.util.ModuleUtils;
import java.util.logging.Logger;
import java.util.logging.Level;
 
 

public record myModule(String title, String content){
    
    private static final Logger logger = Logger.getLogger(Student.class.getName());
   
    public myModule(String title, String content) {
        if(ModuleUtils.isValidTitle(title) && ModuleUtils.isValidContent(content)) {
        this.title = title;
        this.content = content;
        } else {
            throw new InvalidDataException("Invalid parameters");
        } 
        logger.log(Level.INFO, "Module created successfully: {0}, {1}",
                new Object[]{title, content});
    }
    
 
     static myModule createModule(String title, String content) {
        if (ModuleUtils.isValidTitle(title) &&
                ModuleUtils.isValidContent(content)) {
            logger.log(Level.INFO, "Module created successfully: {0}, {1}",
                new Object[]{title, content});
            return new myModule(title, content);
        }
        throw new InvalidDataException("Invalid parameters");
    }
     
     public String getFullName() {
        if (title.length() < 3 || content.length() < 3) {
            String errorMsg = "Cannot create full name";
            throw new InvalidDataException(errorMsg);
        }
        String fullName = title.substring(0, 3).toUpperCase() + "-" + content.substring(0, 3).toUpperCase();   
        return fullName;
    }
     
    
}
