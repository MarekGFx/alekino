package pl.gajdek.alekino.domain.repertoire.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RepertoireToMainPageDto {

    private long id;
    private LocalDate date;
    private DayOfWeek day;
}
