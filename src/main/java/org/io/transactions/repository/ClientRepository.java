package org.io.transactions.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.io.transactions.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
