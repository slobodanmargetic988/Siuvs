package org.bitbucket.pbosko.siuvs.valueObject;

public class PhotoId implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PhotoId(String value) {
        this.value = Integer.parseInt(value);
    }

    public PhotoId(Integer value) {
        this.value = value;
    }

}
