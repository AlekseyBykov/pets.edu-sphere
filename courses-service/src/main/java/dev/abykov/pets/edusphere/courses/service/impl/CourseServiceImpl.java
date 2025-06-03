package dev.abykov.pets.edusphere.courses.service.impl;

import dev.abykov.pets.edusphere.courses.constants.ErrorMessages;
import dev.abykov.pets.edusphere.courses.dto.CourseDto;
import dev.abykov.pets.edusphere.courses.entity.Course;
import dev.abykov.pets.edusphere.courses.exception.CourseNotFoundException;
import dev.abykov.pets.edusphere.courses.mapper.CourseMapper;
import dev.abykov.pets.edusphere.courses.repository.CourseRepository;
import dev.abykov.pets.edusphere.courses.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;

    @Override
    public List<CourseDto> getAll() {
        return courseRepository.findAll().stream()
                .map(courseMapper::toDto)
                .toList();
    }

    @Override
    public CourseDto getById(Long id) {
        Course course = courseRepository.findById(id)
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format(ErrorMessages.COURSE_NOT_FOUND, id)
                ));
        return courseMapper.toDto(course);
    }

    @Override
    public CourseDto create(CourseDto dto) {
        Course course = courseMapper.toEntity(dto);
        return courseMapper.toDto(courseRepository.save(course));
    }

    @Override
    public CourseDto update(Long id, CourseDto dto) {
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
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }
}
