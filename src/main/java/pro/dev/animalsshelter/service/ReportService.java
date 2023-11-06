package pro.dev.animalsshelter.service;

import pro.dev.animalsshelter.model.Report;

import java.util.Collection;

public interface ReportService {
    Collection<Report> getAll();

    Report addReport(Report report);

    Report getReportById(int id);

    Report editReport(Report report);

    Report deleteReportById(int id);
}
