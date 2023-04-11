package org.enigma.service;

import org.enigma.model.Price;
import org.enigma.repository.IPriceRepository;

import java.util.List;
import java.util.Optional;

public class PriceService implements IPriceService{
    IPriceRepository priceRepository;

    public PriceService(IPriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }

    @Override
    public List<Price> getAll() {
        try {
            return priceRepository.getAll();
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Price create(Price create) {
        try {
            System.out.println("Price Created");
            return priceRepository.create(create);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void update(Price update, String id) {
        try {
            System.out.println("Price Updated");
            priceRepository.update(update, id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public void delete(String id) {
        try {
            System.out.println("Price Deleted");
            priceRepository.delete(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public Optional<Price> findId(String id) {
        try {
            return priceRepository.findId(id);
        }catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
