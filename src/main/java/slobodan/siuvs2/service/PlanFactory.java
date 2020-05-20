/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.service;

/**
 *
 * @author Sloba
 */
import slobodan.siuvs2.model.Plan;
import slobodan.siuvs2.model.Client;

public interface PlanFactory {

    Plan empty(Client client);
}
