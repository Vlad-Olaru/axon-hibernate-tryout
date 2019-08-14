package com.example.axon.domain;

import com.example.axon.model.Student;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
public class CourseDTO {
    private List<Student> students;
}
