package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TAppointmentInfoEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.awt.print.Pageable;
import java.util.List;


public interface OnLineBookClassesRepository extends CrudRepository<TAppointmentInfoEntity,Integer> {

    TAppointmentInfoEntity findByUserId(Integer userId);

}
