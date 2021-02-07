package pl.wegner.documents.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.wegner.documents.model.entities.Proof;

import java.util.List;

@Repository
public interface ProofRepository extends JpaRepository<Proof, Long> {

    List<Proof> findAllBy(Pageable pageable);
}
