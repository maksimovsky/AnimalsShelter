package pro.dev.animalsshelter.model;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime reportTime;
    @ManyToOne
    @JoinColumn(name = "owner_id")
    private AnimalOwner owner;
    @ManyToOne
    @JoinColumn(name = "animal_id")
    private Animal animal;
    private String eat;
    private String health;
    private String behaviouralChanges;
    @Lob
    private byte[] photo;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getReportTime() {
        return reportTime;
    }

    public void setReportTime(LocalDateTime reportTime) {
        this.reportTime = reportTime;
    }

    public AnimalOwner getOwner() {
        return owner;
    }

    public void setOwner(AnimalOwner owner) {
        this.owner = owner;
    }

    public Animal getAnimal() {
        return animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public String getEat() {
        return eat;
    }

    public void setEat(String eat) {
        this.eat = eat;
    }

    public String getHealth() {
        return health;
    }

    public void setHealth(String health) {
        this.health = health;
    }

    public String getBehaviouralChanges() {
        return behaviouralChanges;
    }

    public void setBehaviouralChanges(String behaviouralChanges) {
        this.behaviouralChanges = behaviouralChanges;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Report report = (Report) o;

        if (id != report.id) return false;
        if (!Objects.equals(owner, report.owner)) return false;
        return Objects.equals(animal, report.animal);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (animal != null ? animal.hashCode() : 0);
        return result;
    }
}
