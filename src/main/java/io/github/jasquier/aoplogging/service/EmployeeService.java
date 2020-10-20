package io.github.jasquier.aoplogging.service;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import io.github.jasquier.aoplogging.Employee;

@Service
public class EmployeeService {
  static Map<Long, Employee> db = new HashMap<>();

  static {
    db.put(1L, new Employee(1L, "Samwise", "Gamgee"));
    db.put(2L, new Employee(2L, "Frodo", "Baggins"));
  }

  public Employee getById(Long id) {
    return db.get(id);
  }

  public int countToABillion() {
    int counter = 0;
    for (int i = 0; i < 1000; i++) {
      for (int j = 0; j < 1000000; j++) {
        counter += 1;
      }
    }
    return counter;
  }
}
