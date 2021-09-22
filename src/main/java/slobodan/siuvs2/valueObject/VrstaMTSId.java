package slobodan.siuvs2.valueObject;

public class VrstaMTSId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public VrstaMTSId(String value) {
        this.value = Integer.parseInt(value);
    }

    public VrstaMTSId(Integer value) {
        this.value = value;
    }

}
