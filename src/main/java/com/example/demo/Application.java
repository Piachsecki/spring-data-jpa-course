package com.example.demo;

import com.github.javafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    CommandLineRunner commandLineRunner (StudentRepository studentRepository){
        return args -> {

            generateStudents(studentRepository);
//            sorting(studentRepository);
            PageRequest pageRequest = PageRequest.of(0, 5,
                    Sort.by("firstName").ascending()
            );
            Page<Student> page = studentRepository.findAll(pageRequest);

            System.out.println(page);

//            Student maria1 = new Student(
//                    "Maria",
//                    "Jones",
//                    "maria.jones@amigoscode.edu",
//                    21
//            );
//
//            Student maria2 = new Student(
//                    "Maria",
//                    "Jones",
//                    "maria2.jones@amigoscode.edu",
//                    20
//            );
//
//            Student ahmed = new Student(
//                    "Ahmed",
//                    "Ali",
//                    "ahmed.ali@amigoscode.edu",
//                    19
//            );

//            System.out.println("Adding Maria and Ahmed");
//            studentRepository.saveAll(List.of(maria1, ahmed, maria2));

//            studentRepository.findStudentByEmail("ahmed.ali@amigoscode.edu").
//                    ifPresentOrElse(System.out::println,
//                            () -> System.out.println("Student with this email not found !"));
//
//
//            studentRepository.method1(
//                    "Maria",
//                    19
//            ).forEach(System.out::println);
//
//
//            studentRepository.selectStudentWhereFirstNameAndAgeIsGreaterOrEqualNative("Maria", 12).forEach(
//                    System.out::println
//            );
//            System.out.println("Select all students1");
//            List<Student> students1 = studentRepository.findAll();
//            students1.forEach(System.out::println);
//
//            studentRepository.deleteById(3L);
//
//            System.out.println("Select all students");
//            List<Student> students2 = studentRepository.findAll();
//            students2.forEach(System.out::println);
//            System.out.println("Number of students: ");
//            System.out.println(studentRepository.count());
//
//            studentRepository.findById(2L).ifPresentOrElse(
//                    System.out::println,
//                    () -> System.out.println("Student with ID = 2 not found")
//                    );
//
//            studentRepository.findById(3L).ifPresentOrElse(
//                    System.out::println,
//                    () -> System.out.println("Student with ID = 3 not found")
//            );


//            System.out.println("Select all students");
//            List<Student> students = studentRepository.findAll();
//
//            students.forEach(System.out::println);
//
//
//            System.out.println("Delete maria");
//            studentRepository.deleteById(1L);
//
//
//            System.out.println("Number of students: ");
//            System.out.println(studentRepository.count());

        };


    }

    private static void sorting(StudentRepository studentRepository) {
        Sort sort = Sort.
                by("firstName").ascending().
                and(Sort.by("age"))
                .descending();
        studentRepository.findAll(sort).forEach(
                student -> System.out.println(student.getFirstName() + " " + student.getAge())
        );
    }

    private static void generateStudents(StudentRepository studentRepository) {
        Faker faker = new Faker();
        for (int i = 0; i < 20; i++) {
            String lastName = faker.name().lastName();
            String firstName = faker.name().firstName();
            String email = String.format("%s.%s@amigoscode.edu", firstName, lastName);
            Student student = new Student(firstName,
                    lastName,
                    email,
                    faker.number().numberBetween(17, 55)
            );
            studentRepository.save(student);

        }
    }

}
