package slobodan.siuvs2.valueObject;

public class TableDefinitionId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public TableDefinitionId(String value) {
        this.value = Integer.parseInt(value);
    }

    public TableDefinitionId(Integer value) {
        this.value = value;
    }
    
}
