package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalsshelter.model.Animal;
import pro.dev.animalsshelter.repository.AnimalRepository;

import java.util.Collection;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository repository;
    Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Animal> getAll() {
        logger.info("Getting all animals");
        return repository.findAll();
    }

    @Override
    public Animal addAnimal(Animal animal) {
        logger.info("Adding animal: " + animal.getName());
        return repository.save(animal);
    }

    @Override
    public Animal getAnimalById(int id) {
        logger.info("Getting animal by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Animal editAnimal(Animal animal) {
        logger.info("Editing animal with id " + animal.getId());
        if (getAnimalById(animal.getId()) == null) {
            return null;
        }
        return repository.save(animal);
    }

    @Override
    public Animal deleteAnimalById(int id) {
        logger.info("Deleting animal by id " + id);
        Animal animal = getAnimalById(id);
        if (animal == null) {
            return null;
        }
        repository.deleteById(id);
        return animal;
    }
}