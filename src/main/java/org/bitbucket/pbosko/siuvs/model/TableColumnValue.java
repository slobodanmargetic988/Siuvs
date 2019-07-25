package org.bitbucket.pbosko.siuvs.model;

import javax.persistence.*;

@Entity
@Table(name = "table_column_value")
public class TableColumnValue {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "column_id")
    private TableColumn column;

    @Column(name = "value")
    private String value;

    public int getId() {
        return id;
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
}
