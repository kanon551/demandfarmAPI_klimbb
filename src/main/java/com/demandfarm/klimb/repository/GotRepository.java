package com.demandfarm.klimb.repository;

import com.demandfarm.klimb.models.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface GotRepository extends JpaRepository<CharacterEntity, Long> {


    @Query(value = "SELECT DISTINCT ON (characters ->> 'charactername') *\n" +
            "FROM public.gottable\n" +
            "ORDER BY (characters ->> 'charactername'), id ASC", nativeQuery = true)
    List<CharacterEntity> getFullCharctersWithoutRepetitiveCharacterNames();



    @Query(value = "SELECT DISTINCT housename\n" +
            "FROM public.gottable\n" +
            "CROSS JOIN LATERAL (\n" +
            "    SELECT jsonb_array_elements_text(characters -> 'houseName') AS housename\n" +
            "    WHERE jsonb_typeof(characters -> 'houseName') = 'array'\n" +
            "    UNION ALL\n" +
            "    SELECT characters ->> 'houseName'\n" +
            "    WHERE jsonb_typeof(characters -> 'houseName') = 'string'\n" +
            ") AS subquery\n" +
            "WHERE characters -> 'houseName' IS NOT NULL", nativeQuery = true)
    List<String> getAllHouseNames();



    @Query(value = "SELECT DISTINCT ON (characters -> 'charactername') characters ->> 'charactername' AS charactername, characters, favorite, id\n" +
            "FROM public.gottable\n" +
            "WHERE \n" +
            "  characters ->> 'houseName' = :houseName\n" +
            "  OR jsonb_typeof(characters -> 'houseName') = 'array' AND characters -> 'houseName' @> jsonb_build_array(:houseName)", nativeQuery = true)
    List<CharacterEntity> retriveAllCharactersByHouseName(@Param("houseName") String houseName);


    @Query(value = "SELECT DISTINCT ON (characters -> 'charactername') characters ->> 'charactername' AS charactername, characters, favorite, id\n" +
            "FROM public.gottable\n" +
            "WHERE (characters ->> 'houseName' IN :houseNames\n" +
            "      OR jsonb_typeof(characters -> 'houseName') = 'array' AND characters -> 'houseName' @> jsonb_build_array(:houseNames))\n" +
            "  AND charactername ILIKE '%' || :charactername || '%'\n" +
            "ORDER BY characters -> 'charactername', id ASC", nativeQuery = true)
    List<CharacterEntity> retrieveAllCharactersByHouseNamesAndCharacterKeywordSearchName(@Param("houseNames") List<String> houseNames, @Param("charactername") String charactername);



    @Query(value = "SELECT DISTINCT ON (characters -> 'charactername') characters ->> 'charactername' AS charactername, characters, favorite, id\n" +
            "FROM public.gottable\n" +
            "WHERE charactername ILIKE '%' || :charactername || '%'\n" +
            "ORDER BY characters -> 'charactername', id ASC", nativeQuery = true)
    List<CharacterEntity> retriveCharacterByCharacterName(@Param("charactername") String charactername);




    @Modifying
    @Transactional
    @Query(value = "UPDATE public.gottable SET favorite = ?1 WHERE id = ?2", nativeQuery = true)
    void makeCharacterObjectASTrue(@Param("status") Boolean status, @Param("id") Integer id);


    @Query(value = "SELECT *\n" +
            "FROM public.gottable\n" +
            "WHERE id = :id", nativeQuery = true)
    List<CharacterEntity> retriveCharacterByID(Integer id);
}
