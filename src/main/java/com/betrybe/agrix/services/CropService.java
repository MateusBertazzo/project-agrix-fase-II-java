package com.betrybe.agrix.services;

import com.betrybe.agrix.exceptions.CropNotFoundException;
import com.betrybe.agrix.models.entities.Crop;
import com.betrybe.agrix.models.entities.Fertilizer;
import com.betrybe.agrix.models.repositories.CropRepository;
import com.betrybe.agrix.models.repositories.FertilizerRepository;
import com.betrybe.agrix.exceptions.FertilizerNotFoundException;
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
  private final FertilizerRepository fertilizerRepository;

  public CropService(CropRepository cropRepository, FertilizerRepository fertilizerRepository) {
    this.cropRepository = cropRepository;
    this.fertilizerRepository = fertilizerRepository;
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

  public Crop addFertilizerByCrop(Long cropId, long FertilizerId) {
    Crop crop = cropRepository.findById(cropId).orElseThrow(() -> new CropNotFoundException());

    Fertilizer fertilizer = fertilizerRepository.findById(FertilizerId)
      .orElseThrow(() -> new FertilizerNotFoundException());

    fertilizer.getCrops().add(crop);
    crop.getFertilizers().add(fertilizer);

    fertilizerRepository.save(fertilizer);
    return cropRepository.save(crop);
  }
}
