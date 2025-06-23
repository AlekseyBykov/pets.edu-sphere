package dev.abykov.pets.edusphere.courses.service.impl;

import dev.abykov.pets.edusphere.courses.client.ProfileDto;
import dev.abykov.pets.edusphere.courses.client.ProfileFeignClient;
import dev.abykov.pets.edusphere.courses.constants.ErrorMessages;
import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.dto.CourseWithTeacherDTO;
import dev.abykov.pets.edusphere.courses.entity.Course;
import dev.abykov.pets.edusphere.courses.exception.CourseNotFoundException;
import dev.abykov.pets.edusphere.courses.mapper.CourseMapper;
import dev.abykov.pets.edusphere.courses.repository.CourseRepository;
import dev.abykov.pets.edusphere.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final ProfileFeignClient profileFeignClient;

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public CourseDto getById(UUID id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(ErrorMessages.COURSE_NOT_FOUND, id)
                ));
        return courseMapper.toDto(course);
    }

    @Override
    public CourseWithTeacherDTO getCourseWithTeacher(UUID courseId) {
        Course course = courseRepository.findById(courseId).orElseThrow();
        ProfileDto teacher = profileFeignClient.fetchProfileById(course.getTeacherId());

        return new CourseWithTeacherDTO(
                course.getId(),
                course.getTitle(),
                teacher
        );
    }

    @Override
    public CourseDto create(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto update(UUID id, CourseDto dto) {
        Course existing = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(ErrorMessages.COURSE_NOT_FOUND, id)
                ));
        existing.setTitle(dto.getTitle());
        existing.setDescription(dto.getDescription());
        existing.setTeacherId(dto.getTeacherId());
        return courseMapper.toDto(courseRepository.save(existing));
    }

    @Override
    public void delete(UUID id) {
        courseRepository.deleteById(id);
    }
}
