package ro.uvt.adoption.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.uvt.adoption.model.Animal;

public interface AnimalRepository extends JpaRepository<Animal, Long> {
}
