package com.tekpyramid.reservation.service;

import com.tekpyramid.reservation.data.models.dto.BusDto;
import com.tekpyramid.reservation.data.models.dto.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface BusService {

    public ResponseEntity<ApiResponse> saveBus(BusDto busDto);

    public ResponseEntity<ApiResponse> updateBus(BusDto busDto, String id);

    public ResponseEntity<ApiResponse> findBusById(String id);
}
