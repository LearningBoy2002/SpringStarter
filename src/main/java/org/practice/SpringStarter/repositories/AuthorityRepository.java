package org.practice.SpringStarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.practice.SpringStarter.models.Authority;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {

}
