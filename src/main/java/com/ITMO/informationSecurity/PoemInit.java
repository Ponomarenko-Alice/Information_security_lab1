package com.ITMO.informationSecurity;


import com.ITMO.informationSecurity.model.Poem;
import com.ITMO.informationSecurity.repository.DataRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PoemInit {
    @Bean
    CommandLineRunner seedPoem(DataRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                Poem p = new Poem();
                p.setWords(List.of("Мороз", "и", "солнце", "день", "чудесный"));
                repo.save(p);
            }
        };
    }
}