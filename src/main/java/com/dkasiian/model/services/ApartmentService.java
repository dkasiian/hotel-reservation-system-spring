package com.dkasiian.model.services;

import com.dkasiian.model.entities.Apartment;

import java.util.List;
import java.util.Optional;

public interface ApartmentService {
    List<Apartment> getAllApartments();
    Optional<Apartment> getApartmentById(Long apartmentId);
    Apartment saveApartment(Apartment apartment);
    void deleteApartmentById(Long apartmentId);
}
