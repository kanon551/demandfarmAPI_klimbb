package com.demandfarm.klimb.service;

import com.demandfarm.klimb.models.CharacterEntity;
import com.demandfarm.klimb.repository.GotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CharacterService {

    private final GotRepository characterRepository;

    @Autowired
    public CharacterService(GotRepository characterRepository) {
        this.characterRepository = characterRepository;
    }

    public List<String> getAllHouseNames() {
        return characterRepository.getAllHouseNames();
    }

    public List<CharacterEntity> receiveFullObject() {
        return characterRepository.getFullCharctersWithoutRepetitiveCharacterNames();
    }

    public List<CharacterEntity> getAllCharactersByHouseName(String houseName) {
        return characterRepository.retriveAllCharactersByHouseName(houseName);
    }


    public List<CharacterEntity> getCharacterByCharacterName(String characterName) {
        return characterRepository.retriveCharacterByCharacterName(characterName);
    }

    public List<CharacterEntity> getCharactersByHouseNamesAndKeywordSearch(List<String> houseNames, String characterName) {
        return characterRepository.retrieveAllCharactersByHouseNamesAndCharacterKeywordSearchName(houseNames, characterName);
    }


    public void updateCharacterFavoriteAsTrue(Boolean status, Integer id) {
            characterRepository.makeCharacterObjectASTrue(status, id);
    }

    public List<CharacterEntity> getCharByID(Integer id) {
        return characterRepository.retriveCharacterByID(id);
    }
}

