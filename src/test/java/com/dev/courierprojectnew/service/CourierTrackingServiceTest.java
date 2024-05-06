package com.dev.courierprojectnew.service;

import com.dev.courierprojectnew.entity.CourierTracking;
import com.dev.courierprojectnew.entity.Store;
import com.dev.courierprojectnew.entity.StoreLog;
import com.dev.courierprojectnew.repository.CourierTrackingRepository;
import com.dev.courierprojectnew.repository.StoreLogRepository;
import com.dev.courierprojectnew.repository.StoreRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class CourierTrackingServiceTest {
    @Mock
    private StoreLogRepository storeLogRepository;
    @Mock
    private StoreRepository storeRepository;
    @Mock
    private CourierTrackingRepository courierTrackingRepository;
    @InjectMocks
    private CourierTrackingService courierTrackingService;

    LocalDateTime dateTime = LocalDateTime.now();

    @Test
    public void testGetStoreLog() {
        List<StoreLog> mockStoreLogs = new ArrayList<>();
        mockStoreLogs.add(new StoreLog(1L, 1L, 12345L, dateTime));
        mockStoreLogs.add(new StoreLog(2L, 2L, 12346L, dateTime));
        when(storeLogRepository.findAll()).thenReturn(mockStoreLogs);
        List<StoreLog> result = courierTrackingService.getStoreLog();
        assertEquals(mockStoreLogs.size(), result.size());
    }

    @Test
    public void testGetAllStore() {
        List<Store> mockStore = new ArrayList<>();
        mockStore.add(new Store(1L, "Test Mağaza", 41.0066851, 28.6552262));
        mockStore.add(new Store(2L, "Test Mağaza 2", 41.055783, 29.0210292));
        when(storeRepository.findAll()).thenReturn(mockStore);
        List<Store> result = courierTrackingService.getAllStore();
        assertEquals(mockStore.size(), result.size());
    }

    @Test
    public void testGetTotalTravelDistance_NoData() {
        // Kurye izleme verisi bulunmuyorsa
        Long courierId = 1L;
        when(courierTrackingRepository.findAllByCourierIdOrderByTimeAsc(courierId)).thenReturn(new ArrayList<>());
        String expected = "Kurye id : 1 olan kuryeye ait konum verisi bulunmamaktadır!";
        String result = courierTrackingService.getTotalTravelDistance(courierId);
        assertEquals(expected, result);
    }

    @Test
    public void testGetTotalTravelDistance_NoMovement() {
        // Kurye hiç yol almamışsa
        Long courierId = 2L;
        List<CourierTracking> courierTrackingList = new ArrayList<>();
        courierTrackingList.add(new CourierTracking(1L, 12345L, 40.111111, 29.111111, dateTime));
        when(courierTrackingRepository.findAllByCourierIdOrderByTimeAsc(courierId)).thenReturn(courierTrackingList);
        String expected = "Kurye id : 2 olan kurye hiç yol almamıştır!";
        String result = courierTrackingService.getTotalTravelDistance(courierId);
        assertEquals(expected, result);
    }

//    @Test
//    public void testGetTotalTravelDistance_WithData() {
//        // Kurye hareket etmişse
//        Long courierId = 12345L;
//        List<CourierTracking> courierTrackingList = new ArrayList<>();
//        when(courierTrackingRepository.findAllByCourierIdOrderByTimeAsc(courierId)).thenReturn(courierTrackingList);
//        String expected = "Kurye id                 : " + courierId +
//                "\nKurye İlk Hareket Zamanı : " + "2024-05-05 14:46:40.592838" +
//                "\nKurye Son Hareket Zamanı : " + "2024-05-05 14:47:57.480984" +
//                "\nTotal Mesafe             : " + "1.88" + " km";
//        String result = courierTrackingService.getTotalTravelDistance(courierId);
//        assertEquals(expected, result);
//    }
}
