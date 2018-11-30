package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class IprTest {
	private static final UUID UID = UUID.randomUUID();
	private static final Integer ID = 123;

	@Autowired
	private Validator validator;

	private Ipr ipr = null;

	
	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.ipr = createIpr();
	}

	@After
	public void tearDown() throws Exception {
		this.ipr = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.ipr.equals(createIprViaConstructor()));
		assertTrue(this.ipr.hashCode() == createIprViaConstructor().hashCode());
		Map<String, Ipr> map = new HashMap<>();
		map.put(this.ipr.getUuid().toString() + this.ipr.getId(), this.ipr);
		Ipr e = map.get(this.ipr.getUuid().toString() + this.ipr.getId());
		e = new Ipr();
		assertNotNull(e);
	}

	@Test
	public void testIprUuid() {
		assertEquals(UID, this.ipr.getUuid());
	}

	@Test
	public void testNullUuidFail() {
		this.ipr.setUuid(null);
		assertViolations();
	}

	@Test
	public void testIprId() {
		assertEquals(ID, this.ipr.getId());
	}

	@Test
	public void testNullIdFail() {
		this.ipr.setId(null);
		assertViolations();
	}

	@Test
	public void testIprEqualsOrHashcode() {
		final Ipr r = Ipr.builder().uuid(UID).id(ID).build();
		assertEquals(this.ipr, r);
		assertEquals(this.ipr.hashCode(), r.hashCode());
	}

	private Ipr createIpr() {
		return Ipr.builder()
				.uuid(UID)
				.id(ID)
				.build();
	}

	private Ipr createIprViaConstructor() {
		return new Ipr(UID, ID);
	}

	private void assertViolations() {
		Set<ConstraintViolation<Ipr>> violations = validator.validate(this.ipr);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
