package br.com.th.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.th.springboot.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    // @Query(value = "SELECT * FROM users WHERE username = ?1 limit 1", nativeQuery = true)
    // Client findByUsername(String username);

    // User findByEmail(String email);

}
