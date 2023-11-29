package com.betrybe.agrix.controllers;

import com.betrybe.agrix.dto.FertilizerDto;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.services.FertilizerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


/**
 * Fertilizer controller.
 */
@RestController
@RequestMapping("/fertilizers")
public class FertilizerController {
  
  private final FertilizerService fertilizerService;

  @Autowired
  public FertilizerController(FertilizerService fertilizerService) {
    this.fertilizerService = fertilizerService;
  }

  /**
   * Create a new fertilizer.
   */
  @PostMapping()
  @ResponseStatus(HttpStatus.CREATED)
  public FertilizerDto createFertilizer(@RequestBody FertilizerDto fertilizerDto) {
    Fertilizer newFertilizer = fertilizerService.createFertilizer(fertilizerDto.toFertilizer());

    FertilizerDto fertilizerToResponse = 
        new FertilizerDto(newFertilizer.getId(), newFertilizer.getName(), 
          newFertilizer.getBrand(), newFertilizer.getComposition());
    
    return fertilizerToResponse;
  }
}
