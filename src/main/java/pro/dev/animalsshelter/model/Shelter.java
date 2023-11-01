package pro.dev.animalsshelter.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Shelter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String info;
    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private Set<Animal> animals;
    @OneToMany(mappedBy = "shelter")
    @JsonIgnore
    private Set<Volunteer> volunteers;

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

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }

    public Set<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(Set<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Shelter shelter = (Shelter) o;

        if (id != shelter.id) return false;
        return Objects.equals(name, shelter.name);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
