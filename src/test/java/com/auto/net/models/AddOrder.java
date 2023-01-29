package com.auto.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.joda.time.DateTime;

import java.util.Date;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class AddOrder implements Request{
   public Long id;
   public Long petId;
   public int quantity;
   public Date shipDate;
   public boolean complete;
   public String status;
}
