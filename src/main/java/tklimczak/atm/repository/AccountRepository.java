package tklimczak.atm.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import tklimczak.atm.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {

	@Query("SELECT a FROM Account a WHERE a.number like ?1")
	Optional<Account> findByNumber(String number);

	Boolean existsByNumber(String number);
}
