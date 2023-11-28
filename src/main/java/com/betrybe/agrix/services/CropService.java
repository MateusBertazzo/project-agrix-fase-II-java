package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.repositories.CropRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

/**
 * Crop service.
 */
@Service
public class CropService {
  private final CropRepository cropRepository;

  public CropService(CropRepository cropRepository) {
    this.cropRepository = cropRepository;
  }

  /**
   * Get All crops.
   */
  public List<Crop> getAllCrop() {
    return cropRepository.findAll();
  }

  /**
   * Get crop by id.
   */
  public Crop getCropById(Long id) {
    Optional<Crop> crop = cropRepository.findById(id);
    if (crop.isEmpty()) {
      throw new CropNotFoundException();
    }
    return crop.get();
  }

  /**
   * Get crop by harvestDateBetween.
   */
  public List<Crop> getCropByHarvestDateBetween(LocalDate start, LocalDate end) {
    List<Crop> crops = cropRepository.findByHarvestDateBetween(start, end);

    if (crops.isEmpty()) {
      throw new CropNotFoundException();
    }

    return crops;
  }
}
