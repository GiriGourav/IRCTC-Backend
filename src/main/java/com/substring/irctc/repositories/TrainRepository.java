package com.substring.irctc.repositories;

import com.substring.irctc.entity.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TrainRepository extends JpaRepository <Train,Long > {
    @Query("""
            SELECT tr.train from TrainRoute tr
            WHERE tr.station.id = sourceStationId OR tr.station.id = destinationStationId
            ORDER BY tr.stationOrder
            """)
    List<Train> findTrainBySourceAndDestinationInOrder(@Param("sourceStationId") Long sourceStationId,@Param("destinationStationId") Long destinationStationId);
}
