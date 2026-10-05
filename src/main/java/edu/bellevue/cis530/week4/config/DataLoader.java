package edu.bellevue.cis530.week4.config;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/** Seeds 12 sample students on first startup only (skipped if the table already has rows). */
@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner seedStudents(StudentRepository repo) {
        return args -> {
            if (repo.count() > 0) {
                return;
            }
            repo.saveAll(List.of(
                new Student("Sara", "Feilmann", "sara.feilmann@example.com", "Computer Science", 3.75, 2024),
                new Student("Liam", "Anderson", "liam.anderson@example.com", "Computer Science", 3.42, 2022),
                new Student("Maya", "Patel", "maya.patel@example.com", "Mathematics", 3.91, 2021),
                new Student("Noah", "Brooks", "noah.brooks@example.com", "Business", 3.10, 2023),
                new Student("Ava", "Nguyen", "ava.nguyen@example.com", "Biology", 3.68, 2022),
                new Student("Ethan", "Rivera", "ethan.rivera@example.com", "Computer Science", 2.95, 2020),
                new Student("Olivia", "Carter", "olivia.carter@example.com", "Psychology", 3.83, 2024),
                new Student("Lucas", "Mitchell", "lucas.mitchell@example.com", "Mathematics", 3.55, 2023),
                new Student("Emma", "Zhang", "emma.zhang@example.com", "Business", 3.98, 2021),
                new Student("Mason", "Hughes", "mason.hughes@example.com", "Biology", 2.78, 2020),
                new Student("Chloe", "Bennett", "chloe.bennett@example.com", "Psychology", 3.30, 2022),
                new Student("Jack", "Sullivan", "jack.sullivan@example.com", "Computer Science", 3.60, 2025)
            ));
        };
    }
}
