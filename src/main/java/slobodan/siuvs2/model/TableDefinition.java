package slobodan.siuvs2.model;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "table_definition")
public class TableDefinition {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id")
    private int id;

    @Column(name = "title")
    @NotEmpty(message = "Молимо унесите назив табеле")
    private String title;

    @OneToMany(mappedBy = "table")
    @OrderBy("order")
    private List<TableColumn> columns;

    @ManyToOne
    @JoinColumn(name = "page_id")
    private Page page;

    @Column(name = "order")
    private int order;

    @Column(name = "user_defined")
    private boolean userDefined;

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TableColumn> getColumns() {
        return columns;
    }

    public List<TableColumn> getColumns(Client client) {
        List<TableColumn> result = new ArrayList<TableColumn>();
        for (TableColumn column : getColumns()) {
            if (column.getClient() == null || column.getClient().getId() == client.getId()) {
                result.add(column);
            }
        }
        return result;
    }

    public List<TableColumn> getDataColumns(Client client) {
        List<TableColumn> result = new ArrayList<TableColumn>();
        for (TableColumn column : getColumns(client)) {
            if (!column.isDynamicRoot()) {
                result.add(column);
            }
        }
        return result;
    }

    public void setColumns(List<TableColumn> columns) {
        this.columns = columns;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isUserDefined() {
        return userDefined;
    }

    public void setUserDefined(boolean userDefined) {
        this.userDefined = userDefined;
    }

    public boolean hasAggregateColumns() {
        boolean result = false;
        for (TableColumn column : getColumns()) {
            if (column.getType().equals(TableColumnTypes.AGGREGATE)) {
                result = true;
                break;
            }
        }
        return result;
    }

    public List<TableColumn> getDynamicColumns() {
        List<TableColumn> result = new ArrayList<TableColumn>();
        for (TableColumn column : getColumns()) {
            if (column.isDynamic() && column.getParent() == null) {
                result.add(column);
            }
        }
        return result;
    }

    public boolean hasDynamicColumns() {
        boolean result = false;
        for (TableColumn column : getColumns()) {
            if (column.isDynamic()) {
                result = true;
                break;
            }
        }
        return result;
    }

}
