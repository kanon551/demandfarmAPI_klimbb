package com.demandfarm.klimb.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CharactersDTO {

    @JsonProperty("characters")
    public List<CharacterDTO> characters;

}
