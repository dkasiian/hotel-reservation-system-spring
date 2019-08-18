package com.dkasiian.model.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "apartments")
public class Apartment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "number_of_rooms")
    @NotNull(message = "*Please provide number of rooms")
    private Integer numberOfRooms;

    @Column(name = "roominess")
    @NotNull(message = "*Please provide roominess")
    private Integer roominess;

    @Column(name = "cost_per_day")
    @NotNull(message = "*Please provide cost per day")
    private BigDecimal costPerDay;

    @Column(name = "apartment_class")
    @NotNull(message = "*Please provide apartment class")
    @Enumerated(value = EnumType.STRING)
    private ApartmentClassification apartmentClass;

    @Column(name = "is_occupied")
    @NotNull(message = "*Please indicate if the room is occupied")
    private Boolean isOccupied;
    
}
