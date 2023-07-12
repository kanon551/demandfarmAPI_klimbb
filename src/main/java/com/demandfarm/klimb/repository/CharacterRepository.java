package com.demandfarm.klimb.repository;


import com.demandfarm.klimb.models.CharacterEnt;
import com.demandfarm.klimb.models.House;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEnt, Long> {

     List<CharacterEnt> findByHouseNames_Housename(String housename);

     CharacterEnt findByCharectername(String charectername);
}
