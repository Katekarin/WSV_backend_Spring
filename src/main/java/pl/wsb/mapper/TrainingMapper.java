package pl.wsb.mapper;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;

public class TrainingMapper {

    public static TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setUserId(training.getUserId());
        dto.setActivityType(training.getActivityType());
        dto.setDistance(training.getDistance());
        dto.setStartTime(training.getStartTime());
        dto.setEndTime(training.getEndTime());
        return dto;
    }

    public static Training toEntity(TrainingDto dto) {
        Training training = new Training();
        training.setId(dto.getId());
        training.setUserId(dto.getUserId());
        training.setActivityType(dto.getActivityType());
        training.setDistance(dto.getDistance());
        training.setStartTime(dto.getStartTime());
        training.setEndTime(dto.getEndTime());
        return training;
    }

    public static void updateEntity(Training training, TrainingDto dto) {
        if (dto.getDistance() != null) {
            training.setDistance(dto.getDistance());
        }
        if (dto.getActivityType() != null) {
            training.setActivityType(dto.getActivityType());
        }
        if (dto.getStartTime() != null) {
            training.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            training.setEndTime(dto.getEndTime());
        }
    }
}
