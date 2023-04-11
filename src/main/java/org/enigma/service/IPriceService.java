package org.enigma.service;

import org.enigma.model.Price;


import java.util.List;
import java.util.Optional;

public interface IPriceService {
    List<Price> getAll();
    Price create(Price create);
    void update(Price update, String id);
    void delete(String id);
    Optional<Price> findId(String id);
}
