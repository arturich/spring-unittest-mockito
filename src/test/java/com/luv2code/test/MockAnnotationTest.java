package com.luv2code.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import com.luv2code.component.MvcTestingExampleApplication;
import com.luv2code.component.dao.ApplicationDao;
import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import com.luv2code.component.service.ApplicationService;

@SpringBootTest(classes = MvcTestingExampleApplication.class)
class MockAnnotationTest {
	
	@Autowired
	ApplicationContext context;
	
	@Autowired
	CollegeStudent studentOne;
	
	@Autowired
	StudentGrades studentGrades;
	
	@Mock
	private ApplicationDao applicationDao;
	
	@InjectMocks
	private ApplicationService applicationService;
	
	@BeforeEach
	public void beforeEach()
	{
		studentOne.setFirstname("Erick");
		studentOne.setLastname("Roby");
		studentOne.setEmailAddress("erick.roby@luv2code_school.com");
		studentOne.setStudentGrades(studentGrades);
		
	}

	@DisplayName("When & Verify")
	@Test
	void assertEqualsTestAddGrades() {
		
		// Set expectations
		when(applicationDao.addGradeResultsForSingleClass(
				studentGrades.getMathGradeResults()))
		.thenReturn(100.00);
		
		//Execute and Assert
		assertEquals(100, applicationService.addGradeResultsForSingleClass(
				studentOne.getStudentGrades().getMathGradeResults()));
		
		//Verify that the method ran
		verify(applicationDao).addGradeResultsForSingleClass(studentGrades
				.getMathGradeResults());
		//how many times ran
		verify(applicationDao, times(1)).addGradeResultsForSingleClass(studentGrades
				.getMathGradeResults());
		
	}

}
