package service;

import java.util.ArrayList;

import domain.Major;
import domain.Student;

public interface IMajorService {
	abstract ArrayList<Major> getAllMajors();
	abstract Major getMajor(String id);
}
