package com.dkasiian.controllers;

import com.dkasiian.model.entities.Bill;
import com.dkasiian.model.services.BillService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Hotel Reservation System", tags = "bill")
@RestController
@PreAuthorize("hasAuthority(T(com.dkasiian.model.entities.Role).ADMIN)")
@RequestMapping("/bills")
public class BillController {

    private final BillService billService;

    public BillController(BillService billService) {
        this.billService = billService;
    }

    @ApiOperation("Get a list of bills")
    @GetMapping
    public ResponseEntity<List<Bill>> getBills(){
        return new ResponseEntity<>(billService.getAllBills(), HttpStatus.OK);
    }

    @ApiOperation("Add bill")
    @PostMapping
    public ResponseEntity<Bill> saveBill(@Valid @RequestBody Bill bill){
        return new ResponseEntity<>(billService.saveBill(bill), HttpStatus.CREATED);
    }

    @ApiOperation("Update bill")
    @PutMapping
    public ResponseEntity<Bill> updateBill(@Valid @RequestBody Bill bill){
        return new ResponseEntity<>(billService.updateBill(bill), HttpStatus.OK);
    }

    @ApiOperation("Get bill by Id")
    @GetMapping("{billId}")
    public ResponseEntity<Bill> getBillById(@PathVariable Long billId){
        return billService.getBillById(billId)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation("Delete bill by Id")
    @DeleteMapping("{billId}")
    public ResponseEntity deleteBill(@PathVariable Long billId){
        return billService.getBillById(billId)
                .map(value -> {
                    billService.deleteBillById(billId);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}
