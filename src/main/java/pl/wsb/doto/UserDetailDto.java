package pl.wsb.doto;

import java.time.LocalDate;

public record UserDetailDto(
        Long id,
        String firstName,
        String lastName,
        String email,
        LocalDate birthDate
) {}
