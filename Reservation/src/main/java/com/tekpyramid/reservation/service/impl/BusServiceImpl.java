package com.tekpyramid.reservation.service.impl;

import com.tekpyramid.reservation.dao.BusDao;
import com.tekpyramid.reservation.data.models.dto.ApiResponse;
import com.tekpyramid.reservation.data.models.dto.BusDto;
import com.tekpyramid.reservation.data.models.entities.Bus;
import com.tekpyramid.reservation.service.BusService;
import com.tekpyramid.reservation.utils.ResponseUtil;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusDao busDao;
    public final ModelMapper modelMapper;

    @Override
    public ResponseEntity<ApiResponse> saveBus(BusDto busDto) {
        Bus bus=modelMapper.map(busDto,Bus.class);
        bus.setDateOfDeparture(LocalDate.now());
        return ResponseUtil.createResponse(busDao.saveBus(bus));
    }

    @Override
    public ResponseEntity<ApiResponse> updateBus(BusDto busDto, String id) {
        Bus bus=busDao.findBusById(id).orElseThrow(()-> new NoSuchElementException("Cannot Update Bus as Invalid Id"));
        modelMapper.map(busDto,bus);
        return ResponseUtil.updateResponse(busDao.saveBus(bus));
    }

    @Override
    public ResponseEntity<ApiResponse> findBusById(String id) {
        return busDao.findBusById(id).map(ResponseUtil::createOkResponse).orElseThrow(()-> new NoSuchElementException("Invalid Id"));
    }


}
