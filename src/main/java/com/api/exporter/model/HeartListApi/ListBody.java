
package com.api.exporter.model.HeartListApi;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "series",
    "offset",
    "more"
})
public class ListBody {

    @JsonProperty("series")
    private List<Series> series = null;

    @JsonProperty("offset")
    private Integer offset;
    
    @JsonProperty("more")
    private Boolean more;

    public List<Series> getSeries() {
        return this.series;
    }

    public void setSeries(List<Series> series) {
        this.series = series;
    }

    public Integer getOffset() {
        return this.offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Boolean isMore() {
        return this.more;
    }

    public Boolean getMore() {
        return this.more;
    }

    public void setMore(Boolean more) {
        this.more = more;
    }
   
    @Override
    public String toString() {
        return "{" +
            " series='" + getSeries() + "'" +
            ", offset='" + getOffset() + "'" +
            ", more='" + isMore() + "'" +
            "}";
    }

}
