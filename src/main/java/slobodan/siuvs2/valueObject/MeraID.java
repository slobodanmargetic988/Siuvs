/*
 * 
 */
package slobodan.siuvs2.valueObject;

/**
 *
 * @author deca
 */
public class MeraID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public MeraID(String value) {
        this.value = Integer.parseInt(value);
    }

    public MeraID(Integer value) {
        this.value = value;
    }
}
