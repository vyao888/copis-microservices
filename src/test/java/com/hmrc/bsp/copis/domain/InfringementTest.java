package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class InfringementTest {

	private static final String ID = "GB12334567";
	private static final LocalDate DETENTION_DATE = LocalDate.now();
	private static final String GROSS_WEIGHT = "GrossWeight";
	private static final String CUSTOMER_REFERENCE = "Reference1234";

	@Autowired
	private Validator validator;

	private Infringement infringement = null;

	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.infringement = createInfringement();
	}

	@After
	public void tearDown() throws Exception {
		this.infringement = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.infringement.equals(createInfringementViaConstructor()));
		assertTrue(this.infringement.hashCode() == createInfringementViaConstructor().hashCode());
		Map<String, Infringement> map = new HashMap<>();
		map.put(this.infringement.getId(), this.infringement);
		Infringement e = map.get(this.infringement.getId());
		assertNotNull(e);
		e = new Infringement();
		assertNotNull(e);
	}

	@Test
	public void testInfringementIdOk() {
		assertEquals(ID, this.infringement.getId());
	}

	@Test
	public void testInfringementIdNullFail() {
		this.infringement.setId(null);
		assertViolations();
	}

	@Test
	public void testInfringementIdEmptyFail() {
		this.infringement.setId("");
		assertViolations();
	}

	@Test
	public void testInfringementIdTooShortFail() {
		this.infringement.setId("GB");
		assertViolations();
	}

	@Test
	public void testInfringementIdTooLongFail() {
		this.infringement.setId("GB123456789012345678901234567890");
		assertViolations();
	}

	@Test
	public void testInvalidInfringementIdFail() {
		this.infringement.setId("GA1234567890");
		assertViolations();
		this.infringement.setId("GB12345A67890");
		assertViolations();
		this.infringement.setId("GB1234567890.");
		assertViolations();
	}


	@Test
	public void testDetentionDateOk() {
		assertEquals(DETENTION_DATE, this.infringement.getDetentionDate());
	}

	@Test
	public void testDetentionDateInTheFutureFail() {
		this.infringement.setDetentionDate(DETENTION_DATE.plusDays(1));
		assertViolations();
	}

	@Test
	public void testGrossWeight() {
		assertEquals(GROSS_WEIGHT, this.infringement.getGrossWeight());
	}

	@Test
	public void testReferenceNumber() {
		assertEquals(CUSTOMER_REFERENCE, this.infringement.getReferenceNumber());
	}

	@Test
	public void testReferenceNumberNullFail() {
		this.infringement.setReferenceNumber(null);
		assertViolations();
	}

	@Test
	public void testInfringementEqualsOrHashcode() {
		final Infringement r = Infringement.builder().id(ID).build();
		assertEquals(this.infringement, r);
		assertEquals(this.infringement.hashCode(), r.hashCode());
	}

	public static Infringement createInfringement() {
		return Infringement.builder()
				.id(ID)
				.detentionDate(DETENTION_DATE)
				.grossWeight(GROSS_WEIGHT)
				.referenceNumber(CUSTOMER_REFERENCE)
				.build();
	}

	private Infringement createInfringementViaConstructor() {
		return new Infringement(ID, DETENTION_DATE, GROSS_WEIGHT, CUSTOMER_REFERENCE);
	}

	private void assertViolations() {
		Set<ConstraintViolation<Infringement>> violations = validator.validate(this.infringement);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
