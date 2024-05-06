package com.dev.courierprojectnew.entity;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "PUBLIC" ,name = "STORE_LOG")
public class StoreLog {
    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "STORE_ID")
    private Long storeId;

    @Column(name = "COURIER_ID")
    private Long courierId;

    @Column(name = "ENTRANCE_TIME")
    private LocalDateTime entranceTime;

    public StoreLog() {

    }

    @PrePersist
    public void prePersist() {
        entranceTime = LocalDateTime.now();
    }

    public StoreLog(Long id, Long storeId, Long courierId, LocalDateTime entranceTime) {
        this.id = id;
        this.storeId = storeId;
        this.courierId = courierId;
        this.entranceTime = entranceTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public LocalDateTime getEntranceTime() {
        return entranceTime;
    }

    public void setEntranceTime(LocalDateTime entranceTime) {
        this.entranceTime = entranceTime;
    }
}
