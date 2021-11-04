package slobodan.siuvs2.valueObject;

public class StabVSId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public StabVSId(String value) {
        this.value = Integer.parseInt(value);
    }

    public StabVSId(Integer value) {
        this.value = value;
    }

}