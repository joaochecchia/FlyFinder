package FlyFinder.com.FlyFinderBackend.Infra.Persistence.repository;

import FlyFinder.com.FlyFinderBackend.Infra.Persistence.entities.PassagensModel;
import org.hibernate.sql.ast.tree.expression.JdbcParameter;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PassagensRepository extends JpaRepository<PassagensModel, Long> {
}
