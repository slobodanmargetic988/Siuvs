/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package slobodan.siuvs2.repository;

/**
 *
 * @author Slobodan Margetic slobodanmargetic988@gmail.com
 */
import slobodan.siuvs2.model.Rezultat;
import slobodan.siuvs2.model.Mera;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RezultatRepository extends JpaRepository<Rezultat, Integer> {

    Rezultat findFirstByMera(Mera mera);

}
