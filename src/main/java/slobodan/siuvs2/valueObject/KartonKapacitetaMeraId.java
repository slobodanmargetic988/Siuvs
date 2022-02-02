package slobodan.siuvs2.valueObject;
/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
public class KartonKapacitetaMeraId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public KartonKapacitetaMeraId(String value) {
        this.value = Integer.parseInt(value);
    }

    public KartonKapacitetaMeraId(Integer value) {
        this.value = value;
    }

}
