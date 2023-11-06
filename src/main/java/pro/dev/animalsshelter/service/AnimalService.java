package pro.dev.animalsshelter.service;

import pro.dev.animalsshelter.model.Animal;

import java.util.Collection;

public interface AnimalService {
    Collection<Animal> getAll();

    Animal addAnimal(Animal animal);

    Animal getAnimalById(int id);

    Animal editAnimal(Animal animal);

    Animal deleteAnimalById(int id);
}
