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
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Integer>{
    Plan findFirstByClientAndPage(Client client, Page page);
}
