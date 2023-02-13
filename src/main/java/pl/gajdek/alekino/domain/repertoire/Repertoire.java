package pl.gajdek.alekino.domain.repertoire;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pl.gajdek.alekino.domain.showing.Showing;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Repertoire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate repertoireDate;

    @OneToMany(mappedBy = "repertoire")
    private List<Showing> showing;
    @Enumerated(EnumType.STRING)
    private DayOfWeek dayOfWeek;

    public LocalDate getRepertoireDate() {
        return repertoireDate;
    }

    public void setRepertoireDate(LocalDate repertoireDate) {
        this.repertoireDate = repertoireDate;
    }

    public DayOfWeek getDayOfWeek() {
        return repertoireDate.getDayOfWeek();
    }

    public void setDayOfWeek() {
        this.dayOfWeek = repertoireDate.getDayOfWeek();
    }
}
