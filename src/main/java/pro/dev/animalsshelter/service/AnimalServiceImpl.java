package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalsshelter.DTO.AnimalDto;
import pro.dev.animalsshelter.mapper.AnimalMapper;
import pro.dev.animalsshelter.model.Animal;
import pro.dev.animalsshelter.repository.AnimalRepository;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AnimalServiceImpl implements AnimalService {
    private final AnimalRepository repository;
    Logger logger = LoggerFactory.getLogger(AnimalServiceImpl.class);

    public AnimalServiceImpl(AnimalRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public List<AnimalDto> getAll() {
        logger.info("Getting all animals");
        return repository.findAllByOrderById().stream().map(AnimalMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public AnimalDto addAnimal(String name, String type, String breed,
                               int dayOfBirth, int monthOfBirth, int yearOfBirth, MultipartFile photo) {
        logger.info("Adding animal: " + name);
        Animal animal = new Animal();
        animal.setName(name);
        animal.setBreed(breed);
        try {
            animal.setType(Animal.Type.valueOf(type.toUpperCase()));
        } catch (IllegalArgumentException e) {
            logger.info("Неверный вид животного");
            return null;
        }
        try {
            animal.setDateOfBirth(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth));
        } catch (DateTimeException e) {
            logger.info("Неверный формат даты");
            return null;
        }
        animal = savePhoto(animal, photo);
        if (animal == null) {
            return null;
        }
        return AnimalMapper.toDto(repository.save(animal));
    }

    @Override
    public AnimalDto getAnimalDtoById(int id) {
        logger.info("Getting animal by id " + id);
        Animal animal = repository.findById(id).orElse(null);
        return AnimalMapper.toDto(animal);
    }

    @Override
    public Animal getAnimalById(int id) {
        logger.info("Getting animal by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public AnimalDto editAnimal(int id, String name, String type, String breed, Integer dayOfBirth,
                                Integer monthOfBirth, Integer yearOfBirth, MultipartFile photo) {
        logger.info("Editing animal with id " + id);
        Animal animal = getAnimalById(id);
        if (animal == null) {
            return null;
        }
        if (name != null) {
            animal.setName(name);
        }
        if (type != null) {
            try {
                animal.setType(Animal.Type.valueOf(type.toUpperCase()));
            } catch (IllegalArgumentException e) {
                logger.info("Неверный вид животного");
                return null;
            }
        }
        if (breed != null) {
            animal.setBreed(breed);
        }
        if (dayOfBirth == null) {
            dayOfBirth = animal.getDateOfBirth().getDayOfMonth();
        }
        if (monthOfBirth == null) {
            monthOfBirth = animal.getDateOfBirth().getMonthValue();
        }
        if (yearOfBirth == null) {
            yearOfBirth = animal.getDateOfBirth().getYear();
        }
        try {
            animal.setDateOfBirth(LocalDate.of(yearOfBirth, monthOfBirth, dayOfBirth));
        } catch (DateTimeException e) {
            logger.info("Неверный формат даты");
            return null;
        }
        if (photo != null) {
            animal = savePhoto(animal, photo);
            if (animal == null) {
                return null;
            }
        }
        return AnimalMapper.toDto(repository.save(animal));
    }

    @Override
    public AnimalDto deleteAnimalById(int id) {
        logger.info("Deleting animal by id " + id);
        Animal animal = getAnimalById(id);
        if (animal == null) {
            return null;
        }
        repository.deleteById(id);
        return AnimalMapper.toDto(animal);
    }

    private Animal savePhoto(Animal animal, MultipartFile photo) {
        if (!photo.getContentType().startsWith("image")) {
            logger.info("Загруженный файл не является изображением");
            return null;
        }
        if (photo.getSize() > 1024 * 1024) {
            logger.info("Загруженное фото больше 1 Мб");
            return null;
        }
        try {
            animal.setPhoto(photo.getBytes());
            animal.setPhotoSize(photo.getSize());
            animal.setMediaType(photo.getContentType());
        } catch (IOException e) {
            logger.info("Ошибка при сохранении фото");
            return null;
        }
        return animal;
    }
}