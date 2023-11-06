package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalsshelter.model.Animal;
import pro.dev.animalsshelter.service.AnimalServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("animal")
@Tag(name = "Животные", description = "Методы работы с животными")
public class AnimalController {
    AnimalServiceImpl service;

    public AnimalController(AnimalServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Получение всех животных")
    @GetMapping
    public ResponseEntity<Collection<Animal>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание животного",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно добавлено"),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос")
            }
    )
    @PostMapping
    public ResponseEntity<Animal> addAnimal(@RequestBody Animal animal) {
        return validate(service.addAnimal(animal));
    }

    @Operation(summary = "Получение животного по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно получено"),
                    @ApiResponse(responseCode = "204", description = "Животное c данным id не найдено")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Animal> getAnimal(@Parameter(description = "id искомого животного")
                                            @PathVariable int id) {
        return validate(service.getAnimalById(id));
    }

    @Operation(summary = "Изменение животного",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно изменено"),
                    @ApiResponse(responseCode = "204", description = "Животное c данным id не найдено")
            }
    )
    @PutMapping
    public ResponseEntity<Animal> editAnimal(@RequestBody Animal animal) {
        return validate(service.editAnimal(animal));
    }

    @Operation(summary = "Удаление животного по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно удалено"),
                    @ApiResponse(responseCode = "204", description = "Животное c данным id не найдено")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Animal> deleteAnimal(@PathVariable int id) {
        return validate(service.deleteAnimalById(id));
    }

    private static ResponseEntity<Animal> validate(Animal o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}