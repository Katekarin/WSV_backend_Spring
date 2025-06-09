package pl.wsb.mapper;

import java.time.ZoneId;
import java.util.Date;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.fitnesstracker.training.internal.ActivityType;
import pl.wsb.fitnesstracker.user.api.User;

public class TrainingMapper {

    public static TrainingDto toDto(Training training) {
        TrainingDto dto = new TrainingDto();
        dto.setId(training.getId());
        dto.setUserId(training.getUser() != null ? training.getUser().getId() : null);
        dto.setActivityType(training.getActivityType().toString());
        dto.setDistance(training.getDistance());
        dto.setCreatedAt(training.getCreatedAt().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        dto.setEndTime(training.getEndTime().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime());

        return dto;
    }

    public static Training toEntity(TrainingDto dto, User user) {
        Training training = new Training();
        training.setUser(user);
        training.setActivityType(ActivityType.valueOf(dto.getActivityType()));
        training.setDistance(dto.getDistance());
        training.setStartTime(Date.from(dto.getStartTime().atZone(ZoneId.systemDefault()).toInstant()));
        training.setEndTime(Date.from(dto.getEndTime().atZone(ZoneId.systemDefault()).toInstant()));
        return training;
    }

    public static void updateEntity(Training training, TrainingDto dto) {
        if (dto.getDistance() != null) {
            training.setDistance(dto.getDistance());
        }
        if (dto.getActivityType() != null) {
            training.setActivityType(ActivityType.valueOf(dto.getActivityType()));
        }
        if (dto.getStartTime() != null) {
            training.setStartTime(Date.from(dto.getStartTime().atZone(ZoneId.systemDefault()).toInstant()));
        }
        if (dto.getEndTime() != null) {
            training.setEndTime(Date.from(dto.getEndTime().atZone(ZoneId.systemDefault()).toInstant()));

        }
    }

}
