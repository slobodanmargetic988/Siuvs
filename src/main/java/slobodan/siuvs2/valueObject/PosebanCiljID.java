/*
 * 
 */
package slobodan.siuvs2.valueObject;

/**
 *
 * @author deca
 */
public class PosebanCiljID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PosebanCiljID(String value) {
        this.value = Integer.parseInt(value);
    }

    public PosebanCiljID(Integer value) {
        this.value = value;
    }
}
