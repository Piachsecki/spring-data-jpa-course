package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    @Query(
            "SELECT s FROM Student s WHERE s.email = ?1"
    )
    Optional<Student> findStudentByEmail(String email);

    @Query(
            "SELECT s from Student s WHERE s.firstName = ?1 AND s.age > ?2"
    )
    List<Student> method1(
            String firstName, Integer age
    );

    @Query(
            value = "SELECT * FROM student WHERE first_name = :firstName AND age >= :age",
            nativeQuery = true
    )
    List<Student> selectStudentWhereFirstNameAndAgeIsGreaterOrEqualNative(
            @Param("firstName") String firstName,
            @Param("age") Integer age
    );

    @Modifying
    @Query(
            "DELETE FROM Student u WHERE u.id =?1"
    )
    void deleteById(Long id);

}
