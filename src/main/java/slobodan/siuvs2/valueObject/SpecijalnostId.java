package slobodan.siuvs2.valueObject;

public class SpecijalnostId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public SpecijalnostId(String value) {
        this.value = Integer.parseInt(value);
    }

    public SpecijalnostId(Integer value) {
        this.value = value;
    }

}
