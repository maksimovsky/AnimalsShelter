package pro.dev.animalsshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalsshelter.model.AnimalOwner;

@Repository
public interface AnimalOwnerRepository extends JpaRepository<AnimalOwner, Integer> {

}