package com.dkasiian.model.services;

import com.dkasiian.model.entities.Request;

import java.util.List;
import java.util.Optional;

public interface RequestService {
    Request saveRequest(Request request);
    List<Request> getAllRequests();
    Optional<Request> getRequestById(Long id);
    void deleteRequestById(Long requestId);
}
