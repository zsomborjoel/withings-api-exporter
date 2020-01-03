
package com.api.exporter.model.HeartGetApi;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
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
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "signal",
    "sampling_frequency",
    "wearposition"
})
@Table(name = "heartget", schema="withings")
public class GetBody {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "heartget_id", nullable = true)
    private Long id;

    @JsonIgnore
    @Column(name = "signal_id", nullable = true)
    private Integer signalId;

    @JsonProperty("signal")
    @ElementCollection
    @CollectionTable(name = "heartgetsignal", schema = "withings")
    @Column(name = "signal", nullable = true)
    private List<Integer> signal = null;

    @JsonProperty("sampling_frequency")
    @Column(name = "sampling_frequency", nullable = true)
    private Integer samplingFrequency;

    @JsonProperty("wearposition")
    @Column(name = "wearposition", nullable = true)
    private Integer wearposition;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Integer getSignalId() {
        return this.signalId;
    }

    public void setSignalId(Integer signalId) {
        this.signalId = signalId;
    }

    public List<Integer> getSignal() {
        return this.signal;
    }

    public void setSignal(List<Integer> signal) {
        this.signal = signal;
    }

    public Integer getSamplingFrequency() {
        return this.samplingFrequency;
    }

    public void setSamplingFrequency(Integer samplingFrequency) {
        this.samplingFrequency = samplingFrequency;
    }

    public Integer getWearposition() {
        return this.wearposition;
    }

    public void setWearposition(Integer wearposition) {
        this.wearposition = wearposition;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", signal='" + getSignal() + "'" +
            ", samplingFrequency='" + getSamplingFrequency() + "'" +
            ", wearposition='" + getWearposition() + "'" +
            "}";
    }

}