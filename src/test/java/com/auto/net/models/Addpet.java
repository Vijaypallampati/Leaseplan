package com.auto.net.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)

public class Addpet implements Request{
   public Long id;
   public Category category;
   public String name;
   public List<String> photoUrls;
   public List<Tag> tags;
   public String status;

   public void photoUrls(String name) {
   }
}
