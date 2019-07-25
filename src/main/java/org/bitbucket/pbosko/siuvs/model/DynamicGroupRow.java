package org.bitbucket.pbosko.siuvs.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dynamic_group_row")
public class DynamicGroupRow {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "group_row_id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "table_definition_id")
    private TableDefinition tableDefinition;

    @ManyToOne
    @JoinColumn(name = "custom_table_definition_id")
    private CustomTableDefinition customTableDefinition;

    @Column(name = "`order`")
    private int order;

    @OneToMany(mappedBy = "groupRow")
    private List<DynamicGroupData> data = new ArrayList<>();

    public int getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public TableDefinition getTableDefinition() {
        return tableDefinition;
    }

    public void setTableDefinition(TableDefinition tableDefinition) {
        this.tableDefinition = tableDefinition;
    }

    public CustomTableDefinition getCustomTableDefinition() {
        return customTableDefinition;
    }

    public void setCustomTableDefinition(CustomTableDefinition customTableDefinition) {
        this.customTableDefinition = customTableDefinition;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public List<DynamicGroupData> getData() {
        return data;
    }

    public void setData(List<DynamicGroupData> data) {
        this.data = data;
    }
}
