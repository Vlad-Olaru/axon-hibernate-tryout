package com.example.axon.model;

import com.example.axon.domain.commands.CreateCourseCommand;
import com.example.axon.domain.events.CreateCourseEvent;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

@Data
@Entity
@Aggregate
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @AggregateMember
    @JoinColumn(name = "entityId")
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Student> students = new ArrayList<>();

    @CommandHandler
    private Course(CreateCourseCommand createCourseCommand) {
        apply(new CreateCourseEvent(createCourseCommand.getId(), createCourseCommand.getCourse()));
    }

    @EventHandler
    private void on(CreateCourseEvent event) {
        this.students = event.getCourse().getStudents();
    }

}
