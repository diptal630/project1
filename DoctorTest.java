package com.cg.bookmydoctor.testcases;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Repository;

import com.cg.bookmydoctor.entities.Appointment;
import com.cg.bookmydoctor.entities.AvailabilityDates;
import com.cg.bookmydoctor.entities.Doctor;
import com.cg.bookmydoctor.entities.Patient;
import com.cg.bookmydoctor.repository.IAvailabilityDatesRepository;
import com.cg.bookmydoctor.repository.IDoctorRepository;
import com.cg.bookmydoctor.service.IDoctorService;

@SpringBootTest
public class DoctorTest {

	@Autowired
	private IDoctorService doctorService;

	@MockBean
	IDoctorRepository doctorRepository;

	@MockBean
	IAvailabilityDatesRepository availabilityDatesRepository;

	@Test
	void contextLoads() {
		// TODO Auto-generated method stub

	}

	// test case for adding Doctor

	@Test
	public void testAddDoctor() {
		// TODO Auto-generated method stub

		Doctor doctor = getDoctor();

		Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
		Doctor result = doctorService.addDoctor(doctor);
		assertEquals(doctor, result); // to check whether the objects are equal

	}

	

	// test case for getting Doctor by id
	@Test
	public void testGetDoctor() {
		Doctor doctor = getDoctor();
		doctorService.getDoctor(doctor.getDoctorId());
		verify(doctorRepository, times(1)).findById(doctor.getDoctorId()); // Verify that the correct methods of our
																			// mock objects were invoked
	}

	// test case for removing Doctor by id
	@Test
	public void testRemoveDoctor() {
		Doctor doctor = getDoctor();
		doctorService.removeDoctor(doctor.getDoctorId());
		verify(doctorRepository, times(1)).existsById(doctor.getDoctorId());
	}

	// test case for fetching all Doctors
	@Test
	public void testGetAllDoctors() {
		when(doctorRepository.findAll()).thenReturn(Stream.of(getDoctor()).collect(Collectors.toList()));
		assertEquals(1, doctorService.getDoctorList().size());
	}

	// test case for fetching all Doctors by speciality
	@Test
	public void testGetDoctorListBySpeciality() {
		Doctor doctor = getDoctor();
		when(doctorRepository.findBySpeciality(doctor.getSpeciality()))
				.thenReturn(Stream.of(doctor).collect(Collectors.toList()));
		assertEquals(1, doctorService.getDoctorList(doctor.getSpeciality()).size());
	}

	// test case for adding availability
	@Test
	public void testAddAvailability() {
		Doctor doctor = getDoctor();
		AvailabilityDates dates = getAvailabilityDates();
		Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);

		Mockito.when(availabilityDatesRepository.save(dates)).thenReturn(dates);

		assertEquals(true, doctorService.addAvailability(dates));

	}

	// test case for updating availability
	@Test
	public void testUpdateAvailability() {
		Doctor doctor = getDoctor();
		AvailabilityDates dates = getAvailabilityDates();
		Mockito.when(doctorRepository.save(doctor)).thenReturn(doctor);
		Mockito.when(availabilityDatesRepository.save(dates)).thenReturn(dates);

		///assertEquals(true, doctorService.updateAvailability(dates));

	}

	private Doctor getDoctor() {

		Doctor doctor = new Doctor();
		doctor.setDoctorId(1);
		doctor.setChargesPerVisit(45.5);
		doctor.setDoctorName("Rohan");
		doctor.setEmail("rohan@gmail.com");
		doctor.setHospitalName("govt hospital");
		doctor.setLocation("Nagpur");
		doctor.setMobileNo(7972794);
		doctor.setPassword("dt5Fg");
		doctor.setSpeciality("eye doctor");
		return doctor;
	}

	private AvailabilityDates getAvailabilityDates() {

		AvailabilityDates availabilityDates = new AvailabilityDates();

		Doctor doctor = getDoctor();

		availabilityDates.setAvailabilityId(3);
		availabilityDates.setDoctor(doctor);
		availabilityDates.setFromDate(LocalDate.now());
		availabilityDates.setFromDate(LocalDate.now());
		return availabilityDates;

	}

}
