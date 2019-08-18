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
@Table(name = "bills")
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "request_id")
    @NotNull(message = "*Please provide request id")
    private Long requestId;

    @Column(name = "apartment_id")
    @NotNull(message = "*Please provide apartment id")
    private Long apartmentId;

    @Column(name = "cost")
    private BigDecimal cost;

    @Column(name = "is_paid")
    private Boolean isPaid;
}
