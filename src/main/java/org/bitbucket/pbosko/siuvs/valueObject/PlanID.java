/*
 * 
 */
package org.bitbucket.pbosko.siuvs.valueObject;

/**
 *
 * @author deca
 */
public class PlanID implements IdValueObjectInterface {

    private Integer value;

    @Override
    public Integer getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return this.getValue().toString();
    }

    public PlanID(String value) {
        this.value = Integer.parseInt(value);
    }

    public PlanID(Integer value) {
        this.value = value;
    }
}
