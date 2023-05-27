package com.example.laba.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MinMiddleMaxModel {
    @JsonProperty("min")
    private Double min;
    @JsonProperty("middle")
    private Double middle;
    @JsonProperty("max")
    private Double max;
}
