package pl.wsb.persistence;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String activityType;
    private double distance;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}