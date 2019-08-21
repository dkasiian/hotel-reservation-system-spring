package com.dkasiian.model.services;

import com.dkasiian.model.entities.Apartment;
import com.dkasiian.model.entities.Bill;
import com.dkasiian.model.entities.Request;
import com.dkasiian.model.exceptions.NotFoundException;
import com.dkasiian.model.repositories.ApartmentRepository;
import com.dkasiian.model.repositories.BillRepository;
import com.dkasiian.model.repositories.RequestRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final RequestRepository requestRepository;
    private final ApartmentRepository apartmentRepository;

    public BillService(BillRepository billRepository,
                       RequestRepository requestRepository,
                       ApartmentRepository apartmentRepository) {
        this.billRepository = billRepository;
        this.requestRepository = requestRepository;
        this.apartmentRepository = apartmentRepository;
    }

    public Bill saveBill(Bill bill) {
        Optional<Request> request = requestRepository.findById(bill.getRequestId());
        Optional<Apartment> apartment = apartmentRepository.findById(bill.getApartmentId());
        BigDecimal cost;

        if (request.isPresent() && apartment.isPresent()){
            Request req = request.get();
            Apartment apart = apartment.get();
            cost = apart.getCostPerDay()
                    .multiply(BigDecimal.valueOf(DAYS.between(req.getArrivalDate(), req.getDepartureDate())));
        } else {
            throw new NotFoundException("RequestId or ApartmentId is invalid.");
        }

        bill.setCost(cost);
        return billRepository.save(bill);
    }

    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

    public Bill updateBill(Bill bill) {
        return billRepository.save(bill);
    }

    public Optional<Bill> getBillById(Long billId) {
        return billRepository.findById(billId);
    }

    public void deleteBillById(Long billId) {
        billRepository.deleteById(billId);
    }

}
