package com.baobab.ebanking.repository;

import com.baobab.ebanking.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the Territory entity.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
