package slobodan.siuvs2.valueObject;

public class DokumentID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public DokumentID(String value) {
        this.value = Integer.parseInt(value);
    }

    public DokumentID(Integer value) {
        this.value = value;
    }

}
