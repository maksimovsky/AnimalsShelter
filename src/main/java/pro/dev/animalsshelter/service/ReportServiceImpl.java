package pro.dev.animalsshelter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import pro.dev.animalsshelter.model.Report;
import pro.dev.animalsshelter.repository.ReportRepository;

import java.util.Collection;

@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository repository;
    Logger logger = LoggerFactory.getLogger(ReportServiceImpl.class);

    public ReportServiceImpl(ReportRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Report> getAll() {
        logger.info("Getting all reports");
        return repository.findAll();
    }

    @Override
    public Report addReport(Report report) {
        logger.info("Adding report: " + report.getId());
        return repository.save(report);
    }

    @Override
    public Report getReportById(int id) {
        logger.info("Getting report by id " + id);
        return repository.findById(id).orElse(null);
    }

    @Override
    public Report editReport(Report report) {
        logger.info("Editing report with id " + report.getId());
        if (getReportById(report.getId()) == null) {
            return null;
        }
        return repository.save(report);
    }

    @Override
    public Report deleteReportById(int id) {
        logger.info("Deleting report by id " + id);
        Report report = getReportById(id);
        if (report == null) {
            return null;
        }
        repository.deleteById(id);
        return report;
    }
}