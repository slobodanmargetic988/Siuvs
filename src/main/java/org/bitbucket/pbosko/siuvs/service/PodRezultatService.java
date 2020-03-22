/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.PodRezultat;
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.valueObject.PodRezultatID;

public interface PodRezultatService {
    PodRezultat findOne(PodRezultatID podRezultatID);

    void save(PodRezultat podRezultat);
     void delete(PodRezultatID podRezultatID);
}
