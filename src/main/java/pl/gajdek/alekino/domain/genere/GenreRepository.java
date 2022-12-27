package pl.gajdek.alekino.domain.genere;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface GenreRepository extends CrudRepository<Genre, Long> {

    Optional<Genre> findByNameIgnoreCase(String name);
}
