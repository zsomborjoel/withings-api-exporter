
package com.api.exporter.model.HeartListApi;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "deviceid",
    "model",
    "ecg",
    "bloodpressure",
    "heart_rate",
    "timestamp"
})
@Table(name = "heartlist", schema="withings")
public class Series {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "heartlist_id", nullable = true)
    private Long id;

    @JsonProperty("deviceid")
    @Column(name = "device_id", nullable = true)
    private String deviceid;

    @JsonProperty("model")
    @Column(name = "model", nullable = true)
    private Integer model;

    @Embedded
    @JsonProperty("ecg")
    private Ecg ecg;

    @Embedded
    @JsonProperty("bloodpressure")
    private Bloodpressure bloodpressure;

    @JsonProperty("heart_rate")
    @Column(name = "heart_rate", nullable = true)
    private Integer heartRate;

    @JsonProperty("timestamp")
    @Column(name = "ctimestamp", nullable = true)
    private Integer timestamp;

    @JsonIgnore
    @Column(name = "call_startdate", nullable = true)
    private LocalDate startdate;

    @JsonIgnore
    @Column(name = "call_enddate", nullable = true)
    private LocalDate enddate;
   
}
