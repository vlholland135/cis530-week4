package edu.bellevue.cis530.week4.service.impl;

import edu.bellevue.cis530.week4.entity.Student;
import edu.bellevue.cis530.week4.exception.ResourceNotFoundException;
import edu.bellevue.cis530.week4.repository.StudentRepository;
import edu.bellevue.cis530.week4.service.StudentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student createStudent(Student student) {
        student.setId(null); // make sure a new row is always inserted
        return studentRepository.save(student);
    }

    @Override
    public Student getStudentById(Long id) {
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public Student updateStudent(Long id, Student student) {
        Student existing = getStudentById(id);
        existing.setFirstName(student.getFirstName());
        existing.setLastName(student.getLastName());
        existing.setEmail(student.getEmail());
        existing.setMajor(student.getMajor());
        existing.setGpa(student.getGpa());
        existing.setEnrollmentYear(student.getEnrollmentYear());
        return studentRepository.save(existing);
    }

    @Override
    public void deleteStudentById(Long id) {
        Student existing = getStudentById(id);
        studentRepository.delete(existing);
    }

    @Override
    public List<Student> getByMajor(String major) {
        return studentRepository.findByMajor(major);
    }

    @Override
    public List<Student> getByGpaGreaterThan(Double gpa) {
        return studentRepository.findByGpaGreaterThan(gpa);
    }

    @Override
    public List<Student> getByEnrollmentYearGreaterThan(Integer year) {
        return studentRepository.findByEnrollmentYearGreaterThan(year);
    }

    @Override
    public List<Student> getTop3ByGpa() {
        return studentRepository.findTop3ByOrderByGpaDesc();
    }

    @Override
    public List<Student> getAllSortedByLastName(String direction) {
        Sort.Direction dir;
        if ("asc".equalsIgnoreCase(direction)) {
            dir = Sort.Direction.ASC;
        } else if ("desc".equalsIgnoreCase(direction)) {
            dir = Sort.Direction.DESC;
        } else {
            throw new IllegalArgumentException("direction must be 'asc' or 'desc'");
        }
        return studentRepository.findAll(Sort.by(dir, "lastName"));
    }

    @Override
    public Page<Student> getStudentsPaged(int page, int size) {
        if (page < 0 || size < 1) {
            throw new IllegalArgumentException("page must be 0 or greater and size must be at least 1");
        }
        return studentRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public List<Student> getByMajorJpql(String major) {
        return studentRepository.findByMajorJpql(major);
    }

    @Override
    public int deleteByEnrollmentYearJpql(Integer year) {
        return studentRepository.deleteByEnrollmentYearJpql(year);
    }
}
