/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.comba
 */

import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.OpenData;

public interface OpenDataRepository extends JpaRepository<OpenData, Integer> {

    OpenData findFirstByToken(String Token);
}
