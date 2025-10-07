package com.substring.irctc.repositories;

import com.substring.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository <Train,Long > {

    @Query("SELECT tr.train from TrainRoute tr  WHERE tr.station.id= :sourceStationId OR tr.station.id= :destinationStationId")
//@Query("""
//        SELECT DISTINCT t
//        FROM Train t
//        JOIN t.routes r1
//        JOIN t.routes r2
//        WHERE r1.station.id = :sourceStationId
//          AND r2.station.id = :destinationStationId
//          AND r1.stationOrder < r2.stationOrder
//    """)
    List<Train> findTrainBySourceAndDestination(@Param("sourceStationId") Long sourceStationId, @Param("destinationStationId") Long destinationStationId);

}
