package com.auto.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Inventory {
    public int confirm;
    public int sold;
    public int avail;
    public int string;
    public int availible;
    public int pending;
    public int available;
    @JsonProperty("new reclived")
    public int newreclived;
    @JsonProperty("newly reclived")
    public int newlyreclived;
    @JsonProperty("@@status@@")
    public int status;
    public int placed;
}
