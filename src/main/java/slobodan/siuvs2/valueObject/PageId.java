package slobodan.siuvs2.valueObject;

public class PageId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PageId(String value) {
        this.value = Integer.parseInt(value);
    }

    public PageId(Integer value) {
        this.value = value;
    }

}