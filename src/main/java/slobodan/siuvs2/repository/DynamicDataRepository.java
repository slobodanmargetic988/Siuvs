package slobodan.siuvs2.repository;

import java.util.List;
import slobodan.siuvs2.model.DynamicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;

public interface DynamicDataRepository extends JpaRepository<DynamicData, Integer> {

    @Modifying
    @Query(value = "INSERT INTO dynamic_data (`row_id`, `column_id`, `value`) VALUES (:rowId, :columnId, :value)", nativeQuery = true)
    void addData(@Param("rowId") Integer rowId, @Param("columnId") Integer columnId, @Param("value") String value);

    @Modifying
    @Query(value = "SELECT * FROM dynamic_data WHERE value LIKE %:request%", nativeQuery = true)
    List<DynamicData> findByValue(@Param("request") String request);

    @Modifying
    @Query(value = "SELECT * FROM dynamic_data WHERE row_id = :rowId", nativeQuery = true)
    List<DynamicData> findById(@Param("rowId") Integer rowId);
    
    DynamicData findFirstByRow(DynamicRow dr);
}
