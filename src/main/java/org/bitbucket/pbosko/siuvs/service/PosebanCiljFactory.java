/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.bitbucket.pbosko.siuvs.model.Plan;

public interface PosebanCiljFactory {
    PosebanCilj empty(Plan plan);
}



