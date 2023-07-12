package com.demandfarm.klimb.service;


import com.demandfarm.klimb.dto.CharacterDTO;
import com.demandfarm.klimb.dto.CharactersDTO;
import com.demandfarm.klimb.models.CharacterEnt;
import com.demandfarm.klimb.models.House;
import com.demandfarm.klimb.repository.CharacterRepository;
import com.demandfarm.klimb.repository.HouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class CharService {

    private final CharacterRepository characterRepository;
    private final HouseRepository houseRepository;

    @Autowired
    public CharService(CharacterRepository characterRepository, HouseRepository houseRepository) {
        this.characterRepository = characterRepository;
        this.houseRepository = houseRepository;
    }

    public List<CharacterDTO> getCharacters(String houseName) {

        List<CharacterEnt> characterEnts = characterRepository.findByHouseNames_Housename(houseName);

        List<CharacterDTO> characterDTOs = new ArrayList<>();

        for (CharacterEnt characterEnt : characterEnts) {
            CharacterDTO characterDTO = new CharacterDTO();
            characterDTO.setCharacterName(characterEnt.getCharectername());
            characterDTO.setHouseNames(characterEnt.getHouseNames().stream().map(House::getHousename).toList());
            characterDTO.setParents(characterEnt.getParents().stream().map(CharacterEnt::getCharectername).toList());
            characterDTO.setSiblings(characterEnt.getSiblings().stream().map(CharacterEnt::getCharectername).toList());
            characterDTO.setParentOf(characterEnt.getParentOf().stream().map(CharacterEnt::getCharectername).toList());
            characterDTO.setMarriedEngaged(characterEnt.getMarriedEngaged().stream().map(CharacterEnt::getCharectername).toList());
            characterDTOs.add(characterDTO);
        }

        return characterDTOs;
    }

    public void saveCharacter(CharactersDTO charactersDTO) {

        List<CharacterEnt> charactersEnt = new ArrayList();

        for (CharacterDTO characterDTO : charactersDTO.getCharacters()) {

            CharacterEnt characterEnt = new CharacterEnt();
            List<House> houses = new ArrayList<>();

            if (characterDTO.getHouseNames() != null && !characterDTO.getHouseNames().isEmpty()) {

                characterDTO.getHouseNames().forEach(houseName -> {
                    House house = new House();

                    house.setHousename(houseName);
                    House houseExist = houseRepository.findByHousename(houseName);

                    if (houseExist == null) {
                        houseRepository.saveAndFlush(house);
                    } else {
                        house = houseExist;
                    }
                    houses.add(house);

                });
            }

            List<CharacterEnt> marriedTO = new ArrayList<>();
            if (characterDTO.getMarriedEngaged() != null && !characterDTO.getMarriedEngaged().isEmpty()) {
                characterDTO.getMarriedEngaged().forEach(married -> {
                    CharacterEnt marriedChar = new CharacterEnt();
                    marriedTO.add(marriedChar);
                    marriedChar.setCharectername(married);
                    saveCharacterToRep(marriedChar);
                });
            }
            List<CharacterEnt> parents = new ArrayList<>();
            if (characterDTO.getParents() != null && !characterDTO.getParents().isEmpty()) {
                characterDTO.getParents().forEach(parent -> {
                    CharacterEnt parentChar = new CharacterEnt();
                    parents.add(parentChar);
                    parentChar.setCharectername(parent);
                    saveCharacterToRep(parentChar);

                });
            }
            List<CharacterEnt> parentsOf = new ArrayList<>();
            if (characterDTO.getParentOf() != null && !characterDTO.getParentOf().isEmpty()) {
                characterDTO.getParentOf().forEach(parentof -> {
                    CharacterEnt parentofChar = new CharacterEnt();
                    parentsOf.add(parentofChar);
                    parentofChar.setCharectername(parentof);
                    saveCharacterToRep(parentofChar);

                });
            }


            List<CharacterEnt> siblings = new ArrayList<>();
            if (characterDTO.getSiblings() != null && !characterDTO.getSiblings().isEmpty()) {
                characterDTO.getSiblings().forEach(sibling -> {
                    CharacterEnt siblingChar = new CharacterEnt();
                    siblings.add(siblingChar);
                    siblingChar.setCharectername(sibling);
                    saveCharacterToRep(siblingChar);
                });
            }

            //    houseRepository.saveAll(houses);

            characterEnt.setCharectername(characterDTO.getCharacterName());
            characterEnt.setHouseNames(houses);
            characterEnt.setMarriedEngaged(marriedTO);
            characterEnt.setParents(parents);
            characterEnt.setSiblings(siblings);
            characterEnt.setParentOf(parentsOf);

            charactersEnt.add(characterEnt);

            CharacterEnt characterEntExist = characterRepository.findByCharectername(characterEnt.getCharectername());

            if (characterEntExist != null) {
                characterEnt.setCharnameid(characterEntExist.getCharnameid());
            }

            characterRepository.saveAndFlush(characterEnt);
        }

        // characterRepository.saveAll(charactersEnt);
    }

    private void saveCharacterToRep(CharacterEnt characterEnt) {

        CharacterEnt characterEntExist = characterRepository.findByCharectername(characterEnt.getCharectername());
        if (characterEntExist == null) {
            characterRepository.saveAndFlush(characterEnt);
        } else {
            characterEnt.setCharnameid(characterEntExist.getCharnameid());
        }
    }
}
