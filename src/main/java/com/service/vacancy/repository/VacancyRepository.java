package com.service.vacancy.repository;

import com.service.vacancy.entity.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface VacancyRepository extends JpaRepository<Vacancy, Long> {

    @Query("SELECT v FROM Vacancy v WHERE " +
            "(:name IS NULL OR LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))) AND " +
            "(:position IS NULL OR LOWER(v.position) LIKE LOWER(CONCAT('%', :position, '%'))) AND " +
            "(:city IS NULL OR LOWER(v.city) LIKE LOWER(CONCAT('%', :city, '%')))")
    List<Vacancy> findByFilters(@Param("name") String name,
                                @Param("position") String position,
                                @Param("city") String city);

    List<Vacancy> findByCreatedAtAfter(LocalDateTime since);
}
