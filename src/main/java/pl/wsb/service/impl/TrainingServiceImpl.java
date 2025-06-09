package pl.wsb.service.impl;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.training.internal.TrainingRepository;
import pl.wsb.fitnesstracker.user.internal.UserRepository;
import pl.wsb.service.TrainingService;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TrainingServiceImpl implements TrainingService {
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private Date LocalDate;

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
    public List<Training> getTrainingsBeforeDate(LocalDate Localdate) {
        return trainingRepository.findByStartTimeBefore(LocalDate);
    }

    @Override
    public List<Training> findByActivityType(ActivityType activityType) {
        return trainingRepository.findByActivityType(activityType);
    }


    @Override
    public Training createTraining(TrainingDto dto) {
        Optional<User> userOpt = userRepository.findById(dto.getUserId());
        if (userOpt.isEmpty()) throw new RuntimeException("User not found");

        Training training = new Training();
        training.setActivityType(ActivityType.valueOf(dto.getActivityType()));
        training.setDistance(dto.getDistance());
        training.setStartTime(dto.getStartTime());
        training.setUser(userOpt.get());

        return trainingRepository.save(training);
    }

    @Override
    public Training updateTraining(Long id, TrainingDto dto) {
        Training training = trainingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Training not found"));

        training.setActivityType(ActivityType.valueOf(dto.getActivityType()));
        training.setDistance(dto.getDistance());
        training.setStartTime(dto.setStartTime());
        return trainingRepository.save(training);
    }

    @Override
    public List<Training> getTrainingsByActivityType(String activityType) {
        ActivityType type = ActivityType.fromString(activityType);
        return trainingRepository.findByActivityType(type);
    }

}