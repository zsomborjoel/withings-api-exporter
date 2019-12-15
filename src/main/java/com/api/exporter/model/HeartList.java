package com.api.exporter.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@Table(name = "heart_list", schema="withings")
public class HeartList extends BaseEntity {

    @Column(name = "device_id", nullable = true)
    private String deviceId;

    @Column(name = "model", nullable = true)
    private int model;

    @Column(name = "signal_id", nullable = true)
    private int signalId;

    @Column(name = "diastole", nullable = true)
    private int diastole;

    @Column(name = "systole", nullable = true)
    private int systole;

    @Column(name = "heart_rate", nullable = true)
    private int heartRate;

    @Column(name = "call_timestamp", nullable = true)
    private int timestamp;

    @Column(name = "call_startdate", nullable = true)
    private LocalDate startdate;

    @Column(name = "call_enddate", nullable = true)
    private LocalDate enddate;
    
}