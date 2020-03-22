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
import org.bitbucket.pbosko.siuvs.valueObject.MeraID;

public interface MeraService {
    Mera findOne(MeraID meraID);

    void save(Mera mera);
    void delete(MeraID meraID);
}
