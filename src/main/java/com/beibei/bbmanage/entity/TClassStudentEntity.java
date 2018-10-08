package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_class_student", schema = "bbmanage", catalog = "")
public class TClassStudentEntity {
    private Integer id;
    private Integer classId;
    private Integer studentId;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "class_id")
    public Integer getClassId() {
        return classId;
    }

    public void setClassId(Integer classId) {
        this.classId = classId;
    }

    @Basic
    @Column(name = "student_id")
    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TClassStudentEntity that = (TClassStudentEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(studentId, that.studentId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, classId, studentId);
    }
}
