package com.oms.service;

import com.oms.dto.Response;
import org.springframework.http.HttpHeaders;

public interface OSMService {
    ServiceType getServiceType();

    <T> Response executeService(T t, HttpHeaders headers);
}
