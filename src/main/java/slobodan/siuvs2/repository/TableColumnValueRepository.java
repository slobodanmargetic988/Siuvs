package slobodan.siuvs2.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import slobodan.siuvs2.model.TableColumn;

import slobodan.siuvs2.model.TableColumnValue;

public interface TableColumnValueRepository extends JpaRepository<TableColumnValue, Integer> {

    TableColumnValue findOne(Integer tableColumnValueId);
    int countByColumn(TableColumn tableColumn);

    @Modifying
    @Query(value = "INSERT INTO `table_column_value` (`column_id`, `order`,`value`) VALUES ( :columnId, :order, :value)", nativeQuery = true)
    void addData(@Param("columnId") Integer columnId,@Param("order") Integer order, @Param("value") String value);
}
