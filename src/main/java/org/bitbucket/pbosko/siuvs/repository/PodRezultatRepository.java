/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.repository;

/**
 *
 * @author deca
 */
import org.bitbucket.pbosko.siuvs.model.Rezultat;
import org.bitbucket.pbosko.siuvs.model.PodRezultat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PodRezultatRepository extends JpaRepository<PodRezultat, Integer>{
    PodRezultat findFirstByRezultat(Rezultat rezultat);

@Modifying
    @Query(value = "INSERT INTO pod_rezultat (`rezultat_id`, `redosled`, `period_kompletiran`) VALUES (:rezultat_id, :redosled, :period_kompletiran)", nativeQuery = true)
    void addData(@Param("rezultat_id") Integer rezultat_id, @Param("redosled") Integer redosled, @Param("period_kompletiran") Integer period_kompletiran);
}
