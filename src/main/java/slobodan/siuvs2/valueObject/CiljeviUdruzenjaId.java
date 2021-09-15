package slobodan.siuvs2.valueObject;

public class CiljeviUdruzenjaId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public CiljeviUdruzenjaId(String value) {
        this.value = Integer.parseInt(value);
    }

    public CiljeviUdruzenjaId(Integer value) {
        this.value = value;
    }

}
