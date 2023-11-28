package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.CropDtos;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.services.CropService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Crop controller.
 */
@RestController
@RequestMapping("/crops")
public class CropController {
  private final CropService cropService;
  
  @Autowired
  public CropController(CropService cropService) {
    this.cropService = cropService;    
  }

  
  /**
  * Get all crops.
  */
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CropDtos> getAllCrop() {
    List<Crop> crops = cropService.getAllCrop();
    List<CropDtos> cropsDto = crops.stream()
        .map(crop -> 
            new CropDtos(
                crop.getId(), crop.getName(), crop.getFarm().getId(), crop.getPlantedArea()))
                .toList();

    return cropsDto;
  }


  /**
  * Get By Id crop.
  */
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public CropDtos getCropById(@PathVariable Long id) {
    Crop crop = cropService.getCropById(id);
    CropDtos cropServiceResponse = 
        new CropDtos(crop.getId(), crop.getName(), crop.getFarm().getId(), crop.getPlantedArea());

    return cropServiceResponse;
  }
}
