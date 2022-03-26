package telran.annotation.example;

import java.time.LocalDate;

import telran.annotation.Column;
import telran.annotation.Id;
import telran.annotation.Table;

@Table

public class Person {
	@Id
	int id;
	
	String name;
	
	@Column(name="birth_date")
	LocalDate birthDate;
}
