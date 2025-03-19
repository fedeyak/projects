package com.example.student_organiser.listeners;

import com.example.student_organiser.events.AddStudentEvent;
import com.example.student_organiser.events.RemoveStudentEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class StudentEventListener {

    @EventListener
    public void addStudentListener(AddStudentEvent addStudentEvent) {
        System.out.println("Student added: " + addStudentEvent.student());
    }

    @EventListener/*(condition = "#event.success")*/
    public void removeStudentListener(RemoveStudentEvent removeStudentEvent) {
        System.out.println("Student removed. Id: " + removeStudentEvent.id());
    }
}
