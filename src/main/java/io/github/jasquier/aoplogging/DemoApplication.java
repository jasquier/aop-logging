package io.github.jasquier.aoplogging;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import io.github.jasquier.aoplogging.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication {

  private final EmployeeService employeeService;

  @Bean
  public CommandLineRunner run(ApplicationContext context) {
    return args -> {
      log.info("|||--- Starting CommandLineRunner ---|||");

      // run some methods and also log the return value
      log.info(employeeService.getById(1L).toString());
      log.info(employeeService.getById(2L).toString());

      // can also simply run methods to get timing
      employeeService.countToABillion();

      log.info("|||--- Ending CommandLineRunner ---|||");
    };
  }

  public static void main(String[] args) {
    SpringApplication.run(DemoApplication.class, args);
  }

}
