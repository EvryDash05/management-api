package com.demo.management_api.domain.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "calendar_courses_students", schema = "db_management")
public class CalendarCoursesStudentEntity extends AuditableEntity {
    @EmbeddedId
    private CalendarCoursesStudentId id;

    @MapsId("calendarCourseId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "calendar_course_id", nullable = false)
    private CalendarCourseEntity calendarCourse;

    @MapsId("studentId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "student_id", nullable = false)
    private UserEntity student;

}