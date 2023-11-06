package pro.dev.animalsshelter.service;

import pro.dev.animalsshelter.model.Shelter;

import java.util.Collection;

public interface ShelterService {
    Collection<Shelter> getAll();

    Shelter addShelter(Shelter shelter);

    Shelter getShelterById(int id);

    Shelter editShelter(Shelter shelter);

    Shelter deleteShelterById(int id);
}
