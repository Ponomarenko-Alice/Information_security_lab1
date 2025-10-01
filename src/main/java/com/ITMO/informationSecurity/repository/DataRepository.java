package com.ITMO.informationSecurity.repository;

import com.ITMO.informationSecurity.model.Poem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DataRepository extends JpaRepository<Poem, Long> {
}
