package com.demandfarm.klimb.models;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Entity
@Component
@Table(name = "gottable", schema = "public")
public class CharacterEntity {

    @Column(columnDefinition = "jsonb")
    private String characters;

    @Column(name = "favorite")
    private boolean favorite;

    @Column(name = "charactername")
    private String charactername;

    @Id
    @Column(name = "id")
    private Long id;

}
