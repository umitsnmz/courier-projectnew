package com.dev.courierprojectnew.service;

import com.dev.courierprojectnew.dto.request.CourierTrackingRequest;
import com.dev.courierprojectnew.dto.response.CourierTrackingResponse;
import com.dev.courierprojectnew.entity.CourierTracking;
import com.dev.courierprojectnew.entity.Store;
import com.dev.courierprojectnew.entity.StoreLog;
import com.dev.courierprojectnew.implement.CourierTrackingImpl;
import com.dev.courierprojectnew.mapper.CourierTrackingMapper;
import com.dev.courierprojectnew.repository.CourierTrackingRepository;
import com.dev.courierprojectnew.repository.StoreLogRepository;
import com.dev.courierprojectnew.repository.StoreRepository;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CourierTrackingService implements CourierTrackingImpl {
    private static final double RADIUS_WORD= 6371;
    private final CourierTrackingRepository courierTrackingRepository;
    private final StoreRepository storeRepository;
    private final StoreLogRepository storeLogRepository;
    private final CourierTrackingMapper courierTrackingMapper;

    public CourierTrackingService(CourierTrackingRepository courierTrackingRepository, StoreRepository storeRepository, StoreLogRepository storeLogRepository, CourierTrackingMapper courierTrackingMapper) {
        this.courierTrackingRepository=courierTrackingRepository;
        this.storeRepository = storeRepository;
        this.storeLogRepository = storeLogRepository;
        this.courierTrackingMapper = courierTrackingMapper;
    }

    @Override
    public CourierTrackingResponse saveCourierTracking(CourierTrackingRequest courierTrackingRequest){
        StoreLog storeLog = new StoreLog();
        CourierTracking courierTracking = courierTrackingMapper.courierTrackingRequestToCourierTracking(courierTrackingRequest);
        List<Store> storeAll = storeRepository.findAll();
        for (int i = 0; i <storeAll.size()- 1; i++){
            double distance = calculateDistance(storeAll.get(i).getLat(), storeAll.get(i).getLng(), courierTrackingRequest.getLat(), courierTrackingRequest.getLng());
            if (distance * 100 <=100) {
                List<StoreLog> allByCourierIdAndStoreIdOrderByEntranceTimeDesc = storeLogRepository.findAllByCourierIdAndStoreIdOrderByEntranceTimeDesc(courierTracking.getCourierId(), storeAll.get(i).getId());
                if (!allByCourierIdAndStoreIdOrderByEntranceTimeDesc.isEmpty()) {
                    LocalDateTime time = allByCourierIdAndStoreIdOrderByEntranceTimeDesc.get(0).getEntranceTime();
                    LocalDateTime now = LocalDateTime.now();
                    Duration fark = Duration.between(now, time);
                    if (fark.getSeconds() > 60) {
                        storeLog.setCourierId(courierTracking.getCourierId());
                        storeLog.setStoreId(storeAll.get(i).getId());
                        storeLogRepository.save(storeLog);
                        courierTracking = courierTrackingRepository.save(courierTracking);
                        return courierTrackingMapper.courierTrackingToCourierTrackingResponse(courierTracking);
                    }
                } else {
                    storeLog.setCourierId(courierTracking.getCourierId());
                    storeLog.setStoreId(storeAll.get(i).getId());
                    storeLogRepository.save(storeLog);
                    courierTracking = courierTrackingRepository.save(courierTracking);
                    return courierTrackingMapper.courierTrackingToCourierTrackingResponse(courierTracking);
                }
            }
        }
        courierTracking = courierTrackingRepository.save(courierTracking);
        return courierTrackingMapper.courierTrackingToCourierTrackingResponse(courierTracking);
    }

    @Override
    public String getTotalTravelDistance(Long courierId) {
        List<CourierTracking> allByCourierId = courierTrackingRepository.findAllByCourierIdOrderByTimeAsc(courierId);
        double totalDistance = 0;
        DecimalFormat df = new DecimalFormat("#.##");
        if (allByCourierId == null || allByCourierId.isEmpty()) {
            return "Kurye id : " + courierId + " olan kuryeye ait konum verisi bulunmamaktadır!";
        } else if (!(allByCourierId.size() > 1)) {
            return "Kurye id : " + courierId + " olan kurye hiç yol almamıştır!";
        } else {
            for (int i = 0; i < allByCourierId.size() - 1; i++) {
                CourierTracking konum1 = allByCourierId.get(i);
                CourierTracking konum2 = allByCourierId.get(i + 1);
                double distance = calculateDistance(konum1.getLat(), konum1.getLng(), konum2.getLat(), konum2.getLng());
                totalDistance += distance;
            }
            //allByCourierId.stream().map(courierTracking -> courierTracking.getLat()+ " " +courierTracking.getLng()).collect(Collectors.toList());
            return "Kurye id                 : " + courierId +
                    "\nKurye İlk Hareket Zamanı : " + allByCourierId.get(0).getTime() +
                    "\nKurye Son Hareket Zamanı : " + allByCourierId.get(allByCourierId.size() - 1).getTime() +
                    "\nTotal Mesafe             : " + Double.parseDouble(df.format(totalDistance))+" km";
        }
    }

    @Override
    public List<Store> getAllStore() {
        return storeRepository.findAll();
    }

    @Override
    public List<StoreLog> getStoreLog() {
        return storeLogRepository.findAll();
    }

    public double calculateDistance(double lat1, double lng1, double lat2, double lng2) {
        double lat1Rad = Math.toRadians(lat1);
        double lng1Rad = Math.toRadians(lng1);
        double lat2Rad = Math.toRadians(lat2);
        double lng2Rad = Math.toRadians(lng2);
        double dLat = lat2Rad - lat1Rad;
        double dLon = lng2Rad - lng1Rad;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return RADIUS_WORD * c;
    }

}
