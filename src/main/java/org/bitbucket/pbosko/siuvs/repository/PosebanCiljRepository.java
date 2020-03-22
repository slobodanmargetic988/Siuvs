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
import java.util.List;
import org.bitbucket.pbosko.siuvs.model.PosebanCilj;
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PosebanCiljRepository extends JpaRepository<PosebanCilj, Integer>{
    
    PosebanCilj findFirstByPlan(Plan plan);
    List<PosebanCilj> findAllByPlanOrderByRedosledAsc(Plan plan);
}
