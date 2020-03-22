/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.valueObject.RezultatID;

public interface RezultatService {
    Rezultat findOne(RezultatID rezultatID);

    void save(Rezultat rezultat);
    void delete(RezultatID rezultatID);
}
