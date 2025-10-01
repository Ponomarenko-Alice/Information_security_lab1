package com.ITMO.informationSecurity.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DataRepository {
    List<String> poem = new ArrayList<>(List.of("Мороз", "и", "солнце", "день", "чудесный"));

    public List<String> getPoem() {

        return poem;
    }
}
