package com.demo.management_api.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.util.Objects;

@Getter
@Setter
@Builder
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
public class CalendarCoursesStudentId implements java.io.Serializable {
    private static final long serialVersionUID = 5966752225649570717L;
    @NotNull
    @Column(name = "calendar_course_id", nullable = false)
    private Integer calendarCourseId;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CalendarCoursesStudentId entity = (CalendarCoursesStudentId) o;
        return Objects.equals(this.studentId, entity.studentId) &&
                Objects.equals(this.calendarCourseId, entity.calendarCourseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(studentId, calendarCourseId);
    }

}