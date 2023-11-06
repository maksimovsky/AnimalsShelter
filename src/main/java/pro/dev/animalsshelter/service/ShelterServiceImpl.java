package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalsshelter.model.Shelter;
import pro.dev.animalsshelter.repository.ShelterRepository;

import java.util.Collection;

@Service
public class ShelterServiceImpl implements ShelterService {
    private final ShelterRepository repository;
    Logger logger = LoggerFactory.getLogger(ShelterServiceImpl.class);

    public ShelterServiceImpl(ShelterRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Shelter> getAll() {
        logger.info("Getting all shelters");
        return repository.findAll();
    }

    @Override
    public Shelter addShelter(Shelter shelter) {
        logger.info("Adding shelter: " + shelter.getName());
        return repository.save(shelter);
    }

    @Override
    public Shelter getShelterById(int id) {
        logger.info("Getting shelter by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Shelter editShelter(Shelter shelter) {
        logger.info("Editing shelter with id " + shelter.getId());
        if (getShelterById(shelter.getId()) == null) {
            return null;
        }
        return repository.save(shelter);
    }

    @Override
    public Shelter deleteShelterById(int id) {
        logger.info("Deleting shelter by id " + id);
        Shelter shelter = getShelterById(id);
        if (shelter == null) {
            return null;
        }
        repository.deleteById(id);
        return shelter;
    }
}