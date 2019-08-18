package com.dkasiian.controllers;

import com.dkasiian.model.entities.Request;
import com.dkasiian.model.services.RequestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "Hotel Reservation System", tags = "request")
@RestController
@RequestMapping("/requests")
public class RequestController {

    private final RequestService requestService;

    @Autowired
    public RequestController(RequestService requestService) {
        this.requestService = requestService;
    }

    @ApiOperation("Get a list of requests")
    @PreAuthorize("hasAuthority(T(com.dkasiian.model.entities.Role).ADMIN)")
    @GetMapping
    public ResponseEntity<List<Request>> getRequests(){
        return new ResponseEntity<>(requestService.getAllRequests(), HttpStatus.OK);
    }

    @ApiOperation("Add request")
    @PostMapping
    public ResponseEntity<Request> saveRequest(@Valid @RequestBody Request request){
        return new ResponseEntity<>(requestService.saveRequest(request), HttpStatus.CREATED);
    }

    @ApiOperation("Update request")
    @PutMapping
    public ResponseEntity<Request> updateRequest(@Valid @RequestBody Request request){
        return new ResponseEntity<>(requestService.saveRequest(request), HttpStatus.OK);
    }

    @ApiOperation("Get a request by Id")
    @GetMapping("{requestId}")
    public ResponseEntity<Request> getRequestById(@PathVariable Long requestId){
        return requestService.getRequestById(requestId)
                .map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @ApiOperation("Delete a request by Id")
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
