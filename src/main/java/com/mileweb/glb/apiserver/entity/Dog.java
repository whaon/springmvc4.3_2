package com.mileweb.glb.apiserver.entity;

import java.util.Date;

import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.mileweb.glb.apiserver.validation.DogNameDuplicate;
import com.mileweb.glb.apiserver.validation.ValidationHolder.DogName;

public class Dog {

	//@NotNull(message="not allow null")
	//@JsonProperty("input_name")
	//@DogNameDuplicate(groups={DogName.class}, message="{org.hibernate.validator.constraints.Email.message}", init = {"dog", "mydog"})
	@DogNameDuplicate(groups={DogName.class}, message="{msg}", init = {"dog", "mydog"})
	private String name;
	private int age;
	@JsonFormat(pattern="yyyy-MM-dd")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Past(message="pastError")
	private Date birth; 

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", age=" + age + ", birth=" + birth + "]";
	}
}

