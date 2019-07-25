package org.bitbucket.pbosko.siuvs.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(DynamicData.PK.class)
@Table(name = "dynamic_data")
public class DynamicData {

    @Id
    @ManyToOne
    @JoinColumn(name = "row_id")
    private DynamicRow row;

    @Id
    @ManyToOne
    @JoinColumn(name = "column_id")
    private TableColumn column;

    @Column(name = "value")
    private String value;

    @Transient
    private Integer rowSpan = 0;

    @Transient
    private String virtualValue;

    @Transient
    private Integer order = 0;

    public static class PK implements Serializable {

        private DynamicRow row;

        private TableColumn column;

        public DynamicRow getRow() {
            return row;
        }

        public void setRow(DynamicRow row) {
            this.row = row;
        }

        public TableColumn getColumn() {
            return column;
        }

        public void setColumn(TableColumn column) {
            this.column = column;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PK pk = (PK) o;

            if (!row.equals(pk.row)) return false;
            return column.equals(pk.column);
        }

        @Override
        public int hashCode() {
            int result = row.hashCode();
            result = 31 * result + column.hashCode();
            return result;
        }

    }

    public DynamicRow getRow() {
        return row;
    }

    public void setRow(DynamicRow row) {
        this.row = row;
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

    public Integer getRowSpan() {
        return rowSpan;
    }

    public void setRowSpan(Integer rowSpan) {
        this.rowSpan = rowSpan;
    }

    public String getVirtualValue() {
        return virtualValue;
    }

    public void setVirtualValue(String virtualValue) {
        this.virtualValue = virtualValue;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public boolean isEmpty() {
        return (getValue() == null || getValue().isEmpty()) && (getVirtualValue() == null || getVirtualValue().isEmpty());
    }
}
