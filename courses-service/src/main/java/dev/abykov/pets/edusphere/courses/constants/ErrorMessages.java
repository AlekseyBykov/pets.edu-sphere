package dev.abykov.pets.edusphere.courses.constants;

public class ErrorMessages {

    public static final String COURSE_NOT_FOUND = "Course with ID %s not found";
    public static final String MODULE_NOT_FOUND = "Module with ID %s not found";

    private ErrorMessages() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
