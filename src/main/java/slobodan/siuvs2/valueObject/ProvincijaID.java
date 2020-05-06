package slobodan.siuvs2.valueObject;

public class ProvincijaID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public ProvincijaID(String value) {
        this.value = Integer.parseInt(value);
    }

    public ProvincijaID(Integer value) {
        this.value = value;
    }

}
