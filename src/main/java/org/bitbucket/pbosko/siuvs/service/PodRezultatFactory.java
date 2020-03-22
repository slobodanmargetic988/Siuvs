/*
 * 
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author deca
 */


import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.model.PodRezultat;

public interface PodRezultatFactory {
       PodRezultat empty(Rezultat rezultat);
       
}
