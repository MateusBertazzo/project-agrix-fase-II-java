package com.betrybe.agrix.dto;

import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Farm;

/**
* Crop DTO.
*/
public record CropDto(Long id, String name, Long farmId, Double plantedArea) {
  
  /**
  * Crop DTO.
  */
  public Crop toCrop() {
    Farm farm = new Farm();
    farm.setId(farmId);
    return new Crop(id, name, farm, plantedArea);
  }
}

