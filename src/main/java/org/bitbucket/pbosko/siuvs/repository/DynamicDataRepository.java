package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DynamicDataRepository extends JpaRepository<DynamicData, Integer> {

    @Modifying
    @Query(value = "INSERT INTO dynamic_data (`row_id`, `column_id`, `value`) VALUES (:rowId, :columnId, :value)", nativeQuery = true)
    void addData(@Param("rowId") Integer rowId, @Param("columnId") Integer columnId, @Param("value") String value);

}
