package com.ITMO.informationSecurity.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "poem")
@Getter
@Setter
public class Poem {
    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ElementCollection
    @CollectionTable(name = "poem_words", joinColumns = @JoinColumn(name = "poem_id"))
    @OrderColumn(name = "pos")
    @Column(name = "word")
    private List<String> words = new ArrayList<>();
}
