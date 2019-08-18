package org.bitbucket.pbosko.siuvs.repository;

import org.bitbucket.pbosko.siuvs.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Integer> {

    Page<Client> findAllByOrderByActiveDescIdAsc(Pageable pageable);

    List<Client> findByName(String name);

}
