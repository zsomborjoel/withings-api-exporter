
package com.api.exporter.model.HeartGetApi;

import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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

}