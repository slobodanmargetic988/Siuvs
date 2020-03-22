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

public interface RezultatFactory {
       Rezultat empty(Mera mera);
}
