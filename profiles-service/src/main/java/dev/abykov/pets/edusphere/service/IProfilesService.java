package dev.abykov.pets.edusphere.service;

import dev.abykov.pets.edusphere.dto.ProfileDto;

public interface IProfilesService {

    void createProfile(ProfileDto profileDto);

    ProfileDto fetchProfile(String phone);

    boolean updateProfile(ProfileDto profileDto);

    boolean deleteProfile(String phone);
}
