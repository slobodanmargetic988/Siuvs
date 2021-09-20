package slobodan.siuvs2.valueObject;

public class KartonClanovaStabaId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public KartonClanovaStabaId(String value) {
        this.value = Integer.parseInt(value);
    }

    public KartonClanovaStabaId(Integer value) {
        this.value = value;
    }

}
