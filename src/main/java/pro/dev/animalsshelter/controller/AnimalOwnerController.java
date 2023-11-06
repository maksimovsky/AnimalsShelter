package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalsshelter.model.AnimalOwner;
import pro.dev.animalsshelter.service.AnimalOwnerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("animal_owner")
@Tag(name = "Владельцы животных", description = "Методы работы с владельцами животных")
public class AnimalOwnerController {
    AnimalOwnerServiceImpl service;

    public AnimalOwnerController(AnimalOwnerServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Получение всех владельцев")
    @GetMapping
    public ResponseEntity<Collection<AnimalOwner>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание владельца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Владелец успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос")
            }
    )
    @PostMapping
    public ResponseEntity<AnimalOwner> addAnimalOwner(@RequestBody AnimalOwner animalOwner) {
        return validate(service.addAnimalOwner(animalOwner));
    }

    @Operation(summary = "Получение владельца по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Владелец успешно получен"),
                    @ApiResponse(responseCode = "204", description = "Владелец c данным id не найден")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<AnimalOwner> getAnimalOwner(@Parameter(description = "id искомого владельца")
                                            @PathVariable int id) {
        return validate(service.getAnimalOwnerById(id));
    }

    @Operation(summary = "Изменение владельца",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Владелец успешно изменен"),
                    @ApiResponse(responseCode = "204", description = "Владелец c данным id не найден")
            }
    )
    @PutMapping
    public ResponseEntity<AnimalOwner> editAnimalOwner(@RequestBody AnimalOwner animalOwner) {
        return validate(service.editAnimalOwner(animalOwner));
    }

    @Operation(summary = "Удаление владельца по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Владелец успешно удален"),
                    @ApiResponse(responseCode = "204", description = "Владелец c данным id не найден")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<AnimalOwner> deleteAnimalOwner(@PathVariable int id) {
        return validate(service.deleteAnimalOwnerById(id));
    }

    private static ResponseEntity<AnimalOwner> validate(AnimalOwner o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}