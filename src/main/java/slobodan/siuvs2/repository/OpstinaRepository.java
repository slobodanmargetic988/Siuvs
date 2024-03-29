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
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import slobodan.siuvs2.model.Distrikt;
import slobodan.siuvs2.model.Opstina;
import slobodan.siuvs2.model.Provincija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OpstinaRepository extends JpaRepository<Opstina, Integer> {

    Opstina findFirstByDistrikt(Distrikt distrikt);
    Opstina findFirstByName(String name);
Opstina findFirstByNamelatinica(String namelatinica);
    List<Opstina> findAllByDistriktOrderByIdAsc(Distrikt distrikt);

    List<Opstina> findAllByProvincijaOrderByIdAsc(Provincija provincija);

    List<Opstina> findAllByProvincijaOrderByNameAsc(Provincija provincija);

    List<Opstina> findAllByOrderByNameAsc();

    List<Opstina> findAllByDistriktOrderByNameAsc(Distrikt distrikt);

    Page<Opstina> findAllByOrderByNameAsc(Pageable pageable);

    List<Opstina> findByName(String name);
}
