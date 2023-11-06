package pro.dev.animalsshelter.service;

import pro.dev.animalsshelter.model.Volunteer;

import java.util.Collection;

public interface VolunteerService {
    Collection<Volunteer> getAll();

    Volunteer addVolunteer(Volunteer volunteer);

    Volunteer getVolunteerById(int id);

    Volunteer editVolunteer(Volunteer volunteer);

    Volunteer deleteVolunteerById(int id);
}