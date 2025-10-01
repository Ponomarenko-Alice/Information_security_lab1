package com.ITMO.informationSecurity.service;

import com.ITMO.informationSecurity.model.Poem;
import com.ITMO.informationSecurity.repository.DataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataService {
    private final DataRepository dataRepository;

    public List<String> getPoemWords(Long id) {
        Poem poem = dataRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Poem not found: " + id));
        return poem.getWords();
    }
}
