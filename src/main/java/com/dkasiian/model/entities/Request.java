package com.dkasiian.model.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "user_id")
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(unique = true)
    private Long userId;

    @Column(name = "number_of_guests")
//    @Length(min = 1, message = "*Number of guests must have at least 1 guest")
    @NotNull(message = "*Please provide number of guests")
    private Integer numberOfGuests;

    @Column(name = "apartment_class")
    @NotEmpty(message = "*Please provide apartment class")
    private String apartmentClass;

    @Column(name = "arrival_date")
    @NotNull(message = "*Please provide arrival date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalDate;

    @Column(name = "departure_date")
    @NotNull(message = "*Please provide departure date")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureDate;
}
