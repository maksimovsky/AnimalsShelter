package pro.dev.animalsshelter.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.dev.animalsshelter.model.Report;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {

}