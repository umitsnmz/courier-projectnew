package com.dev.courierprojectnew.repository;

import com.dev.courierprojectnew.entity.CourierTracking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourierTrackingRepository extends JpaRepository<CourierTracking,Long> {

    List<CourierTracking> findAllByCourierIdOrderByTimeAsc(Long courierId);

}
