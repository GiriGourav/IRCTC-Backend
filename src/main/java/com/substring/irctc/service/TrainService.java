package com.substring.irctc.service;

import com.substring.irctc.dto.TrainDTO;
import com.substring.irctc.entity.Train;
import com.substring.irctc.exceptions.ResourceNotFoundException;
import com.substring.irctc.repositories.TrainRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrainService
{
    private TrainRepository trainRepository;

    private ModelMapper modelMapper;

    public TrainService(TrainRepository trainRepository, ModelMapper modelMapper) {
        this.trainRepository = trainRepository;
        this.modelMapper = modelMapper;
    }

    //    add train
    public TrainDTO add(TrainDTO trainDTO)
    {
//        Convert karna padega DTO TO ENTITY
//        Train train=new Train();
//        train.setTrainNo(trainDTO.getTrainNo());
//        train.setName(trainDTO.getName());
//        train.setRouteName(trainDTO.getRouteName());
//        Train savedTrain = trainRepository.save(train );

        Train train = modelMapper.map(trainDTO,Train.class);
        Train savedTrain = trainRepository.save(train);
//        Convert ENTITY into DTO
//        TrainDTO dto=new TrainDTO();
//        dto.setTrainNo(savedTrain.getTrainNo());
//        dto.setName(savedTrain.getName());
//        dto.setRouteName(savedTrain.getRouteName());

        TrainDTO dto =modelMapper.map(savedTrain,TrainDTO.class);
        return dto ;
    }
//    get all
    public Page<TrainDTO> all(int page, int size, String sortBy, String sortDir)
    {
//        Kuch aisa likhna hai pagination implement  ho jaye

//        Sorting
        Sort sort=sortBy.trim().toLowerCase().equals("asc")?Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
        Pageable pageable= PageRequest.of(page,size,sort);



//Database se data fetch karna ka logic
        Page<Train> trainPage= trainRepository.findAll(pageable);
//        List of train to list of TrainDTO

        return trainPage.map(train -> modelMapper.map(train,TrainDTO.class));
    }

//    get single
    public TrainDTO get(String trainNo)
    {

        Train train=  trainRepository
                .findById(trainNo).orElseThrow(()-> new ResourceNotFoundException());
        return modelMapper.map(train,TrainDTO.class);
    }

//    delete
    public  void delete(String trainNo)
    {
        Train train=  trainRepository.findById(trainNo).orElseThrow(()-> new ResourceNotFoundException());
        trainRepository.delete(train);

    }

}
