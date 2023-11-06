package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalsshelter.model.AnimalOwner;
import pro.dev.animalsshelter.repository.AnimalOwnerRepository;

import java.util.Collection;

@Service
public class AnimalOwnerServiceImpl implements AnimalOwnerService {
    private final AnimalOwnerRepository repository;
    Logger logger = LoggerFactory.getLogger(AnimalOwnerServiceImpl.class);

    public AnimalOwnerServiceImpl(AnimalOwnerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<AnimalOwner> getAll() {
        logger.info("Getting all animal owners");
        return repository.findAll();
    }

    @Override
    public AnimalOwner addAnimalOwner(AnimalOwner animalOwner) {
        logger.info("Adding animal owner: " + animalOwner.getName());
        return repository.save(animalOwner);
    }

    @Override
    public AnimalOwner getAnimalOwnerById(int id) {
        logger.info("Getting animal owner by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public AnimalOwner editAnimalOwner(AnimalOwner animalOwner) {
        logger.info("Editing animal owner with id " + animalOwner.getId());
        if (getAnimalOwnerById(animalOwner.getId()) == null) {
            return null;
        }
        return repository.save(animalOwner);
    }

    @Override
    public AnimalOwner deleteAnimalOwnerById(int id) {
        logger.info("Deleting animal owner by id " + id);
        AnimalOwner animalOwner = getAnimalOwnerById(id);
        if (animalOwner == null) {
            return null;
        }
        repository.deleteById(id);
        return animalOwner;
    }
}