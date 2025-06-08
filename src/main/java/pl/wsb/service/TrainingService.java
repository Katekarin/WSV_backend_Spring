package pl.wsb.service;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;

import java.time.LocalDate;
import java.util.List;

public interface TrainingService {
    List<Training> getAllTrainings();
    List<Training> getTrainingsByUserId(Long userId);
    List<Training> getTrainingsBeforeDate(LocalDate date);
    List<Training> getTrainingsByActivityType(String activityType);
    Training createTraining(TrainingDto dto);
    Training updateTraining(Long id, TrainingDto dto);
}