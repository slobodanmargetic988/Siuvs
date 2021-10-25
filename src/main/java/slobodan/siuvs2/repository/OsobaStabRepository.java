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
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import slobodan.siuvs2.model.Osoba;
import slobodan.siuvs2.model.OsobaStab;
import slobodan.siuvs2.model.StabVS;

public interface OsobaStabRepository extends JpaRepository<OsobaStab, Integer> {

    List<OsobaStab> findAllBy();

    List<OsobaStab> findAllByOsoba(Osoba osoba);

    List<OsobaStab> findAllByStab(StabVS stab);
    OsobaStab findFirstByOsobaAndStab(Osoba osoba,StabVS stab);
}
