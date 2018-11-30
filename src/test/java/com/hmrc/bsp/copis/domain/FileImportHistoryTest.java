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

public class FileImportHistoryTest {

	private static final Long ID = 123345678L;
	private static final LocalDate IMPORT_DATE = LocalDate.now();
	private static final String SITE = "London";
	private static final LocalDate CREATE_DATE = LocalDate.now();
	private static final Integer RECORDS_NUMBER = 2;
	private static final String VERSION = "V.1.0";

	@Autowired
	private Validator validator;

	private FileImportHistory fileImportHistory = null;

	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.fileImportHistory = createFileImportHistory();
	}

	@After
	public void tearDown() throws Exception {
		this.fileImportHistory = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.fileImportHistory.equals(createFileImportHistoryViaConstructor()));
		assertTrue(this.fileImportHistory.hashCode() == createFileImportHistoryViaConstructor().hashCode());
		Map<Long, FileImportHistory> map = new HashMap<>();
		map.put(this.fileImportHistory.getId(), this.fileImportHistory);
		FileImportHistory e = map.get(this.fileImportHistory.getId());
		assertNotNull(e);
		e = new FileImportHistory();
		assertNotNull(e);
	}

	@Test
	public void testFileImportHistoryId() {
		assertEquals(ID, this.fileImportHistory.getId());
	}

	@Test
	public void testFileImportHistoryIdNullFail() {
		this.fileImportHistory.setId(null);
		assertViolations();
	}

	@Test
	public void testFileImportHistoryExportDate() {
		assertEquals(IMPORT_DATE, this.fileImportHistory.getImported());
	}

	@Test
	public void testFileImportHistoryExportDateInTheFutureFail() {
		this.fileImportHistory.setImported(IMPORT_DATE.plusDays(1));
		assertViolations();
	}

	@Test
	public void testFileImportHistorySite() {
		assertEquals(SITE, this.fileImportHistory.getSite());
	}

	@Test
	public void testFileImportHistorySiteNullOrEmptyFail() {
		this.fileImportHistory.setSite(null);
		assertViolations();
		this.fileImportHistory.setSite("");
		assertViolations();
	}

	@Test
	public void testFileImportHistoryCreateDate() {
		assertEquals(CREATE_DATE, this.fileImportHistory.getCreated());
	}

	@Test
	public void testFileImportHistoryCreateDateInTheFutureFail() {
		this.fileImportHistory.setImported(CREATE_DATE.plusDays(1));
		assertViolations();
	}

	@Test
	public void testFileImportHistoryRecordsNumber() {
		assertEquals(RECORDS_NUMBER, this.fileImportHistory.getNumberOfRecords());
	}

	@Test
	public void testFileImportHistoryRecordsNumberNegagiveFail() {
		this.fileImportHistory.setNumberOfRecords(-1);
		assertViolations();
	}

	@Test
	public void testFileImportHistoryVersion() {
		assertEquals(VERSION, this.fileImportHistory.getVersion());
	}

	@Test
	public void testFileImportHistoryVersionNullOrEmptyFail() {
		this.fileImportHistory.setVersion(null);
		assertViolations();
		this.fileImportHistory.setVersion("");
		assertViolations();
	}

	@Test
	public void testFileImportHistoryEqualsOrHashcode() {
		final FileImportHistory r = FileImportHistory.builder().id(ID).build();
		assertEquals(this.fileImportHistory, r);
		assertEquals(this.fileImportHistory.hashCode(), r.hashCode());
	}

	private FileImportHistory createFileImportHistory() {
		return FileImportHistory.builder()
				.id(ID)
				.imported(IMPORT_DATE)
				.site(SITE)
				.created(CREATE_DATE)
				.numberOfRecords(RECORDS_NUMBER)
				.version(VERSION)
				.build();
	}

	private FileImportHistory createFileImportHistoryViaConstructor() {
		return new FileImportHistory(ID, IMPORT_DATE, SITE, CREATE_DATE, RECORDS_NUMBER, VERSION);
	}

	private void assertViolations() {
		Set<ConstraintViolation<FileImportHistory>> violations = validator.validate(this.fileImportHistory);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
