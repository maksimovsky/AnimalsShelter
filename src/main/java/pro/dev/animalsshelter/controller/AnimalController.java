package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.dev.animalsshelter.DTO.AnimalDto;
import pro.dev.animalsshelter.model.Animal;
import pro.dev.animalsshelter.service.AnimalServiceImpl;

import java.util.List;

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
    public ResponseEntity<List<AnimalDto>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание животного",
            description = "Все поля обязательны для заполнения. " +
                    "Доступные виды животных: cat, dog. " +
                    "Если неизвестен день рождения - указать \"1\". " +
                    "Размер фото не более 1 Мб",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно добавлено"),
                    @ApiResponse(responseCode = "400",
                            description = "Неверный запрос. " +
                                    "Проверьте дату, формат и размер фото")
            }
    )
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimalDto> addAnimal(@RequestParam(name = "Кличка") String name,
                                               @RequestParam(name = "Вид животного") String type,
                                               @RequestParam(name = "Порода") String breed,
                                               @RequestParam(name = "День рождения") int dayOfBirth,
                                               @RequestParam(name = "Месяц рождения") int monthOfBirth,
                                               @RequestParam(name = "Год рождения") int yearOfBirth,
                                               @RequestParam(name = "Фото") MultipartFile photo) {
        AnimalDto animalDto = service.addAnimal(name, type, breed, dayOfBirth, monthOfBirth, yearOfBirth, photo);
        if (animalDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(animalDto);
    }

    @Operation(summary = "Получение фото животного по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Фото найдено"),
                    @ApiResponse(responseCode = "400", description = "Животное c данным id не найдено")
            }
    )
    @GetMapping("photo")
    public ResponseEntity<byte[]> getPhotoByAnimalId(int id) {
        Animal animal = service.getAnimalById(id);
        if (animal == null) {
            return ResponseEntity.badRequest().build();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(animal.getMediaType()));
        headers.setContentLength(animal.getPhotoSize());
        return ResponseEntity.ok().headers(headers).body(animal.getPhoto());
    }

    @Operation(summary = "Получение животного по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно получено"),
                    @ApiResponse(responseCode = "204", description = "Животное c данным id не найдено")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<AnimalDto> getAnimal(@Parameter(description = "id искомого животного")
                                               @PathVariable int id) {
        return validate(service.getAnimalDtoById(id));
    }

    @Operation(summary = "Изменение животного",
            description = "Необходимо указать id и заполнить изменяемые поля",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно изменено"),
                    @ApiResponse(responseCode = "400",
                            description = "Неверный запрос. " +
                                    "Проверьте id, дату, формат и размер фото")
            }
    )
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AnimalDto> editAnimal
            (@RequestParam(name = "id животного") int id,
             @RequestParam(required = false, name = "Кличка") String name,
             @RequestParam(required = false, name = "Вид животного") String type,
             @RequestParam(required = false, name = "Порода") String breed,
             @RequestParam(required = false, name = "День рождения") Integer dayOfBirth,
             @RequestParam(required = false, name = "Месяц рождения") Integer monthOfBirth,
             @RequestParam(required = false, name = "Год рождения") Integer yearOfBirth,
             @RequestParam(required = false, name = "Фото") MultipartFile photo) {
        AnimalDto animalDto = service.editAnimal(id, name, type, breed, dayOfBirth, monthOfBirth, yearOfBirth, photo);
        if (animalDto == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(animalDto);
    }

    @Operation(summary = "Удаление животного по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Животное успешно удалено"),
                    @ApiResponse(responseCode = "204", description = "Животное c данным id не найдено")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<AnimalDto> deleteAnimal(@PathVariable int id) {
        return validate(service.deleteAnimalById(id));
    }

    private static ResponseEntity<AnimalDto> validate(AnimalDto o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}