package dev.abykov.pets.edusphere.profiles.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.abykov.pets.edusphere.profiles.constants.ProfilesConstants;
import dev.abykov.pets.edusphere.profiles.dto.ProfileDto;
import dev.abykov.pets.edusphere.profiles.service.IProfilesService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ActiveProfiles("test-mvc")
@WebMvcTest(controllers = ProfilesController.class)
@ContextConfiguration(classes = ProfilesTestConfiguration.class)
class ProfilesControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private IProfilesService profilesService;

    @Test
    @DisplayName("when POST /api/profiles/create with valid payload then return 201 with created profile")
    void whenValidProfilePosted_thenReturnsCreated() throws Exception {
        ProfileDto profile = new ProfileDto();
        profile.setName("Test User");
        profile.setEmail("test@example.com");
        profile.setPhone("9123456789");

        Mockito.doNothing()
                .when(profilesService)
                .createProfile(any(ProfileDto.class));

        mockMvc.perform(
                        post("/api/profiles/create")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(profile))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.statusCode").value("201"))
                .andExpect(jsonPath("$.statusMessage").value("Profile created successfully"))
                .andDo(print()); // for debugging

        Mockito.verify(profilesService, times(1))
                .createProfile(any(ProfileDto.class));
    }

    @Test
    @DisplayName("when GET /api/profiles/fetch with valid phone then return 200 with profile")
    void whenValidPhoneProvided_thenReturnsProfile() throws Exception {
        String phone = "9123456789";

        ProfileDto profile = new ProfileDto();
        profile.setName("John Doe");
        profile.setEmail("john@example.com");
        profile.setPhone(phone);

        Mockito.when(profilesService.fetchProfile(phone)).thenReturn(profile);

        mockMvc.perform(
                        get("/api/profiles/fetch")
                                .param("phone", phone)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(profile.getName()))
                .andExpect(jsonPath("$.phone").value(profile.getPhone()))
                .andExpect(jsonPath("$.email").value(profile.getEmail()));
    }

    @Test
    @DisplayName("when GET /api/profiles/fetch with unknown phone then return 404 Not Found")
    void whenUnknownPhoneProvided_thenReturnsNotFound() throws Exception {
        String unknownPhone = "9999999999";

        Mockito.when(profilesService.fetchProfile(unknownPhone)).thenReturn(null);

        mockMvc.perform(
                        get("/api/profiles/fetch")
                                .param("phone", unknownPhone)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("when valid profile is PUT to /api/profiles/update then return 200 with success message")
    void whenValidProfileUpdated_thenReturnsSuccess() throws Exception {
        ProfileDto profile = new ProfileDto();
        profile.setName("Test User");
        profile.setEmail("test@example.com");
        profile.setPhone("1234567890");

        Mockito.when(profilesService.updateProfile(any(ProfileDto.class))).thenReturn(true);

        mockMvc.perform(
                        put("/api/profiles/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(ProfilesConstants.STATUS_200))
                .andExpect(jsonPath("$.statusMessage").value(ProfilesConstants.MESSAGE_200));
    }

    @Test
    @DisplayName("when profile update fails then return 417 with failure message")
    void whenProfileUpdateFails_thenReturnsFailure() throws Exception {
        ProfileDto profile = new ProfileDto();
        profile.setName("Unknown User");
        profile.setEmail("test@example.com");
        profile.setPhone("1234567890");

        Mockito.when(profilesService.updateProfile(any(ProfileDto.class))).thenReturn(false);

        mockMvc.perform(
                        put("/api/profiles/update")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(profile)))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value(ProfilesConstants.STATUS_417))
                .andExpect(jsonPath("$.statusMessage").value(ProfilesConstants.MESSAGE_417_UPDATE));
    }

    @Test
    @DisplayName("when valid phone is DELETE /api/profiles/delete then return 200 with success message")
    void whenValidPhoneDeleted_thenReturnsSuccess() throws Exception {
        String phone = "1234567890";

        Mockito.when(profilesService.deleteProfile(phone)).thenReturn(true);

        mockMvc.perform(delete("/api/profiles/delete")
                        .param("phone", phone))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.statusCode").value(ProfilesConstants.STATUS_200))
                .andExpect(jsonPath("$.statusMessage").value(ProfilesConstants.MESSAGE_200));
    }

    @Test
    @DisplayName("when deletion fails then return 417 with failure message")
    void whenPhoneDeletionFails_thenReturnsFailure() throws Exception {
        String phone = "1234567890";

        Mockito.when(profilesService.deleteProfile(phone)).thenReturn(false);

        mockMvc.perform(delete("/api/profiles/delete")
                        .param("phone", phone))
                .andExpect(status().isExpectationFailed())
                .andExpect(jsonPath("$.statusCode").value(ProfilesConstants.STATUS_417))
                .andExpect(jsonPath("$.statusMessage").value(ProfilesConstants.MESSAGE_417_DELETE));
    }
}
