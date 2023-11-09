package pro.dev.animalsshelter.service;

import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalsshelter.DTO.AnimalDto;
import pro.dev.animalsshelter.model.Animal;

import java.util.List;

public interface AnimalService {
    List<AnimalDto> getAll();

    AnimalDto addAnimal(String name, String type, String breed,
                        int dayOfBirth, int monthOfBirth, int yearOfBirth, MultipartFile photo);

    AnimalDto getAnimalDtoById(int id);

    Animal getAnimalById(int id);

    AnimalDto editAnimal(int id, String name, String type, String breed, Integer dayOfBirth,
                         Integer monthOfBirth, Integer yearOfBirth, MultipartFile photo);

    AnimalDto deleteAnimalById(int id);
}
