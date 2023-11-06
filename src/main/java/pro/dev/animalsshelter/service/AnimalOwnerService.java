package pro.dev.animalsshelter.service;

import pro.dev.animalsshelter.model.AnimalOwner;

import java.util.Collection;

public interface AnimalOwnerService {
    Collection<AnimalOwner> getAll();

    AnimalOwner addAnimalOwner(AnimalOwner owner);

    AnimalOwner getAnimalOwnerById(int id);

    AnimalOwner editAnimalOwner(AnimalOwner owner);

    AnimalOwner deleteAnimalOwnerById(int id);
}
