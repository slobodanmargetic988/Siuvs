/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;


public interface MeraFactory {
       Mera empty(PosebanCilj posebanCilj);
}
