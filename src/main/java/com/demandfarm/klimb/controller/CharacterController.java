package com.demandfarm.klimb.controller;

import com.demandfarm.klimb.models.CharacterEntity;
import com.demandfarm.klimb.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/characters")
public class CharacterController {
    private final CharacterService characterService;

    @Autowired
    public CharacterController(CharacterService characterService) {
        this.characterService = characterService;
    }

    @GetMapping("/housenames")
    public List<String> getAllHouseNames() {
        return characterService.getAllHouseNames();
    }

    @GetMapping("/fullObject")
    public List<CharacterEntity> getFullObject() {
        return characterService.receiveFullObject();
    }

    @GetMapping("/fetchAllCharactersByHouseName/{houseName}")
    public List<CharacterEntity> obtainAllCharactersByHouseName(@PathVariable String houseName) {
        return characterService.getAllCharactersByHouseName(houseName);
    }

    @GetMapping("/fetchCharactersByCharacterName/{characterName}")
    public List<CharacterEntity> obtainCharacterByCharacterName(@PathVariable String characterName) {
        return characterService.getCharacterByCharacterName(characterName);
    }

    @GetMapping("/fetchByHouseNamesAndCharacterNames/{houseNames}/{characterName}")
    public List<CharacterEntity> obtainCombinationOfHouseNamesAndKeywordSearch(@PathVariable List<String> houseNames, @PathVariable String characterName) {
        if(characterName.equals("empty")){
            characterName = "";
        }
        return characterService.getCharactersByHouseNamesAndKeywordSearch(houseNames, characterName);
    }

    @PutMapping("/setFavorite/{id}/{status}")
    public ResponseEntity<String> editCharacterFavoriteAsTrue(@PathVariable Integer id, @PathVariable Boolean status){
        try {
            characterService.updateCharacterFavoriteAsTrue(status, id);
            return ResponseEntity.ok("Character favorite updated successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update character favorite.");
        }
    }

    @GetMapping("/getCharacterById/{id}")
    public List<CharacterEntity> obtainCharacterByID(@PathVariable Integer id){
       return characterService.getCharByID(id);
    }



}
