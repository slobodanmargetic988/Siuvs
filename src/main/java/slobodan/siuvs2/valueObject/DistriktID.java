package slobodan.siuvs2.valueObject;

public class DistriktID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public DistriktID(String value) {
        this.value = Integer.parseInt(value);
    }

    public DistriktID(Integer value) {
        this.value = value;
    }

}
