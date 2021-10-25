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
import slobodan.siuvs2.model.OsobaSOT;
import slobodan.siuvs2.model.StrucnoOT;

public interface OsobaSOTRepository extends JpaRepository<OsobaSOT, Integer> {

    List<OsobaSOT> findAllBy();

    List<OsobaSOT> findAllByOsoba(Osoba osoba);

    List<OsobaSOT> findAllBySot(StrucnoOT sot);
     OsobaSOT findFirstByOsobaAndSot(Osoba osoba,StrucnoOT sot);
}
