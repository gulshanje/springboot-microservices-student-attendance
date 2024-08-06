package com.studentservice.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.studentservice.entities.Student;
import com.studentservice.repositories.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service

public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);

    private final StudentRepository repository;
    private final WebClient.Builder webClientBuilder;
    @Autowired
    private JavaMailSender emailSender;

    @Value("${spring.mail.from}")
    private String senderEmail;

    // @LoadBalanced
    public WebClient.Builder loadBalancedWebClientBuilder() {
        return WebClient.builder();
  
    }
    public StudentService(StudentRepository repository, WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.webClientBuilder = webClientBuilder;
    }

    public Flux<Student> getAllStudents() {
        return repository.findAll();
    }

    public Mono<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    public Mono<Student> createStudent(Student student) {
        return repository.save(student);
    }

    public Mono<Void> deleteStudent(Long id) {
        return repository.deleteById(id);
    }
    public Flux<?> getStudentAttendance(Long studentId) {
        return webClientBuilder.build()
            .get()
            // .uri("http://attendance-service/attendance/student/{studentId}", studentId)
            .uri("http://localhost:8085/attendance/student/{studentId}", studentId)
            .retrieve()
            .bodyToFlux(Object.class);
    }

    public Flux<String> getStudents() {
        WebClient webClient = webClientBuilder.build();
        return Flux.concat(
                webClient.get().uri("http://student-service/students").retrieve().bodyToFlux(String.class)
        );
    }

    public Mono<Void> sendEmail(String to, String subject, String text) {
        logger.debug("Sending email to: {}, subject: {}, text: {}", to, subject, text);
        if (to == null || subject == null || text == null) {
            logger.error("Invalid email parameters: to={}, subject={}, text={}", to, subject, text);
            return Mono.error(new IllegalArgumentException("Email, subject, and text must not be null"));
        }

        return Mono.fromRunnable(() -> {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(senderEmail);
            message.setTo(to);
            message.setSubject(subject);
            message.setText(text);
            emailSender.send(message);
        }).then(Mono.empty());
    }

}
