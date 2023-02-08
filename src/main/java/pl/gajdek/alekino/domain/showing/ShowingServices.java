package pl.gajdek.alekino.domain.showing;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.gajdek.alekino.constantCinemaData.ConstantDataForCinema;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoom;
import pl.gajdek.alekino.domain.cinemaRoom.CinemaRoomRepository;
import pl.gajdek.alekino.domain.cinemaRoom.Seat;
import pl.gajdek.alekino.domain.movie.Movie;
import pl.gajdek.alekino.domain.movie.MovieRepository;
import pl.gajdek.alekino.domain.showing.dto.CreateShowDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingDto;
import pl.gajdek.alekino.domain.showing.dto.ShowingToListDto;
import pl.gajdek.alekino.domain.showing.map.ShowingMapperDto;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeat;
import pl.gajdek.alekino.domain.showingSeat.ShowingSeatRepository;
import pl.gajdek.alekino.domain.showingSeat.dto.ShowingSeatDto;
import pl.gajdek.alekino.domain.showingSeat.map.ShowingSeatDtoMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShowingServices {

    private ShowingRepository showingRepository;
    private CinemaRoomRepository cinemaRoomRepository;
    private MovieRepository movieRepository;
    private ShowingSeatRepository showingSeatRepository;
    private ShowingMapperDto showingMapperDto;

    public ResponseEntity<?> getListAllShowings() {
        List<ShowingToListDto> showingDtoList = showingRepository.findAll()
                .stream()
                .map(showingMapperDto::mapToShowingListDto)
                .toList();
        return ResponseEntity.ok(showingDtoList);
    }

    public ResponseEntity<?> getShowing(long id) {
        Optional<ShowingDto> showingDto = showingRepository.findById(id).map(showingMapperDto::toDto);
        if (showingDto.isPresent()){
            List<ShowingSeatDto> seatDtoList = showingDto.get().getSeatDtoList();
            if (seatDtoList.isEmpty() ) {
                Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findByRoomNumber(showingDto.get().getCinemaRoomNumber());
                if (cinemaRoom.isPresent()){
                    List<Seat> seatList = cinemaRoom.get().getSeats();
                    seatDtoList = seatList.stream().map(ShowingSeatDtoMapper::mapToShowingSeatDto).toList();
                    showingDto.get().setSeatDtoList(seatDtoList);
                    return ResponseEntity.ok(showingDto);
                } else
                    return ResponseEntity.status(404).body("Cinema room in for current show dose not exist");
            }
            return ResponseEntity.ok(showingDto);
        } else
            return ResponseEntity.status(404).body("Showing with Id " + id + " does not exist");
    }

    public ResponseEntity<?> getListShowingsByMovie(long id) {
        List<ShowingToListDto> showingDtoList = showingRepository.findByMovieId(id)
                .stream()
                .map(showingMapperDto::mapToShowingListDto)
                .toList();
        return ResponseEntity.ok(showingDtoList);
    }

    public ResponseEntity<?> createShow(CreateShowDto showToAdd){
        if (showToAdd != null) {
            Showing showing = new Showing();
            showing.setStartTime(showToAdd.getStartTime());
            showing.setShowHour();
            showing.setShowMin();
            Optional<Movie> movie = movieRepository.findById(showToAdd.getMovieId());
            if (movie.isPresent()) {
                showing.setMovie(movie.get());
            } else {
                return ResponseEntity.status(404).body("Movie with Id " + showToAdd.getMovieId() + " does not exist");
            }
            Optional<CinemaRoom> cinemaRoom = cinemaRoomRepository.findById(showToAdd.getCinemaRoomId());
            if (cinemaRoom.isPresent()) {
                List<Showing> showings = showingRepository.findByCinemaRoomId(cinemaRoom.get().getId());

                if(showing.getStartTime().getHour() > 22){
                    return ResponseEntity.status(400).body("Not possible to add a screening after 11 p.m!");
                }
                if (showing.getStartTime().getHour() < 10){
                    return ResponseEntity.status(400).body("Cinema is open from ten o'clock, it is not possible to add a screening before this hour");
                }

                if (!showings.isEmpty()) {

                    for (Showing showToCheckSameHour : showings) {
                        if (showToAdd.getStartTime().equals(showToCheckSameHour.getStartTime())) {
                            return ResponseEntity.status(400).body("There is another show in this room at the same time");
                        }
                    }

                    for (Showing show : showings) {
                        if (showing.getStartTime()
                                .minusHours(getHourFromMovieRunTime(movie.get().getRunTimeInMin()))
                                .minusMinutes(getMinFromMovieRunTime(movie.get().getRunTimeInMin())
                                        + ConstantDataForCinema.REST_IN_ROOM).isBefore(show.getStartTime())) {
                            if (showing.getStartTime()
                                    .plusHours(getHourFromMovieRunTime(movie.get().getRunTimeInMin()))
                                    .plusMinutes(getMinFromMovieRunTime(movie.get().getRunTimeInMin())
                                            + ConstantDataForCinema.REST_IN_ROOM).isAfter(show.getStartTime())) {
                                return ResponseEntity.status(400).body("There should be 10 min. break between shows in the same cinema room");
                            }
                        }
                    }
                }
                return generateShow(showing, cinemaRoom);

            } else
                return ResponseEntity.status(404).body("Cinema room with Id " + showToAdd.getCinemaRoomId() + " does not exist");
        }
        return ResponseEntity.status(404).body("Can not create show");
    }

    private ResponseEntity<String> generateShow(Showing showing, Optional<CinemaRoom> cinemaRoom) {
        showing.setCinemaRoom(cinemaRoom.get());
        showingRepository.save(showing);

        List<Seat> seatList = cinemaRoom.get().getSeats();
        List<ShowingSeat> showingSeatList = new ArrayList<>();
        for (Seat seat : seatList) {
            ShowingSeat showingSeat;
            showingSeat = ShowingSeatDtoMapper.mapFromShowingSeatDto(seat, showing);
            showingSeatList.add(showingSeat);
            showingSeatRepository.save(showingSeat);
        }
        showing.setSeats(showingSeatList);
        return ResponseEntity.status(201).body("Show successful created");
    }

    private int getHourFromMovieRunTime(int time){
        return time/60;
    }

    private int getMinFromMovieRunTime(int time){
        return time - (getHourFromMovieRunTime(time) * 60);
    }



}
