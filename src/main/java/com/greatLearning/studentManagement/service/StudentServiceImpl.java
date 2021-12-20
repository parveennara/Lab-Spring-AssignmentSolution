package com.greatLearning.studentManagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greatLearning.studentManagement.entity.Student;
import com.greatLearning.studentManagement.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	@Autowired
	StudentRepository studentRepository;

	@Override
	public List<Student> findAll() {

		List<Student> students = studentRepository.findAll();
		return students;
	}

	@Override
	public Student findById(int id) {

		Student student = studentRepository.findById(id).get();
		return student;
	}

	@Override
	public void save(Student student) {

		studentRepository.save(student);

	}

	@Override
	public void deleteById(int id) {

		studentRepository.deleteById(id);

	}

}
