
package com.api.exporter.model.HeartListApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import com.api.exporter.model.HeartListApi.ListBody;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "body"
})
public class  ListResponse {

    @JsonProperty("status")
    private Integer status;
    
    @JsonProperty("body")
    private ListBody body;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public ListBody getBody() {
        return this.body;
    }

    public void setBody(ListBody body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "{" +
            " status='" + getStatus() + "'" +
            ", body='" + getBody() + "'" +
            "}";
    }
    
}
