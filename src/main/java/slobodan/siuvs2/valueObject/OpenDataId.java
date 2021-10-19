package slobodan.siuvs2.valueObject;

public class OpenDataId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public OpenDataId(String value) {
        this.value = Integer.parseInt(value);
    }

    public OpenDataId(Integer value) {
        this.value = value;
    }

}
