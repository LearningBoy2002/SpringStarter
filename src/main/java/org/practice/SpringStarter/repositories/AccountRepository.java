package org.practice.SpringStarter.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.practice.SpringStarter.models.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findOneByEmailIgnoreCase(String email);

    Optional<Account> findByToken(String toekn);

}
