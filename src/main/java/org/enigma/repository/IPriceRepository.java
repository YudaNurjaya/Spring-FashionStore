package org.enigma.repository;

import org.enigma.model.Price;


import java.util.List;
import java.util.Optional;

public interface IPriceRepository {
    List<Price> getAll() throws Exception;
    Price create(Price create) throws Exception;
    void update(Price update, String id) throws Exception;
    void delete(String id) throws Exception;
    Optional<Price> findId(String id) throws Exception;
}
