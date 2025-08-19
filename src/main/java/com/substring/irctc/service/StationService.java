package com.substring.irctc.service;

import com.substring.irctc.dto.PagedResponse;
import com.substring.irctc.dto.StationDto;

public interface StationService {
    StationDto update(Long id, StationDto dto);

    StationDto createStation(StationDto stationDTO);

    PagedResponse<StationDto> listStations(int page, int size, String sortBy, String sortDir);

    StationDto getById(Long id);

    void delete(Long id);
}


