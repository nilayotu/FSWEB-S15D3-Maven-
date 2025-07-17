package org.example;

import org.example.entity.Employee;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, Employee> employeeMap;
    private static List<Employee> duplicatedEmployees;

    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, "nilay", "otu"));
        employees.add(new Employee(2, "duru", "fatma"));
        employees.add(new Employee(1, "nilay", "otu"));

        System.out.println("Duplicates: " + findDuplicates(employees));
        System.out.println("Uniques: " + findUniques(employees));
    }

    public static List<Employee> findDuplicates(List<Employee> employees) {
        employeeMap = new HashMap<>();
        duplicatedEmployees = new LinkedList<>();

        for (Employee employee : employees) {
            if (employee == null) {
                System.out.println("null record");
                continue;
            }
            if (employeeMap.containsKey(employee.getId())) {
                duplicatedEmployees.add(employee);
            } else {
                employeeMap.put(employee.getId(), employee);
            }
        }
        return duplicatedEmployees;
    }

    public static Map<Integer, Employee> findUniques(List<Employee> employees) {
        employeeMap = new HashMap<>();

        for (Employee employee : employees) {
            if (employee == null) {
                System.out.println("null record");
                continue;
            }
            if (!employeeMap.containsKey(employee.getId())) {
                employeeMap.put(employee.getId(), employee);
            }
        }
        return employeeMap;
    }

    public static List<Employee> removeDuplicates(List<Employee> employees) {
        if (employees == null) return Collections.emptyList();

        Map<Integer, Long> countMap = employees.stream()
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Employee::getId, Collectors.counting()));

        // Unique ID olanları bul
        List<Employee> uniqueOnes = employees.stream()
                .filter(Objects::nonNull)
                .filter(e -> countMap.get(e.getId()) == 1)
                .toList();

        // Eğer unique olanlardan biri varsa, sonuncusunu döndür
        if (!uniqueOnes.isEmpty()) {
            return List.of(uniqueOnes.get(uniqueOnes.size() - 1));
        }
        return Collections.emptyList();
    }
}