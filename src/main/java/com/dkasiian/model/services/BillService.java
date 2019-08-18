package com.dkasiian.model.services;

import com.dkasiian.model.entities.Bill;

import java.util.List;
import java.util.Optional;

public interface BillService {
    Bill saveBill(Bill bill);
    List<Bill> getAllBills();
    Bill updateBill(Bill bill);
    Optional<Bill> getBillById(Long billId);
    void deleteBillById(Long billId);
}
