package dev.abykov.pets.edusphere.profiles.controller;

import dev.abykov.pets.edusphere.profiles.constants.ProfilesConstants;
import dev.abykov.pets.edusphere.profiles.dto.ErrorResponseDto;
import dev.abykov.pets.edusphere.profiles.dto.ProfileDto;
import dev.abykov.pets.edusphere.profiles.dto.ResponseDto;
import dev.abykov.pets.edusphere.profiles.service.IProfilesService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/profiles", produces = MediaType.APPLICATION_JSON_VALUE)
@AllArgsConstructor
@Validated
@Tag(
        name = "Profiles CRUD REST APIs",
        description = "APIs to CREATE, UPDATE, FETCH, and DELETE profile information"
)
public class ProfilesController {

    private IProfilesService profilesService;

    @Operation(summary = "Create Profile")
    @ApiResponses(
            {
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(
                            responseCode = "500", description = "Internal Server Error",
                            content = @Content(schema = @Schema(implementation = ErrorResponseDto.class))
                    )
            }
    )
    @PostMapping("/create")
    public ResponseEntity<ResponseDto> createProfile(@Valid @RequestBody ProfileDto profileDto) {
        profilesService.createProfile(profileDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ProfilesConstants.STATUS_201, ProfilesConstants.MESSAGE_201));
    }

    @Operation(summary = "Fetch Profile by Phone")
    @GetMapping("/fetch")
    public ResponseEntity<ProfileDto> fetchProfile(
            @RequestParam("phone")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits") String phone
    ) {
        ProfileDto dto = profilesService.fetchProfile(phone);
        return dto != null
                ? ResponseEntity.ok(dto)
                : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Update Profile")
    @PutMapping("/update")
    public ResponseEntity<ResponseDto> updateProfile(@Valid @RequestBody ProfileDto profileDto) {
        boolean updated = profilesService.updateProfile(profileDto);
        return ResponseEntity.status(updated ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(
                        updated ? ProfilesConstants.STATUS_200 : ProfilesConstants.STATUS_417,
                        updated ? ProfilesConstants.MESSAGE_200 : ProfilesConstants.MESSAGE_417_UPDATE
                ));
    }

    @Operation(summary = "Delete Profile")
    @DeleteMapping("/delete")
    public ResponseEntity<ResponseDto> deleteProfile(
            @RequestParam("phone")
            @Pattern(regexp = "(^$|[0-9]{10})", message = "Phone number must be 10 digits") String phone
    ) {
        boolean deleted = profilesService.deleteProfile(phone);
        return ResponseEntity.status(deleted ? HttpStatus.OK : HttpStatus.EXPECTATION_FAILED)
                .body(new ResponseDto(
                        deleted ? ProfilesConstants.STATUS_200 : ProfilesConstants.STATUS_417,
                        deleted ? ProfilesConstants.MESSAGE_200 : ProfilesConstants.MESSAGE_417_DELETE
                ));
    }
}
