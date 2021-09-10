package slobodan.siuvs2.valueObject;

public class ZanimanjaId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public ZanimanjaId(String value) {
        this.value = Integer.parseInt(value);
    }

    public ZanimanjaId(Integer value) {
        this.value = value;
    }

}
