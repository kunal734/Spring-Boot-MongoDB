package com.tekpyramid.reservation.controller;

import com.tekpyramid.reservation.data.models.dto.ApiResponse;
import com.tekpyramid.reservation.data.models.dto.BusDto;
import com.tekpyramid.reservation.service.BusService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/buses")
@Validated
public class BusController {

    private final BusService busService;

    @PostMapping
    public ResponseEntity<ApiResponse> saveBus(@Valid @RequestBody BusDto busDto){
        return busService.saveBus(busDto);
    }

    @PutMapping
    public ResponseEntity<ApiResponse> updateBus(@RequestBody BusDto busDto, @RequestParam String id){
        return busService.updateBus(busDto,id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> findBusById(@PathVariable String id){
        return busService.findBusById(id);
    }
}
