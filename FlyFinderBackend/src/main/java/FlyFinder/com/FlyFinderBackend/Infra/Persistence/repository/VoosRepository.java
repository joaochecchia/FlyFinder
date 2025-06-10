package FlyFinder.com.FlyFinderBackend.Infra.Persistence.repository;

import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.VoosModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VoosRepository extends JpaRepository<VoosModel, Long> {
}
