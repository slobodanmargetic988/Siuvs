/*
 * 
 */
package slobodan.siuvs2.valueObject;

/**
 *
 * @author deca
 */
public class PodRezultatID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PodRezultatID(String value) {
        this.value = Integer.parseInt(value);
    }

    public PodRezultatID(Integer value) {
        this.value = value;
    }
}