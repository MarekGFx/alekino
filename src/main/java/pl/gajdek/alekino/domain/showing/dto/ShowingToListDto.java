package pl.gajdek.alekino.domain.showing.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ShowingToListDto {

    private long id;
    private LocalDateTime startTime;
 //   private double hour;
    private String hour;
    private String movieTitle;
    private String movieGenre;
    private int cinemaRoomNumber;
}
