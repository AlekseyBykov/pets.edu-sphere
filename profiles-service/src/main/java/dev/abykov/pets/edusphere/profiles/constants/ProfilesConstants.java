package dev.abykov.pets.edusphere.profiles.constants;

public final class ProfilesConstants {

    private ProfilesConstants() {
        throw new UnsupportedOperationException("Cannot instantiate constant class");
    }

    public static final String STATUS_201 = "201";
    public static final String MESSAGE_201 = "Profile created successfully";

    public static final String STATUS_200 = "200";
    public static final String MESSAGE_200 = "Request processed successfully";

    public static final String STATUS_417 = "417";
    public static final String MESSAGE_417_UPDATE = "Profile update failed. Please try again or contact support";
    public static final String MESSAGE_417_DELETE = "Profile delete failed. Please try again or contact support";
}
