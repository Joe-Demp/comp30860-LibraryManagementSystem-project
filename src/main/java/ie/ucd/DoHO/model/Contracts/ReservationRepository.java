package ie.ucd.DoHO.model.Contracts;

import ie.ucd.DoHO.model.Artifact;
import ie.ucd.DoHO.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> findByUserId(Integer user_id);
    List<Reservation> findByUserAndArtifact(User user, Artifact artifact);
    List<Reservation> findByArtifactId(Integer artifact_id);
}
