package com.dev.courierprojectnew.implement;

import com.dev.courierprojectnew.dto.request.CourierTrackingRequest;
import com.dev.courierprojectnew.dto.response.CourierTrackingResponse;
import com.dev.courierprojectnew.entity.Store;
import com.dev.courierprojectnew.entity.StoreLog;

import java.io.IOException;
import java.util.List;


public interface CourierTrackingImpl {

    CourierTrackingResponse saveCourierTracking(CourierTrackingRequest courierTrackingRequest) throws IOException;
    String getTotalTravelDistance(Long courierId);
    List<Store> getAllStore();
    List<StoreLog> getStoreLog();
}
