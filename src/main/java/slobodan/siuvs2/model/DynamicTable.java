package slobodan.siuvs2.model;

import slobodan.siuvs2.shared.NumberHelper;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.*;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
@Entity
@Table(name = "dynamic_table")
public class DynamicTable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "table_id")
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

    @OneToMany(mappedBy = "dynamicTable")
    private List<DynamicRow> rows = new ArrayList<>();

    @Column(name = "description")
    private String description;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DynamicRow> getRows() {
        processRowSort();
        calculateRowSpanForAggregateColumns();
        determineValuesForEnumColumns();
        determineValuesForSumColumns();
        return rows;
    }

    public void setRows(List<DynamicRow> rows) {
        this.rows = rows;
    }

    public void addRow(DynamicRow dynamicRow) {
        this.rows.add(dynamicRow);
    }

    public CustomTableDefinition getCustomTableDefinition() {
        return customTableDefinition;
    }

    public void setCustomTableDefinition(CustomTableDefinition customTableDefinition) {
        this.customTableDefinition = customTableDefinition;
    }

    public int getMaxRowOrder() {
        int order = 0;
        for (DynamicRow row : getRows()) {
            if (row.getOrder() > order) {
                order = row.getOrder();
            }
        }
        return order;
    }

    private void determineValuesForEnumColumns() {
        int currentRowIndex = 0;
        while (currentRowIndex < rows.size()) {
            DynamicRow row = rows.get(currentRowIndex);
            for (DynamicData data : row.getData()) {
                if (data.getColumn().getType().equals(TableColumnTypes.ENUM)) {
                    int enumId = Integer.parseInt(data.getValue());
                    TableColumnValue enumValue = findValueById(data.getColumn().getValues(), enumId);
                    if (enumValue != null) {
                        data.setVirtualValue(enumValue.getValue());
                    }
                }
            }
            currentRowIndex++;
        }
    }

    private TableColumnValue findValueById(List<TableColumnValue> values, int id) {
        TableColumnValue found = null;
        for (TableColumnValue value : values) {
            if (value.getId() == id) {
                found = value;
                break;
            }
        }
        return found;
    }

    private void determineValuesForSumColumns() {
        int currentRowIndex = 0;
        while (currentRowIndex < rows.size()) {
            DynamicRow row = rows.get(currentRowIndex);
            for (DynamicData data : row.getData()) {
                if (data.getColumn().getType().equals(TableColumnTypes.SUM)) {
                    List<TableColumnSum> sumColumns = data.getColumn().getSumColumns();
                    BigDecimal sumValue = getSumColumnValue(row, sumColumns);
                    data.setVirtualValue(sumValue.toString());
                } else if (data.getColumn().getType().equals(TableColumnTypes.AUTOSUM)) {
                    data.setVirtualValue(getAutoSumColumnValue(row).toString());
                }
            }
            currentRowIndex++;
        }
    }

    private BigDecimal getSumColumnValue(DynamicRow row, List<TableColumnSum> sumColumns) {
        BigDecimal result = new BigDecimal(0);
        List<TableColumn> columnsToSum = new ArrayList<TableColumn>();
        for (TableColumnSum tableColumnSum : sumColumns) {
            columnsToSum.add(tableColumnSum.getSumColumn());
        }
        for (DynamicData data : row.getData()) {
            if (columnsToSum.contains(data.getColumn())) {
                result = NumberHelper.addNumberStringToNumber(result, data.getValue());
            }
        }
        return result;
    }

    private BigDecimal getAutoSumColumnValue(DynamicRow row) {
        BigDecimal result = new BigDecimal(0);
        for (DynamicData data : row.getData()) {
            if (data.getColumn().getType().equals(TableColumnTypes.NUMBER)) {
                result = NumberHelper.addNumberStringToNumber(result, data.getValue());
            }
        }
        return result;
    }

    private void processRowSort() {
        Comparator<DynamicRow> comparator = (row1, row2) -> {
            int o1GroupOrder = 0;
            int o2GroupOrder = 0;
            DynamicGroupRow o1Group = row1.getGroupRow();
            DynamicGroupRow o2Group = row2.getGroupRow();
            if (o1Group != null) {
                o1GroupOrder = o1Group.getOrder();
            }
            if (o2Group != null) {
                o2GroupOrder = o2Group.getOrder();
            }
            if (o1GroupOrder == o2GroupOrder) {
                if (row1.getOrder() == row2.getOrder()) {
                    return 0;
                } else {
                    return row1.getOrder() > row2.getOrder() ? 1 : -1;
                }
            } else {
                return o1GroupOrder > o2GroupOrder ? 1 : -1;
            }
        };
        rows.sort(comparator);
    }

    private void calculateRowSpanForAggregateColumns() {
        int currentRowIndex = 0;
        int rowSpan = 0;
        DynamicRow firstRowInGroup = null;
        while (currentRowIndex < rows.size()) {
            DynamicRow currentRow = rows.get(currentRowIndex);
            if (bothRowsAreStandard(firstRowInGroup, currentRow)) {
                rowSpan = 0;
            } else if (shouldStartNewCount(firstRowInGroup, currentRow)) {
                firstRowInGroup = currentRow;
                rowSpan = 1;
            } else if (shouldStopCounting(firstRowInGroup, currentRow)) {
                writeRowSpan(firstRowInGroup, rowSpan);
                rowSpan = 0;
                firstRowInGroup = null;
            } else if (shouldIncrementCount(firstRowInGroup, currentRow)) {
                rowSpan++;
                writeRowSpan(currentRow, -1);
            } else if (shouldStartCountingDifferentGroup(firstRowInGroup, currentRow)) {
                writeRowSpan(firstRowInGroup, rowSpan);
                firstRowInGroup = currentRow;
                rowSpan = 1;
            }
            currentRowIndex++;
            if (currentRowIndex >= rows.size() && rowSpan > 0) {
                writeRowSpan(firstRowInGroup, rowSpan);
            }
        }
    }

    private boolean bothRowsAreStandard(DynamicRow first, DynamicRow second) {
        return first == null && second != null && second.getGroupRow() == null;
    }

    private boolean shouldStartNewCount(DynamicRow first, DynamicRow second) {
        return first == null && second != null && second.getGroupRow() != null;
    }

    private boolean shouldStopCounting(DynamicRow first, DynamicRow second) {
        return first != null && second != null && second.getGroupRow() == null;
    }

    private boolean shouldIncrementCount(DynamicRow first, DynamicRow second) {
        return first != null && second != null && first.getGroupRow() != null
                && first.getGroupRow().equals(second.getGroupRow());
    }

    private boolean shouldStartCountingDifferentGroup(DynamicRow first, DynamicRow second) {
        return first != null && second != null && first.getGroupRow() != null
                && !first.getGroupRow().equals(second.getGroupRow());
    }

    private void writeRowSpan(DynamicRow row, int rowSpan) {
        int index = 0;
        for (DynamicData data : row.getData()) {
            if (data.getColumn().getType().equals(TableColumnTypes.AGGREGATE)
                    || data.getColumn().getType().equals(TableColumnTypes.PART)) {
                data.setRowSpan(rowSpan);
                if (rowSpan > 0) {
                    data.setVirtualValue(row.getGroupRow().getData().get(index).getValue());
                }
                index++;
            }
        }
    }

}
