package pl.wsb.controller;

import pl.wsb.doto.TrainingDto;
import pl.wsb.fitnesstracker.training.api.Training;
import pl.wsb.service.TrainingService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/trainings")
public class TrainingController {
    private final TrainingService trainingService;

    public TrainingController(TrainingService trainingService) {
        this.trainingService = trainingService;
    }

    @GetMapping
    public List<Training> getAllTrainings() {
        return trainingService.getAllTrainings();
    }

    @GetMapping("/user/{userId}")
    public List<Training> getTrainingsByUser(@PathVariable Long userId) {
        return trainingService.getTrainingsByUserId(userId);
    }

    @GetMapping("/before/{date}")
    public List<Training> getTrainingsBefore(@PathVariable String date) {
        return trainingService.getTrainingsBeforeDate(LocalDate.parse(date));
    }

    @GetMapping("/activity/{type}")
    public List<Training> getTrainingsByActivity(@PathVariable String type) {
        return trainingService.getTrainingsByActivityType(type);
    }

    @PostMapping
    public Training createTraining(@RequestBody TrainingDto dto) {
        return trainingService.createTraining(dto);
    }

    @PutMapping("/{id}")
    public Training updateTraining(@PathVariable Long id, @RequestBody TrainingDto dto) {
        return trainingService.updateTraining(id, dto);
    }
}
