package pl.gajdek.alekino.domain.movie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findAllByPremiereIsTrue();

    List<Movie> findAllByGenre_NameIgnoreCase(String genre);

}
