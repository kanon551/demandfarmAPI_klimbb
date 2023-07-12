package com.demandfarm.klimb.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class CharacterDTO {
    @NonNull
    @JsonProperty(value = "characterName")
    private String characterName;

    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @JsonProperty("houseName")
    private List<String> houseNames;
    @JsonProperty("parents")
    private List<String> parents;
    @JsonProperty("parentOf")
    private List<String> parentOf;
    @JsonProperty("siblings")
    private List<String> siblings;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("marriedEngaged")
    private List<String> marriedEngaged;


}
