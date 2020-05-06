package slobodan.siuvs2.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@IdClass(TableColumnSum.PK.class)
@Table(name = "table_column_sum")
public class TableColumnSum {

    @Id
    @ManyToOne
    @JoinColumn(name = "column_id")
    private TableColumn column;

    @Id
    @ManyToOne
    @JoinColumn(name = "sum_column_id")
    private TableColumn sumColumn;

    public static class PK implements Serializable {
        private TableColumn column;
        private TableColumn sumColumn;

        public TableColumn getColumn() {
            return column;
        }

        public void setColumn(TableColumn column) {
            this.column = column;
        }

        public TableColumn getSumColumn() {
            return sumColumn;
        }

        public void setSumColumn(TableColumn sumColumn) {
            this.sumColumn = sumColumn;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            PK pk = (PK) o;

            if (!column.equals(pk.column)) return false;
            return sumColumn.equals(pk.sumColumn);
        }

        @Override
        public int hashCode() {
            int result = column.hashCode();
            result = 31 * result + sumColumn.hashCode();
            return result;
        }
    }

    public TableColumn getColumn() {
        return column;
    }

    public void setColumn(TableColumn column) {
        this.column = column;
    }

    public TableColumn getSumColumn() {
        return sumColumn;
    }

    public void setSumColumn(TableColumn sumColumn) {
        this.sumColumn = sumColumn;
    }
}
