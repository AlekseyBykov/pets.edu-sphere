package dev.abykov.pets.edusphere.profiles.service;

import dev.abykov.pets.edusphere.profiles.dto.ProfileDto;

import java.util.UUID;

public interface IProfilesService {

    void createProfile(ProfileDto profileDto);

    ProfileDto fetchProfile(String phone);

    ProfileDto fetchProfileById(UUID id);

    boolean updateProfile(ProfileDto profileDto);

    boolean deleteProfile(String phone);
}
