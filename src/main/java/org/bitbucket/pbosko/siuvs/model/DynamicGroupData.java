package org.bitbucket.pbosko.siuvs.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "dynamic_group_data")
@EntityListeners(AuditingEntityListener.class)
public class DynamicGroupData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_data_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "group_row_id")
    private DynamicGroupRow groupRow;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private TableColumn column;

    @Column(name = "value")
    private String value;

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

    public DynamicGroupRow getGroupRow() {
        return groupRow;
    }

    public void setGroupRow(DynamicGroupRow groupRow) {
        this.groupRow = groupRow;
    }

    public TableColumn getColumn() {
        return column;
    }

    public void setColumn(TableColumn column) {
        this.column = column;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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
}
