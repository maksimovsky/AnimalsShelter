package pro.dev.animalsshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalsshelter.model.Animal;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Integer> {

}