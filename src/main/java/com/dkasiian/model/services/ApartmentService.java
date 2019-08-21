package com.dkasiian.model.services;

import com.dkasiian.model.entities.Apartment;
import com.dkasiian.model.repositories.ApartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ApartmentService {

    private final ApartmentRepository apartmentRepository;

    @Autowired
    public ApartmentService(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    public Optional<Apartment> getApartmentById(Long apartmentId) {
        return apartmentRepository.findById(apartmentId);
    }

    public Apartment saveApartment(Apartment apartment) {
        return apartmentRepository.save(apartment);
    }

    public void deleteApartmentById(Long apartmentId) {
        apartmentRepository.deleteById(apartmentId);
    }
}
