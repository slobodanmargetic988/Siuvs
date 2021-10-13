package slobodan.siuvs2.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dynamic_row")
@EntityListeners(AuditingEntityListener.class)
public class DynamicRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "row_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "table_id")
    private DynamicTable dynamicTable;

    @OneToMany(mappedBy = "row")
    private List<DynamicData> data = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "group_row_id")
    private DynamicGroupRow groupRow;

    @Column(name = "`order`")
    private int order;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @CreatedDate
    @Column(name = "created_on")
    private LocalDateTime createdOn;

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name = "modified_by")
    private User modifiedBy;

    @LastModifiedDate
    @Column(name = "modified_on")
    private LocalDateTime modifiedOn;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DynamicTable getDynamicTable() {
        return dynamicTable;
    }

    public void setDynamicTable(DynamicTable dynamicTable) {
        this.dynamicTable = dynamicTable;
    }

    public List<DynamicData> getData() {
        return data;
    }

    public void setData(List<DynamicData> data) {
        this.data = data;
    }

    public DynamicGroupRow getGroupRow() {
        return groupRow;
    }

    public void setGroupRow(DynamicGroupRow groupRow) {
        this.groupRow = groupRow;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(LocalDateTime createdOn) {
        this.createdOn = createdOn;
    }

    public User getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(User modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public LocalDateTime getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(LocalDateTime modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public List<DynamicData> getDataForColumns(List<TableColumn> columns) {
        List<DynamicData> resultData = new ArrayList<>();
        for (TableColumn column : columns) {
            DynamicData foundDataRow = null;
            for (DynamicData dataRow : this.getData()) {
                if (dataRow.getColumn() == column) {
                    foundDataRow = dataRow;
                    break;
                }
            }
            if (foundDataRow == null) {
                foundDataRow = new DynamicData();
                foundDataRow.setValue("");
                foundDataRow.setVirtualValue("");
                foundDataRow.setColumn(column);
            }
            resultData.add(foundDataRow);
        }
        return resultData;
    }
    
        public String getValueForColumn(TableColumn column) {
            for (DynamicData dataFromThisRow : this.getData()) {
                if (dataFromThisRow.getColumn().getId() == column.getId()) {
                    return dataFromThisRow.getValue();    
    }
}
            return "/"; }


}
