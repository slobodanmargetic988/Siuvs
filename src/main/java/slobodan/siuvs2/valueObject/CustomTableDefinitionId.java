package slobodan.siuvs2.valueObject;

public class CustomTableDefinitionId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public CustomTableDefinitionId(String value) {
        this.value = Integer.parseInt(value);
    }

    public CustomTableDefinitionId(Integer value) {
        this.value = value;
    }
    
}