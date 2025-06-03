package dev.abykov.pets.edusphere.courses.constants;

public final class CoursesConstants {

    public static final String STATUS_200 = "SUCCESS";
    public static final String STATUS_201 = "CREATED";
    public static final String STATUS_404 = "NOT_FOUND";
    public static final String STATUS_417 = "EXPECTATION_FAILED";

    public static final String MESSAGE_200 = "Course operation successful.";
    public static final String MESSAGE_201 = "Course created successfully.";
    public static final String MESSAGE_404 = "Course not found.";
    public static final String MESSAGE_417_UPDATE = "Course update failed.";
    public static final String MESSAGE_417_DELETE = "Course deletion failed.";

    private CoursesConstants() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }
}
