package slobodan.siuvs2.valueObject;

public class InternationalAgreementsID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public InternationalAgreementsID(String value) {
        this.value = Integer.parseInt(value);
    }

    public InternationalAgreementsID(Integer value) {
        this.value = value;
    }

}
