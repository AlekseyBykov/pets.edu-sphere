package dev.abykov.pets.edusphere.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Schema(name = "Profile", description = "Schema to hold profile information")
public class ProfileDto {

    @Schema(description = "Full name of the profile", example = "Aleksey Bykov")
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, max = 50, message = "The length of the name should be between 3 and 50 characters")
    private String name;

    @Schema(description = "Email address", example = "contact@abykov.dev")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Phone number", example = "9123456789")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits")
    private String phone;
}
