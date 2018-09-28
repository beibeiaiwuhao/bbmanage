package com.beibei.bbmanage.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "t_class_teacher", schema = "bbmanage", catalog = "")
public class TClassTeacherEntity {
    private Integer id;
    private Integer classId;
    private Integer teacherId;

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
    @Column(name = "teacher_id")
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TClassTeacherEntity that = (TClassTeacherEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(classId, that.classId) &&
                Objects.equals(teacherId, that.teacherId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, classId, teacherId);
    }
}
