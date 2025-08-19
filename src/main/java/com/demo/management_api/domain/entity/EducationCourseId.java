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
public class EducationCourseId implements java.io.Serializable {
    private static final long serialVersionUID = 2297981625480710526L;
    @NotNull
    @Column(name = "education_id", nullable = false)
    private Integer educationId;

    @NotNull
    @Column(name = "course_id", nullable = false)
    private Integer courseId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        EducationCourseId entity = (EducationCourseId) o;
        return Objects.equals(this.educationId, entity.educationId) &&
                Objects.equals(this.courseId, entity.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(educationId, courseId);
    }

}