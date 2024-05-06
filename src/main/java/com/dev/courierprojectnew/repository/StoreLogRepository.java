package com.dev.courierprojectnew.repository;

import com.dev.courierprojectnew.entity.CourierTracking;
import com.dev.courierprojectnew.entity.StoreLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreLogRepository extends JpaRepository<StoreLog,Long> {

    List<StoreLog> findAllByCourierIdAndStoreIdOrderByEntranceTimeDesc(Long courierId, Long storeId);
}
