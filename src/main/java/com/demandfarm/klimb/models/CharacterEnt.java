package com.demandfarm.klimb.models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "characternamestable")
@JsonInclude(JsonInclude.Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
@JsonIdentityInfo(         generator = ObjectIdGenerators.PropertyGenerator.class,        property = "charnameid")
public class CharacterEnt {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long charnameid;

    @Column(name = "charectername")
    private String charectername;

    @ManyToMany(cascade=CascadeType.REFRESH)
    @JoinTable(name = "character_houses",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "house_id"))
    private List<House> houseNames;

    @ManyToMany
    @JoinTable(name = "character_parents",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_id"))
    private List<CharacterEnt> parents;


    @ManyToMany
    @JoinTable(name = "character_parent_of",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "child_id"))
    private List<CharacterEnt> parentOf;

    @ManyToMany
    @JoinTable(name = "character_siblings",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "sibling_id"))
    private List<CharacterEnt> siblings;

    @ManyToMany
    @JoinTable(name = "character_married_engaged",
            joinColumns = @JoinColumn(name = "character_id"),
            inverseJoinColumns = @JoinColumn(name = "partner_id"))
    private List<CharacterEnt> marriedEngaged;

    // Constructors, getters, setters
}

