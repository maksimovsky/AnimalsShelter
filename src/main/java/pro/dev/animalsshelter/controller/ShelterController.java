package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalsshelter.model.Shelter;
import pro.dev.animalsshelter.service.ShelterServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("shelter")
@Tag(name = "Приюты", description = "Методы работы с приютами")
public class ShelterController {
    ShelterServiceImpl service;

    public ShelterController(ShelterServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Получение всех приютов")
    @GetMapping
    public ResponseEntity<Collection<Shelter>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание приюта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Приют успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос")
            }
    )
    @PostMapping
    public ResponseEntity<Shelter> addShelter(@RequestBody Shelter shelter) {
        return validate(service.addShelter(shelter));
    }

    @Operation(summary = "Получение приюта по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Приют успешно получен"),
                    @ApiResponse(responseCode = "204", description = "Приют c данным id не найден")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Shelter> getShelter(@Parameter(description = "id искомого приюта")
                                            @PathVariable int id) {
        return validate(service.getShelterById(id));
    }

    @Operation(summary = "Изменение приюта",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Приют успешно изменен"),
                    @ApiResponse(responseCode = "204", description = "Приют c данным id не найден")
            }
    )
    @PutMapping
    public ResponseEntity<Shelter> editShelter(@RequestBody Shelter shelter) {
        return validate(service.editShelter(shelter));
    }

    @Operation(summary = "Удаление приюта по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Приют успешно удален"),
                    @ApiResponse(responseCode = "204", description = "Приют c данным id не найден")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Shelter> deleteShelter(@PathVariable int id) {
        return validate(service.deleteShelterById(id));
    }

    private static ResponseEntity<Shelter> validate(Shelter o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}