package ie.ucd.DoHO.model.Contracts;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.DayOfWeek;

public interface OpeningHoursRepository extends JpaRepository<OpeningHours, DayOfWeek> {
}
