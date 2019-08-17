package com.dkasiian.model.services.impl;

import com.dkasiian.model.entities.Request;
import com.dkasiian.model.repositories.RequestRepository;
import com.dkasiian.model.services.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestServiceImpl implements RequestService {

    private final RequestRepository requestRepository;

    @Autowired
    public RequestServiceImpl(RequestRepository requestRepository) {
        this.requestRepository = requestRepository;
    }

    @Override
    public Request saveRequest(Request request) {
        return requestRepository.save(request);
    }

    @Override
    public List<Request> getAllRequests() {
        return requestRepository.findAll();
    }

    @Override
    public Optional<Request> getRequestById(Long id) {
        return requestRepository.findById(id);
    }

    @Override
    public void deleteRequestById(Long requestId) {
        requestRepository.deleteById(requestId);
    }

}
