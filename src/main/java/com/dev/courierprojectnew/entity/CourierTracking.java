package com.dev.courierprojectnew.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(schema = "PUBLIC" ,name = "COURIER_TRACKING")
public class CourierTracking {
    @Column(name = "ID")
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "COURIERID")
    private Long courierId;

    @Column(name = "LAT")
    private Double lat;

    @Column(name = "LNG")
    private Double lng;

    @Column(name = "TIME")
    private LocalDateTime time;

    public CourierTracking(long id, long courierId, double lat, double lng, LocalDateTime time) {
        this.id = id;
        this.courierId = courierId;
        this.lat = lat;
        this.lng = lng;
        this.time = time;
    }

    public CourierTracking() {

    }

    @PrePersist
    public void prePersist() {
        time = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Long getCourierId() {
        return courierId;
    }

    public void setCourierId(Long courierId) {
        this.courierId = courierId;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

}
