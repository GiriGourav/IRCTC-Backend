package com.substring.irctc.service.impl;

import com.substring.irctc.dto.AvailableTrainResponse;
import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.dto.UserTrainSearchRequest;
import com.substring.irctc.entity.*;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.StationRepo;
import com.substring.irctc.repositories.TrainRepository;
import com.substring.irctc.repositories.TrainScheduleRepository;
import com.substring.irctc.service.TrainService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TrainServiceImpl implements TrainService {

    private StationRepo stationRepository;
    private ModelMapper modelMapper;
    private TrainRepository trainRepository;
    private TrainScheduleRepository trainScheduleRepository;


    public TrainServiceImpl(StationRepo stationRepository, ModelMapper modelMapper, TrainRepository trainRepository, TrainScheduleRepository trainScheduleRepository) {
        this.stationRepository = stationRepository;
        this.modelMapper = modelMapper;
        this.trainRepository = trainRepository;
        this.trainScheduleRepository = trainScheduleRepository;
    }

    @Override
    public TrainDTO createTrain(TrainDTO trainDTO) {

        Long sid=trainDTO.getSourceStation().getId();
        Long did=trainDTO.getDestinationStation().getId();
        Station sourceStation=stationRepository.findById(sid).orElseThrow(()-> new ResourceNotFoundException("Source station not found with sid : "+sid));
        Station destinationStation=stationRepository.findById(did).orElseThrow(()-> new ResourceNotFoundException("Destination station not found with did : "+did));
        Train train=modelMapper.map(trainDTO,Train.class);
        train.setSourceStation(sourceStation);
        train.setDestinationStation(destinationStation);
        Train savedTrain=trainRepository.save(train);
        return modelMapper.map(savedTrain,TrainDTO.class);
    }

    @Override
    public List<TrainDTO> getAllTrains() {
        List<Train> all=trainRepository.findAll();

        return all.stream().map(train -> modelMapper.map(train,TrainDTO.class)).toList();
    }

    @Override
    public TrainDTO getTrainById(Long id) {
        Train train=trainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("train not found with id:"+id));

        return modelMapper.map(train,TrainDTO.class);
    }

    @Override
    public TrainDTO updateTrainById(Long id, TrainDTO trainDTO) {
        Train existingTrain=trainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("train not found with id:"+id));
        existingTrain.setName(trainDTO.getName());
        existingTrain.setNumber(trainDTO.getNumber());
        existingTrain.setTotalDistance(trainDTO.getTotalDistance());
        Station sourceStation=stationRepository.findById(trainDTO.getSourceStation().getId()).orElseThrow(()-> new ResourceNotFoundException("Source station not found"));
        Station destinationStation=stationRepository.findById(trainDTO.getDestinationStation().getId()).orElseThrow(()-> new ResourceNotFoundException("Destination station not found" ));

        existingTrain.setSourceStation(sourceStation);
        existingTrain.setDestinationStation(destinationStation);

        Train updatedTrain=trainRepository.save(existingTrain);
        return modelMapper.map(updatedTrain,TrainDTO.class);
    }

    @Override
    public void deleteTrain(Long id) {
        Train existingTrain=trainRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("train not found with id:"+id));
        trainRepository.delete(existingTrain);
    }

// This method is for user to search trains on specific date from source to destination with available seats
    @Override
    public List<AvailableTrainResponse> userTrainSearch(UserTrainSearchRequest request) {

      List<Train> matchedTrains=  this.trainRepository.findTrainBySourceAndDestination(request.getSourceStationId(),request.getDestinationStationId());

      List<Train> validTrain=new ArrayList<>();
      for(Train train:matchedTrains) {
          Integer sourceStationOrder=null;
          Integer destinationStationOrder=null;

          for (TrainRoute trainRoute:train.getRoutes()){
              if(trainRoute.getStation().getId().equals(request.getSourceStationId())){
                  sourceStationOrder=trainRoute.getStationOrder();
              }
              else if(trainRoute.getStation().getId().equals(request.getDestinationStationId()))
              {
                  destinationStationOrder=trainRoute.getStationOrder();
              }
          }
//Check the schedule date
          boolean runOnThatDay=train.getSchedules().stream().anyMatch(sch->sch.getRunDate().equals(request.getJourneyDate()));
          if (sourceStationOrder!=null && destinationStationOrder!=null && sourceStationOrder < destinationStationOrder && runOnThatDay)
          {
               validTrain.add(train);
          }
      }

      List<AvailableTrainResponse> response=new ArrayList<>();
      for(Train train:validTrain){
          TrainSchedule trainSchedule = train.getSchedules().stream().filter(sch->sch.getRunDate().equals(request.getJourneyDate())).findFirst().orElse(null);
          if(trainSchedule==null){
              continue;
          }

          TrainRoute sourceRoute=train.getRoutes().stream()
                  .filter(r->r.getStation().getId().equals(request.getSourceStationId()))
                  .findFirst()
                  .orElse(null);

//          TrainRoute destinationRoute=train.getRoutes().stream()
//                  .filter(r->r.getStation().getId().equals(request.getDestinationStationId()))
//                  .findFirst()
//                  .orElse(null);

          if(sourceRoute== null)
          {
              continue;
          }
          Map<CoachType, Integer> seatMap=new HashMap<>();
          Map<CoachType, Double> priceMap=new HashMap<>();

          for (TrainSeat trainSeat:trainSchedule.getTrainSeats())
          {
              seatMap.put(trainSeat.getCoachType(),trainSeat.getAvailableSeats());
              priceMap.putIfAbsent(trainSeat.getCoachType(),trainSeat.getPrice());
          }

          AvailableTrainResponse availableTrainResponse=AvailableTrainResponse.builder()
                  .trainId(train.getId())
                  .trainNumber(train.getNumber())
                  .trainName(train.getName())
                  .departureTime(sourceRoute.getDepartureTime())
                  .arrivalTime(sourceRoute.getArrivalTime())
                  .seatsAvailable(seatMap)
                  .priceByCoach(priceMap)
                  .build();

      }

        return null;
    }
}
