package com.greatLearning.studentManagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {

	@Autowired
	private StudentService studentService;

	@RequestMapping("/list")
	public String listStudents(Model model) {

		List<Student> students = studentService.findAll();

		model.addAttribute("Students", students);

		return "List-students";

	}

	@RequestMapping("/showAddForm")
	public String showAddForm(Model model) {

		Student student = new Student();

		model.addAttribute("Student", student);

		return "Student-form";

	}

	@RequestMapping("/updateStudentForm")
	public String updateStudentForm(@RequestParam("studentId") int id, Model model) {

		Student student = studentService.findById(id);

		model.addAttribute("Student", student);

		return "Student-form";
	}

	@PostMapping("/save")
	public String saveStudent(@RequestParam("id") int id, @RequestParam("firstName") String firstName,
			@RequestParam("lastName") String lastName, @RequestParam("department") String department,
			@RequestParam("country") String country) {

		Student student;

		if (id != 0) {

			student = studentService.findById(id);
			student.setFirstName(firstName);
			student.setLastName(lastName);
			student.setDepartment(department);
			student.setCountry(country);

		} else
			student = new Student(firstName, lastName, department, country);

		studentService.save(student);

		return "redirect:/students/list";

	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("studentId") int id) {

		studentService.deleteById(id);

		return "redirect:/students/list";

	}

	@RequestMapping("/403")
	public ModelAndView accesssDenied(Principal user) {

		ModelAndView model = new ModelAndView();

		if (user != null) {
			model.addObject("msg", "Hi " + user.getName() + ", you do not have permission to access this page!");
		} else {
			model.addObject("msg", "You do not have permission to access this page!");
		}

		model.setViewName("403");
		return model;

	}

}
