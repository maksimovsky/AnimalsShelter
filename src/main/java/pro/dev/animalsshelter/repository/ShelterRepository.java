package pro.dev.animalsshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalsshelter.model.Shelter;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

}