package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalsshelter.model.Volunteer;
import pro.dev.animalsshelter.service.VolunteerServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("volunteer")
@Tag(name = "Волонтеры", description = "Методы работы с волонтерами")
public class VolunteerController {
    VolunteerServiceImpl service;

    public VolunteerController(VolunteerServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Получение всех волонтеров")
    @GetMapping
    public ResponseEntity<Collection<Volunteer>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание волонтера",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Волонтер успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос")
            }
    )
    @PostMapping
    public ResponseEntity<Volunteer> addVolunteer(@RequestBody Volunteer volunteer) {
        return validate(service.addVolunteer(volunteer));
    }

    @Operation(summary = "Получение волонтера по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Волонтер успешно получен"),
                    @ApiResponse(responseCode = "204", description = "Волонтер c данным id не найден")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Volunteer> getVolunteer(@Parameter(description = "id искомого волонтера")
                                            @PathVariable int id) {
        return validate(service.getVolunteerById(id));
    }

    @Operation(summary = "Изменение волонтера",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Волонтер успешно изменен"),
                    @ApiResponse(responseCode = "204", description = "Волонтер c данным id не найден")
            }
    )
    @PutMapping
    public ResponseEntity<Volunteer> editVolunteer(@RequestBody Volunteer volunteer) {
        return validate(service.editVolunteer(volunteer));
    }

    @Operation(summary = "Удаление волонтера по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Волонтер успешно удален"),
                    @ApiResponse(responseCode = "204", description = "Волонтер c данным id не найден")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Volunteer> deleteVolunteer(@PathVariable int id) {
        return validate(service.deleteVolunteerById(id));
    }

    private static ResponseEntity<Volunteer> validate(Volunteer o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}