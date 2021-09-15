package slobodan.siuvs2.valueObject;

public class KartonUdruzenjaId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public KartonUdruzenjaId(String value) {
        this.value = Integer.parseInt(value);
    }

    public KartonUdruzenjaId(Integer value) {
        this.value = value;
    }

}
