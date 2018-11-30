package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class InfoTest {
	private static final String ERROR = "Date of Decision is in the future - 28/12/2060";
	private static final FileExportHistory EXPORT_INFO = FileExportHistoryTest.createFileExportHistory();

	@Autowired
	private Validator validator;

	private Info info = null;

	
	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.info = createInfo();
	}

	@After
	public void tearDown() throws Exception {
		this.info = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.info.equals(createInfoViaConstructor()));
		assertTrue(this.info.hashCode() == createInfoViaConstructor().hashCode());
		Info e = new Info();
		assertNotNull(e);
	}

	@Test
	public void testInfoError() {
		assertEquals(ERROR, this.info.getError());
		this.info.setError("");
		assertTrue(true);
		this.info.setError(null);
		assertTrue(true);
	}

	@Test
	public void testHasError() {
		assertTrue(this.info.hasError());
		this.info.setError(null);
		assertFalse(this.info.hasError());
		this.info.setError("");
		assertFalse(this.info.hasError());
	}

	@Test
	public void testInfoExportInfo() {
		assertEquals(EXPORT_INFO, this.info.getExportInformation());
		this.info.setExportInformation(null);
		assertTrue(this.info.hasError());
	}

	@Test
	public void testInfoEqualsOrHashcode() {
		final Info r = createInfo();
		assertEquals(this.info, r);
		assertEquals(this.info.hashCode(), r.hashCode());
	}

	public static Info createInfo() {
		return Info.builder()
				.error(ERROR)
				.exportInformation(EXPORT_INFO)
				.build();
	}

	private Info createInfoViaConstructor() {
		return new Info(ERROR, EXPORT_INFO);
	}

	private void assertViolations() {
		Set<ConstraintViolation<Info>> violations = validator.validate(this.info);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
