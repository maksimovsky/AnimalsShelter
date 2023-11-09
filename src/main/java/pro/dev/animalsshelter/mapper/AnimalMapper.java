package pro.dev.animalsshelter.mapper;

import pro.dev.animalsshelter.DTO.AnimalDto;
import pro.dev.animalsshelter.model.Animal;

public class AnimalMapper {
    public static AnimalDto toDto(Animal animal) {
        if (animal == null) {
            return null;
        }
        AnimalDto dto = new AnimalDto();
        dto.setId(animal.getId());
        dto.setName(animal.getName());
        dto.setType(animal.getType().toString());
        dto.setBreed(animal.getBreed());
        dto.setDateOfBirth(animal.getDateOfBirth());
        dto.setRemainder_of_probation_period(animal.getRemainderOfProbationPeriod());

        if (animal.getShelter() != null) {
            dto.setShelterId(animal.getShelter().getId());
        }
        if (animal.getOwner() != null) {
            dto.setOwnerId(animal.getOwner().getId());
        }
        return dto;
    }
}