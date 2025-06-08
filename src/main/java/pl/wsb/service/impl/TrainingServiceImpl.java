package pl.wsb.service.impl;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.persistence.User;
import pl.wsb.persistence.TrainingRepository;
import pl.wsb.respository.UserRepository;
import pl.wsb.service.TrainingService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;

    public TrainingServiceImpl(TrainingRepository trainingRepository, UserRepository userRepository) {
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }

    @Override
    public List<Training> getTrainingsByUserId(Long userId) {
        return trainingRepository.findByUserId(userId);
    }

    @Override
    public List<Training> getTrainingsBeforeDate(LocalDate date) {
        return trainingRepository.findByDateBefore(date);
    }

    @Override
    public List<Training> getTrainingsByActivityType(String activityType) {
        return trainingRepository.findByActivityTypeIgnoreCase(activityType);
    }

    @Override
    public Training createTraining(TrainingDto dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        Training training = new Training();
        training.setActivityType(dto.getActivityType());
        training.setDistance(dto.getDistance());
        training.setDate(dto.getDate());
        training.setUser(userOpt.get());

        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, TrainingDto dto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        training.setActivityType(dto.getActivityType());
        training.setDistance(dto.getDistance());
        training.setDate(dto.getDate());
        return trainingRepository.save(training);
    }
}