package com.demandfarm.klimb.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "housenamestable")
@AllArgsConstructor
@NoArgsConstructor
//@JsonIdentityInfo(        generator = ObjectIdGenerators.PropertyGenerator.class,        property = "housenameid")
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long housenameid;

    @Column(name = "housename", unique = true)
    private String housename;

  //  @JsonBackReference
  //  @JsonIgnore
  //  @ManyToMany(mappedBy = "houseNames",  fetch=FetchType.LAZY)
  //  private List<CharacterEnt> characters;


    // Constructors, getters, setters
}
