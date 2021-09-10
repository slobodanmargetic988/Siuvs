package slobodan.siuvs2.valueObject;

public class DelatnostId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public DelatnostId(String value) {
        this.value = Integer.parseInt(value);
    }

    public DelatnostId(Integer value) {
        this.value = value;
    }

}
