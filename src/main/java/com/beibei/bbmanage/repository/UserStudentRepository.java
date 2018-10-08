package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TUserStudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserStudentRepository extends CrudRepository<TUserStudentEntity,Integer> {
}
