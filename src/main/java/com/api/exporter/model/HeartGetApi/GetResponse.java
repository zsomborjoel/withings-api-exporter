package com.api.exporter.model.HeartGetApi;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "status",
    "body"
})
@ToString
public class GetResponse {

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("body")
    private GetBody body;

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public GetBody getBody() {
        return this.body;
    }

    public void setBody(GetBody body) {
        this.body = body;
    }

}
