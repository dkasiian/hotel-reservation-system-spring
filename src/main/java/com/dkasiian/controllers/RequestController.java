package com.dkasiian.controllers;

import com.dkasiian.model.entities.Request;
import com.dkasiian.model.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @GetMapping
    public ResponseEntity<List<Request>> getRequests(){
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Request> saveRequest(@Valid @RequestBody Request request){
        return new ResponseEntity<>(requestService.saveRequest(request), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Request> updateRequest(@Valid @RequestBody Request request){
        return new ResponseEntity<>(requestService.saveRequest(request), HttpStatus.OK);
    }

    @GetMapping("{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long requestId){
        return requestService.getRequestById(requestId)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("{requestId}")
    public ResponseEntity deleteRequest(@PathVariable Long requestId){
        return requestService.getRequestById(requestId)
                .map(value -> {
                    requestService.deleteRequestById(requestId);
                    return new ResponseEntity(HttpStatus.NO_CONTENT);
                })
                .orElseGet(() -> new ResponseEntity(HttpStatus.BAD_REQUEST));
    }
}
