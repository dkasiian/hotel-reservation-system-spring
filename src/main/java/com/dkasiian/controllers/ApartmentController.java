package com.dkasiian.controllers;

import com.dkasiian.model.entities.Apartment;
import com.dkasiian.model.services.ApartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Hotel Reservation System", tags = "apartment")
@RestController
@RequestMapping("/apartments")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @ApiOperation("Get a list of apartments")
    @GetMapping
    public ResponseEntity<List<Apartment>> getApartments(){
        return new ResponseEntity<>(apartmentService.getAllApartments(), HttpStatus.OK);
    }

    @ApiOperation("Add apartment")
    @PostMapping
    public ResponseEntity<Apartment> saveApartment(@Valid @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.saveApartment(apartment), HttpStatus.CREATED);
    }

    @ApiOperation("Update apartment")
    @PutMapping
    public ResponseEntity<Apartment> updateApartment(@Valid @RequestBody Apartment apartment){
        return new ResponseEntity<>(apartmentService.saveApartment(apartment), HttpStatus.OK);
    }

    @ApiOperation("Get apartment by Id")
    @GetMapping("{apartmentId}")
    public ResponseEntity<Apartment> getApartmentById(@PathVariable Long apartmentId){
        return apartmentService.getApartmentById(apartmentId)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation("Delete apartment by Id")
    @DeleteMapping("{apartmentId}")
    public ResponseEntity deleteApartment(@PathVariable Long apartmentId){
        return apartmentService.getApartmentById(apartmentId)
                .map(value -> {
                    apartmentService.deleteApartmentById(apartmentId);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}
