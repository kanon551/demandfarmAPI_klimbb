package com.demandfarm.klimb.repository;

import com.demandfarm.klimb.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {
    House findByHousename(String houseName);
}
