package dev.abykov.pets.edusphere.mapper;

import dev.abykov.pets.edusphere.dto.ProfileDto;
import dev.abykov.pets.edusphere.entity.Profile;

public class ProfileMapper {

    public static ProfileDto toDto(Profile profile) {
        ProfileDto dto = new ProfileDto();
        dto.setName(profile.getName());
        dto.setEmail(profile.getEmail());
        dto.setPhone(profile.getPhone());

        return dto;
    }

    public static Profile toEntity(ProfileDto dto) {
        Profile profile = new Profile();
        profile.setName(dto.getName());
        profile.setEmail(dto.getEmail());
        profile.setPhone(dto.getPhone());

        return profile;
    }

    public static Profile updateEntity(ProfileDto dto, Profile existing) {
        existing.setName(dto.getName());
        existing.setEmail(dto.getEmail());

        return existing;
    }
}
