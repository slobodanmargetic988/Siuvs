package slobodan.siuvs2.valueObject;

public class ClientId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public ClientId(String value) {
        this.value = Integer.parseInt(value);
    }

    public ClientId(Integer value) {
        this.value = value;
    }
    
}
