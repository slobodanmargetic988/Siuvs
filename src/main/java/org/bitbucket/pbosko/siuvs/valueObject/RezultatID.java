/*
 * 
 */
package org.bitbucket.pbosko.siuvs.valueObject;

/**
 *
 * @author deca
 */
public class RezultatID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public RezultatID(String value) {
        this.value = Integer.parseInt(value);
    }

    public RezultatID(Integer value) {
        this.value = value;
    }
}