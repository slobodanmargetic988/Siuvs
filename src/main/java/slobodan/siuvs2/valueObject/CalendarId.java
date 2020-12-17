package slobodan.siuvs2.valueObject;

public class CalendarId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public CalendarId(String value) {
        this.value = Integer.parseInt(value);
    }

    public CalendarId(Integer value) {
        this.value = value;
    }

}
