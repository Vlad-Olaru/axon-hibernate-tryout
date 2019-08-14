package com.example.axon.domain.events;

import com.example.axon.domain.CourseDTO;
import lombok.Value;

@Value
public class CreateCourseEvent {
    private String id;
    private CourseDTO course;
}
