package com.ubl.studentweb;

// import java.time.LocalDate;
// import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ubl.studentweb.domain.Student;

@RestController
public class StudentController {
    // @GetMapping("/hello")
	// public String hello(){
	// 	return "Welcome Student";
	// }

	// Aferil Yudhatama
	// 2112500786

	// @GetMapping("/students")
	// public List<Student> getStudents(){

	// 	List<Student> studentList = new ArrayList<>();

	// 	Student student1 = new Student();
	// 	student1.setFullName("Aferil Yudhatama");
	// 	student1.setAddress("Jakarta");
	// 	student1.setNim("2112500786");
	// 	student1.setDateofBirth(LocalDate.of(2003, 04, 10));


	// 	Student student2 = new Student();
	// 	student2.setFullName("Shani Syafira");
	// 	student2.setAddress("Bekasi");
	// 	student2.setNim("2112500999");
	// 	student2.setDateofBirth(LocalDate.of(2003, 06, 15));

	// 	Student student3 = new Student();
	// 	student3.setFullName("Sinta Ayu Nara");
	// 	student3.setAddress("Jawa Timur");
	// 	student3.setNim("2112500998");
	// 	student3.setDateofBirth(LocalDate.of(2003, 10, 20));

	// 	studentList.addAll(List.of(student1, student2, student3));
	// 	return studentList;
	// }
	
	public static Map<String, Student> studentMap = new HashMap<>();

	@GetMapping("/students")
	public List<Student> getStudents(){
		return studentMap.values().stream().toList();
	}

	@PostMapping("/students")
	public ResponseEntity<String> addStudent(@RequestBody Student student){
		studentMap.put(student.getNim(), student);
		Student savedStudent = studentMap.get(student.getNim());
		return new ResponseEntity<>("Student wit NIM: " + savedStudent.getNim() + " has been created", HttpStatus.OK);
	}

	@GetMapping(value = "/students/{nim}")
	public ResponseEntity<Student> findStudent(@PathVariable("nim") String nim){
		final Student student = studentMap.get(nim);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@PutMapping(value = "/students/{nim}")
	public ResponseEntity<String> updateStudent(@PathVariable("nim") String nim, @RequestBody Student student){
		final Student studentToBeUpdated = studentMap.get(student.getNim());
		studentToBeUpdated.setAddress(student.getAddress());
		studentToBeUpdated.setDateOfBirth(student.getDateOfBirth());
		studentToBeUpdated.setFullName(student.getFullName()); 

		studentMap.put(student.getNim(), studentToBeUpdated);
		return new ResponseEntity<String>("Student with NIM: " + studentToBeUpdated.getNim() + " has been updated", HttpStatus.OK);
	}

	@DeleteMapping(value = "/students/{nim}")
	public ResponseEntity<Void> deleteStudent(@PathVariable("nim") String nim){
		studentMap.remove(nim);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}