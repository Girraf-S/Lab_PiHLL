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
public class ShowModel {
    @JsonProperty("x")
    private Double x;
    @JsonProperty("y")
    private Double y;
    @JsonProperty("mode")
    private char mode;
    @JsonProperty("result")
    private Double result;
}
