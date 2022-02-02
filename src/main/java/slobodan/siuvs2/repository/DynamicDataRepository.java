package slobodan.siuvs2.repository;

import java.util.List;
import javax.transaction.Transactional;
import slobodan.siuvs2.model.DynamicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import slobodan.siuvs2.model.DynamicRow;
import slobodan.siuvs2.model.DynamicTable;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
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
    
    @Transactional
    @Query(value = "SELECT * FROM dynamic_data WHERE row_id IN (SELECT row_id FROM dynamic_row where table_id IN (SELECT table_id FROM dynamic_table where table_definition_id= :table_definition_id and client_id= :client_id)) limit 1", nativeQuery = true)
    DynamicData checkIfExists(@Param("table_definition_id") Integer table_definition_id,@Param("client_id") Integer client_id);
    
}
