package dev.abykov.pets.edusphere.profiles.service.impl;

import dev.abykov.pets.edusphere.profiles.dto.ProfileDto;
import dev.abykov.pets.edusphere.profiles.entity.Profile;
import dev.abykov.pets.edusphere.profiles.exception.ResourceAlreadyExistsException;
import dev.abykov.pets.edusphere.profiles.exception.ResourceNotFoundException;
import dev.abykov.pets.edusphere.profiles.mapper.ProfileMapper;
import dev.abykov.pets.edusphere.profiles.repository.ProfileRepository;
import dev.abykov.pets.edusphere.profiles.service.IProfilesService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IProfilesServiceImpl implements IProfilesService {

    private final ProfileRepository profileRepository;

    @Override
    public void createProfile(ProfileDto dto) {
        profileRepository.findByPhone(dto.getPhone()).ifPresent(profile -> {
            throw new ResourceAlreadyExistsException("Profile already exists for phone: " + dto.getPhone());
        });

        Profile profile = ProfileMapper.toEntity(dto);
        profileRepository.save(profile);
    }

    @Override
    public ProfileDto fetchProfile(String phone) {
        Profile profile = profileRepository.findByPhone(phone).orElseThrow(() ->
                new ResourceNotFoundException("Profile", "phone", phone)
        );
        return ProfileMapper.toDto(profile);
    }

    @Override
    public boolean updateProfile(ProfileDto dto) {
        Profile profile = profileRepository.findByPhone(dto.getPhone()).orElseThrow(() ->
                new ResourceNotFoundException("Profile", "phone", dto.getPhone())
        );
        Profile updated = ProfileMapper.updateEntity(dto, profile);
        profileRepository.save(updated);
        return true;
    }

    @Override
    public boolean deleteProfile(String phone) {
        Profile profile = profileRepository.findByPhone(phone).orElseThrow(() ->
                new ResourceNotFoundException("Profile", "phone", phone)
        );
        profileRepository.delete(profile);
        return true;
    }
}
