package slobodan.siuvs2.valueObject;

public class PodvrstaMTSId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PodvrstaMTSId(String value) {
        this.value = Integer.parseInt(value);
    }

    public PodvrstaMTSId(Integer value) {
        this.value = value;
    }

}
