package edu.bellevue.cis530.week4.controller;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // CRUD

    @PostMapping
    public ResponseEntity<Student> create(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(student));
    }

    @GetMapping
    public List<Student> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @RequestBody Student student) {
        return studentService.updateStudent(id, student);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        studentService.deleteStudentById(id);
        return ResponseEntity.ok(Map.of("message", "Student " + id + " deleted"));
    }

    // Derived queries

    @GetMapping("/major/{major}")
    public List<Student> byMajor(@PathVariable String major) {
        return studentService.getByMajor(major);
    }

    @GetMapping("/gpa/{gpa}")
    public List<Student> byGpaGreaterThan(@PathVariable Double gpa) {
        return studentService.getByGpaGreaterThan(gpa);
    }

    @GetMapping("/year/{year}")
    public List<Student> byYearGreaterThan(@PathVariable Integer year) {
        return studentService.getByEnrollmentYearGreaterThan(year);
    }

    @GetMapping("/top3")
    public List<Student> top3() {
        return studentService.getTop3ByGpa();
    }

    // Sorting and paging

    @GetMapping("/sort")
    public List<Student> sorted(@RequestParam(defaultValue = "asc") String direction) {
        return studentService.getAllSortedByLastName(direction);
    }

    @GetMapping("/page")
    public Page<Student> paged(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "5") int size) {
        return studentService.getStudentsPaged(page, size);
    }

    // Custom JPQL

    @GetMapping("/major-jpql/{major}")
    public List<Student> byMajorJpql(@PathVariable String major) {
        return studentService.getByMajorJpql(major);
    }

    @DeleteMapping("/year/{year}/jpql")
    public ResponseEntity<Map<String, Object>> deleteByYearJpql(@PathVariable Integer year) {
        int deleted = studentService.deleteByEnrollmentYearJpql(year);
        return ResponseEntity.ok(Map.of(
                "message", "Deleted students enrolled in " + year,
                "deletedCount", deleted));
    }
}
