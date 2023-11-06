package pro.dev.animalsshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
public class AnimalOwner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int phone;
    private long telegramId;
    private long chatId;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<Report> reports;
    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    private Set<Animal> animals;

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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public long getTelegramId() {
        return telegramId;
    }

    public void setTelegramId(long telegramId) {
        this.telegramId = telegramId;
    }

    public long getChatId() {
        return chatId;
    }

    public void setChatId(long chatId) {
        this.chatId = chatId;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AnimalOwner that = (AnimalOwner) o;

        if (phone != that.phone) return false;
        if (telegramId != that.telegramId) return false;
        return chatId == that.chatId;
    }

    @Override
    public int hashCode() {
        int result = phone;
        result = 31 * result + (int) (telegramId ^ (telegramId >>> 32));
        result = 31 * result + (int) (chatId ^ (chatId >>> 32));
        return result;
    }
}
