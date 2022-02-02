package slobodan.siuvs2.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "table_column")
public class TableColumn implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "column_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "parent_column_id")
    private TableColumn parent;

    @Column(name = "`order`")
    private Integer order;

    @ManyToOne
    @JoinColumn(name = "table_definition_id")
    private TableDefinition table;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @Column(name = "title")
    private String title;

    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private TableColumnTypes type;

    @Column(name = "is_dynamic")
    private boolean isDynamic;

    @Column(name = "col_span")
    private int colSpan;

    @Column(name = "row2")
    private String row2;

    @Column(name = "col_span2")
    private int colSpan2;

    @Column(name = "row3")
    private String row3;

    @Column(name = "col_span3")
    private int colSpan3;

    @Column(name = "row4")
    private String row4;

    @OneToMany(mappedBy = "column")
    @OrderBy("order")
    private List<TableColumnValue> values;

    @OneToMany(mappedBy = "column")
    private List<TableColumnSum> sumColumns;

    public int getId() {
        return id;
    }

    public TableColumn getParent() {
        return parent;
    }

    public void setParent(TableColumn parent) {
        this.parent = parent;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public TableDefinition getTable() {
        return table;
    }

    public void setTable(TableDefinition table) {
        this.table = table;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TableColumnTypes getType() {
        return type;
    }

    public void setType(TableColumnTypes type) {
        this.type = type;
    }

    public boolean isDynamic() {
        return isDynamic;
    }

    public void setDynamic(boolean dynamic) {
        isDynamic = dynamic;
    }

    public boolean isDynamicRoot() {
        return isDynamic() && getParent() == null;
    }

    public int getColSpan() {
        return colSpan == 0 && title != null ? 1 : colSpan;
    }

    public void setColSpan(int colSpan) {
        this.colSpan = colSpan;
    }

    public String getRow2() {
        return row2;
    }

    public void setRow2(String row2) {
        this.row2 = row2;
    }

    public int getColSpan2() {
        return colSpan2;
    }

    public void setColSpan2(int colSpan2) {
        this.colSpan2 = colSpan2;
    }

    public String getRow3() {
        return row3;
    }

    public void setRow3(String row3) {
        this.row3 = row3;
    }

    public int getColSpan3() {
        return colSpan3;
    }

    public void setColSpan3(int colSpan3) {
        this.colSpan3 = colSpan3;
    }

    public String getRow4() {
        return row4;
    }

    public void setRow4(String row4) {
        this.row4 = row4;
    }

    public List<TableColumnValue> getValues() {
        return values;
    }

    public void setValues(List<TableColumnValue> values) {
        this.values = values;
    }

    public List<TableColumnSum> getSumColumns() {
        return sumColumns;
    }

    public void setSumColumns(List<TableColumnSum> sumColumns) {
        this.sumColumns = sumColumns;
    }

}
