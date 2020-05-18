package slobodan.siuvs2.valueObject;

public class OpstinaID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public OpstinaID(String value) {
        this.value = Integer.parseInt(value);
    }

    public OpstinaID(Integer value) {
        this.value = value;
    }

}