package com.kevinjolley.lil.learningspring.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GuestRepository extends JpaRepository<Guest, Long> {
    // CrudRepository could also work, this is to show there are various implementations.
}
