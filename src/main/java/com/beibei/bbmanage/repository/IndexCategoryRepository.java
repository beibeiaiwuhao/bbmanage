package com.beibei.bbmanage.repository;

import com.beibei.bbmanage.entity.TIndexCategoryEntity;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Map;


public interface IndexCategoryRepository extends CrudRepository<TIndexCategoryEntity,Integer> {

    @Query("select  t.pageCode from " +
            "TIndexCategoryEntity t")
    List<String> findAllPageCode();

}
