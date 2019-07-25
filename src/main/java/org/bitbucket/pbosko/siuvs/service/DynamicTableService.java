package org.bitbucket.pbosko.siuvs.service;

import org.bitbucket.pbosko.siuvs.model.*;
import org.bitbucket.pbosko.siuvs.shared.NumberHelper;
import org.bitbucket.pbosko.siuvs.shared.SiuvsException;
import org.bitbucket.pbosko.siuvs.valueObject.CustomTableDefinitionId;
import org.bitbucket.pbosko.siuvs.valueObject.TableDefinitionId;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public interface DynamicTableService {

    DynamicTable findByTableDefinitionIdAndClient(TableDefinitionId tableDefinitionId, Client client);

    DynamicTable findByTableDefinitionIdAndCustomTableDefinitionIdAndClient(
            TableDefinitionId tableDefinitionId,
            CustomTableDefinitionId customTableDefinitionId,
            Client client
    );

    void addRow(DynamicTable dynamicTable, DynamicRow dynamicRow, Client client) throws SiuvsException;

    void save(DynamicTable dynamicTable);

    FooterRow getFooter(DynamicTable dynamicTable, User user);

    class FooterRow {

        private List<FooterField> fields = new ArrayList<>();

        public List<FooterField> getFields() {
            return fields;
        }

        void setFields(List<FooterField> fields) {
            this.fields = fields;
        }

    }

    class FooterField {

        private BigDecimal value = new BigDecimal(0);

        private int colSpan = 1;

        private TableColumn column;

        FooterField(TableColumn column) {
            setColumn(column);
        }

        public BigDecimal getValue() {
            return value;
        }

        void setValue(BigDecimal value) {
            this.value = value;
        }

        void addValue(String addend) {
            value = NumberHelper.addNumberStringToNumber(value, addend);
        }

        public int getColSpan() {
            return colSpan;
        }

        void setColSpan(int colSpan) {
            this.colSpan = colSpan;
        }

        void incColSpan() {
            this.colSpan++;
        }

        public TableColumn getColumn() {
            return column;
        }

        public void setColumn(TableColumn column) {
            this.column = column;
        }

        public String getDisplayValue() {
            List<TableColumnTypes> numericTypes = new ArrayList<TableColumnTypes>();
            numericTypes.add(TableColumnTypes.NUMBER);
            numericTypes.add(TableColumnTypes.SUM);
            numericTypes.add(TableColumnTypes.AUTOSUM);
            String result = "";
            if (column.getType() != null && numericTypes.contains(column.getType())) {
                result = this.value.toString();
            }
            return result;
        }
    }

}
