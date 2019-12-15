package com.api.exporter.model;

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
public class HeartGet extends BaseEntity {

    @Column(name = "signal_id", nullable = true)
    private String signal_id;

    @Column(name = "signal", nullable = true)
    private int signal;

    @Column(name = "sampling_frequency", nullable = true)
    private int samplingFrequency;

    @Column(name = "wearposition", nullable = true)
    private int wearposition; 
    
}