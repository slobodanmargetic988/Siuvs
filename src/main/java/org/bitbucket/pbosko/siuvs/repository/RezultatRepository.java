/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.repository;

/**
 *
 * @author sloba
 */
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.model.Mera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezultatRepository extends JpaRepository<Rezultat, Integer>{
    Rezultat findFirstByMera(Mera mera);
    
}
