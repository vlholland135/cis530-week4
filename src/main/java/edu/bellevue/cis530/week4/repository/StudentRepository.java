package edu.bellevue.cis530.week4.repository;

import edu.bellevue.cis530.week4.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Derived queries
    List<Student> findByMajor(String major);

    List<Student> findByGpaGreaterThan(Double gpa);

    List<Student> findByEnrollmentYearGreaterThan(Integer year);

    List<Student> findTop3ByOrderByGpaDesc();

    // Custom JPQL queries
    @Query("SELECT s FROM Student s WHERE s.major = :major")
    List<Student> findByMajorJpql(@Param("major") String major);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.enrollmentYear = :year")
    int deleteByEnrollmentYearJpql(@Param("year") Integer year);
}
