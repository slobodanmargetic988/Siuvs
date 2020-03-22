/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bitbucket.pbosko.siuvs.service;

/**
 *
 * @author Sloba
 */
import org.bitbucket.pbosko.siuvs.model.Plan;
import org.bitbucket.pbosko.siuvs.model.Client;
import org.bitbucket.pbosko.siuvs.model.Page;

public interface PlanFactory {
       Plan empty(Client client, Page page);
}
