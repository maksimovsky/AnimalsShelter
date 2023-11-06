package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalsshelter.model.Volunteer;
import pro.dev.animalsshelter.repository.VolunteerRepository;

import java.util.Collection;

@Service
public class VolunteerServiceImpl implements VolunteerService {
    private final VolunteerRepository repository;
    Logger logger = LoggerFactory.getLogger(VolunteerServiceImpl.class);

    public VolunteerServiceImpl(VolunteerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Volunteer> getAll() {
        logger.info("Getting all volunteers");
        return repository.findAll();
    }

    @Override
    public Volunteer addVolunteer(Volunteer volunteer) {
        logger.info("Adding volunteer: " + volunteer.getName());
        return repository.save(volunteer);
    }

    @Override
    public Volunteer getVolunteerById(int id) {
        logger.info("Getting volunteer by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Volunteer editVolunteer(Volunteer volunteer) {
        logger.info("Editing volunteer with id " + volunteer.getId());
        if (getVolunteerById(volunteer.getId()) == null) {
            return null;
        }
        return repository.save(volunteer);
    }

    @Override
    public Volunteer deleteVolunteerById(int id) {
        logger.info("Deleting volunteer by id " + id);
        Volunteer volunteer = getVolunteerById(id);
        if (volunteer == null) {
            return null;
        }
        repository.deleteById(id);
        return volunteer;
    }
}