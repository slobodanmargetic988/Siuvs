package slobodan.siuvs2.valueObject;

public class TableColumnId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public TableColumnId(String value) {
        this.value = Integer.parseInt(value);
    }

    public TableColumnId(Integer value) {
        this.value = value;
    }
    
}
