package com.example.axon.domain.commands;

import com.example.axon.domain.CourseDTO;
import lombok.Value;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Value
public class CreateCourseCommand {
    @TargetAggregateIdentifier
    private String id;
    private CourseDTO course;
}
