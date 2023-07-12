package com.demandfarm.klimb.controller;


import com.demandfarm.klimb.dto.CharacterDTO;


import com.demandfarm.klimb.dto.CharactersDTO;
import com.demandfarm.klimb.models.CharacterEnt;
import com.demandfarm.klimb.service.CharService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
public class CharController {

    private CharService charService;



    @PostMapping("/characters")
    public ResponseEntity<String> createCharacter(@Validated @RequestBody CharactersDTO characters) {
        // Save the character and related entities (houses) if they don't exist in the database

        charService.saveCharacter(characters);


        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/characters/{houseName}")
    public List<CharacterDTO> getCharactersByHousename(@PathVariable(name="houseName") String houseName){

        return charService.getCharacters(houseName);
    }
}
