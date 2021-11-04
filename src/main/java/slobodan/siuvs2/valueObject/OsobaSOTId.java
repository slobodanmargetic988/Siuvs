package slobodan.siuvs2.valueObject;

public class OsobaSOTId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public OsobaSOTId(String value) {
        this.value = Integer.parseInt(value);
    }

    public OsobaSOTId(Integer value) {
        this.value = value;
    }

}