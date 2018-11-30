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

public class FileExportHistoryTest {

	private static final Long ID = 123345678L;
	private static final LocalDate EXPORT_DATE = LocalDate.now();
	private static final String FILE_NAME = "FILENAME";
	private static final Integer RECORDS_NUMBER = 2;
	private static final String TRANSACTION_ID = "00032419";

	@Autowired
	private Validator validator;

	private FileExportHistory fileExportHistory = null;

	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.fileExportHistory = createFileExportHistory();
	}

	@After
	public void tearDown() throws Exception {
		this.fileExportHistory = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.fileExportHistory.equals(createFileExportHistoryViaConstructor()));
		assertTrue(this.fileExportHistory.hashCode() == createFileExportHistoryViaConstructor().hashCode());
		Map<Long, FileExportHistory> map = new HashMap<>();
		map.put(this.fileExportHistory.getId(), this.fileExportHistory);
		FileExportHistory e = map.get(this.fileExportHistory.getId());
		assertNotNull(e);
		e = new FileExportHistory();
		assertNotNull(e);
	}

	@Test
	public void testFileExportHistoryId() {
		assertEquals(ID, this.fileExportHistory.getId());
	}

	@Test
	public void testFileExportHistoryIdNullFail() {
		this.fileExportHistory.setId(null);
		assertViolations();
	}

	@Test
	public void testFileExportHistoryExportDate() {
		assertEquals(EXPORT_DATE, this.fileExportHistory.getExported());
	}

	@Test
	public void testFileExportHistoryExportDateInTheFutureFail() {
		this.fileExportHistory.setExported(EXPORT_DATE.plusDays(1));
		assertViolations();
	}

	@Test
	public void testFileExportHistoryFileName() {
		assertEquals(FILE_NAME, this.fileExportHistory.getFileName());
	}

	@Test
	public void testFileExportHistoryFileNameNullOrEmptyFail() {
		this.fileExportHistory.setFileName(null);
		assertViolations();
		this.fileExportHistory.setFileName("");
		assertViolations();
	}

	@Test
	public void testFileExportHistoryRecordsNumber() {
		assertEquals(RECORDS_NUMBER, this.fileExportHistory.getNumberOfRecords());
	}

	@Test
	public void testFileExportHistoryRecordsNumberNegagiveFail() {
		this.fileExportHistory.setNumberOfRecords(-1);
		assertViolations();
	}

	@Test
	public void testFileExportHistoryTransactionId() {
		assertEquals(TRANSACTION_ID, this.fileExportHistory.getTransactionId());
		this.fileExportHistory.setTransactionId(null);
		assertTrue(true);
		this.fileExportHistory.setTransactionId("");
		assertTrue(true);
	}

	@Test
	public void testFileExportHistoryEqualsOrHashcode() {
		final FileExportHistory r = FileExportHistory.builder().id(ID).build();
		assertEquals(this.fileExportHistory, r);
		assertEquals(this.fileExportHistory.hashCode(), r.hashCode());
	}

	public static FileExportHistory createFileExportHistory() {
		return FileExportHistory.builder()
				.id(ID)
				.exported(EXPORT_DATE)
				.fileName(FILE_NAME)
				.numberOfRecords(RECORDS_NUMBER)
				.transactionId(TRANSACTION_ID)
				.build();
	}

	private FileExportHistory createFileExportHistoryViaConstructor() {
		return new FileExportHistory(ID, EXPORT_DATE, FILE_NAME, RECORDS_NUMBER, TRANSACTION_ID);
	}

	private void assertViolations() {
		Set<ConstraintViolation<FileExportHistory>> violations = validator.validate(this.fileExportHistory);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
