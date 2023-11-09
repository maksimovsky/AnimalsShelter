package pro.dev.animalsshelter.DTO;


import java.time.LocalDate;

public class AnimalDto {
    private int id;
    private String name;
    private String type;
    private String breed;
    private LocalDate dateOfBirth;
    private int remainder_of_probation_period;
    private int shelterId;
    private int ownerId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public int getRemainder_of_probation_period() {
        return remainder_of_probation_period;
    }

    public void setRemainder_of_probation_period(int remainder_of_probation_period) {
        this.remainder_of_probation_period = remainder_of_probation_period;
    }

    public int getShelterId() {
        return shelterId;
    }

    public void setShelterId(int shelterId) {
        this.shelterId = shelterId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }
}