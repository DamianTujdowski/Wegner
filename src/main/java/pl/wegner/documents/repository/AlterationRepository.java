package pl.wegner.documents.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wegner.documents.model.entities.Alteration;

@Repository
public interface AlterationRepository extends JpaRepository<Alteration, Long> {
}
