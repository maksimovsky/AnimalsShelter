package pro.dev.animalsshelter.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.dev.animalsshelter.model.Report;
import pro.dev.animalsshelter.service.ReportServiceImpl;

import java.util.Collection;

@RestController
@RequestMapping("report")
@Tag(name = "Отчеты", description = "Методы работы с отчетами")
public class ReportController {
    ReportServiceImpl service;

    public ReportController(ReportServiceImpl service) {
        this.service = service;
    }

    @Operation(summary = "Получение всех отчетов")
    @GetMapping
    public ResponseEntity<Collection<Report>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @Operation(summary = "Создание отчета",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отчет успешно добавлен"),
                    @ApiResponse(responseCode = "400", description = "Неверный запрос")
            }
    )
    @PostMapping
    public ResponseEntity<Report> addReport(@RequestBody Report report) {
        return validate(service.addReport(report));
    }

    @Operation(summary = "Получение отчета по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отчет успешно получен"),
                    @ApiResponse(responseCode = "204", description = "Отчет c данным id не найден")
            }
    )
    @GetMapping("{id}")
    public ResponseEntity<Report> getReport(@Parameter(description = "id искомого отчета")
                                            @PathVariable int id) {
        return validate(service.getReportById(id));
    }

    @Operation(summary = "Изменение отчета",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Необходимо заполнить поля ..."),
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отчет успешно изменен"),
                    @ApiResponse(responseCode = "204", description = "Отчет c данным id не найден")
            }
    )
    @PutMapping
    public ResponseEntity<Report> editReport(@RequestBody Report report) {
        return validate(service.editReport(report));
    }

    @Operation(summary = "Удаление отчета по id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Отчет успешно удален"),
                    @ApiResponse(responseCode = "204", description = "Отчет c данным id не найден")
            }
    )
    @DeleteMapping("{id}")
    public ResponseEntity<Report> deleteReport(@PathVariable int id) {
        return validate(service.deleteReportById(id));
    }

    private static ResponseEntity<Report> validate(Report o) {
        if (o == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.ok(o);
    }
}