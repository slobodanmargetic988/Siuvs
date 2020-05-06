package slobodan.siuvs2.service;

import slobodan.siuvs2.model.Client;
import slobodan.siuvs2.model.Page;
import slobodan.siuvs2.model.TableDefinition;
import slobodan.siuvs2.model.User;
import slobodan.siuvs2.valueObject.TableDefinitionId;

import java.util.ArrayList;
import java.util.List;

public interface TableDefinitionService {

    List<TableDefinition> findByPage(Page page);

    TableDefinition findOne(TableDefinitionId tableDefinitionId);

    List<HeaderRow> getHeader(TableDefinition tableDefinition, Client client, User user);

    boolean showActionsColumn(User user);

    class HeaderRow {

        private List<HeaderColumn> columns = new ArrayList<>();

        public List<HeaderColumn> getColumns() {
            return columns;
        }

        void addColumn(HeaderColumn headerColumn) {
            columns.add(headerColumn);
        }
    }

    class HeaderColumn {

        private String title;

        private int rowSpan;

        private int colSpan;

        HeaderColumn(int rowSpan, int colSpan, String title) {
            setRowSpan(rowSpan);
            setColSpan(colSpan);
            setTitle(title);
        }

        public String getTitle() {
            return title;
        }

        void setTitle(String title) {
            this.title = title;
        }

        public int getRowSpan() {
            return rowSpan;
        }

        void setRowSpan(int rowSpan) {
            this.rowSpan = rowSpan;
        }

        public int getColSpan() {
            return colSpan;
        }

        void setColSpan(int colSpan) {
            this.colSpan = colSpan;
        }
    }

}
