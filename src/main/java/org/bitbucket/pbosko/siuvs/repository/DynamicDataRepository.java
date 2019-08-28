package org.bitbucket.pbosko.siuvs.repository;

import java.util.List;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.DynamicData;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DynamicDataRepository extends JpaRepository<DynamicData, Integer> {

    @Modifying
    @Query(value = "INSERT INTO dynamic_data (`row_id`, `column_id`, `value`) VALUES (:rowId, :columnId, :value)", nativeQuery = true)
    void addData(@Param("rowId") Integer rowId, @Param("columnId") Integer columnId, @Param("value") String value);
 
    @Modifying
    @Query(value = "SELECT * FROM dynamic_data WHERE value LIKE %:request%", nativeQuery = true)
    List<DynamicData> findByValue(@Param("request")String request);
    
    @Modifying
    @Query(value = "SELECT * FROM dynamic_data WHERE row_id = :rowId", nativeQuery = true)
    List<DynamicData> findById(@Param("rowId")Integer rowId);
}
