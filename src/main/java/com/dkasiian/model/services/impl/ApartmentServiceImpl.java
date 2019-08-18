package com.dkasiian.model.services.impl;

import com.dkasiian.model.entities.Apartment;
import com.dkasiian.model.repositories.ApartmentRepository;
import com.dkasiian.model.services.ApartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Optional<Apartment> getApartmentById(Long apartmentId) {
        return apartmentRepository.findById(apartmentId);
    }

    @Override
    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    @Override
    public void deleteApartmentById(Long apartmentId) {
        apartmentRepository.deleteById(apartmentId);
    }
}
