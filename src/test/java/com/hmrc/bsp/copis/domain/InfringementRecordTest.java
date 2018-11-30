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

public class InfringementRecordTest {

	private static final Goods GOODS = GoodsTest.createGoods();
	private static final Infringement INFRINGEMENT = InfringementTest.createInfringement();
	private static final Routing ROUTING = RoutingTest.createRouting();
	private static final Info INFO = InfoTest.createInfo();

	@Autowired
	private Validator validator;

	private InfringementRecord infringementRecord = null;

	
	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.infringementRecord = createInfringementRecord();
	}

	@After
	public void tearDown() throws Exception {
		this.infringementRecord = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.infringementRecord.equals(createGoodsViaConstructor()));
		assertTrue(this.infringementRecord.hashCode() == createGoodsViaConstructor().hashCode());
		InfringementRecord e = new InfringementRecord();
		assertNotNull(e);
	}

	@Test
	public void testInfringementRecordInfringement() {
		assertEquals(INFRINGEMENT, this.infringementRecord.getInfringement());
	}

	@Test
	public void testInfringementRecordNullInfringementFail() {
		this.infringementRecord.setInfringement(null);
		assertViolations();
	}

	@Test
	public void testInfringementRecordGoods() {
		assertEquals(GOODS, this.infringementRecord.getGoods());
	}

	@Test
	public void testInfringementRecordNullGoodsFail() {
		this.infringementRecord.setGoods(null);
		assertViolations();
	}

	@Test
	public void testInfringementRecordRouting() {
		assertEquals(ROUTING, this.infringementRecord.getRouting());
	}

	@Test
	public void testInfringementRecordNullRoutingFail() {
		this.infringementRecord.setRouting(null);
		assertViolations();
	}

	@Test
	public void testInfringementRecordInfo() {
		assertEquals(INFO, this.infringementRecord.getInfo());
	}

	@Test
	public void testInfringementRecordNullInfoFail() {
		this.infringementRecord.setInfo(null);
		assertViolations();
	}


	@Test
	public void testGoodsEqualsOrHashcode() {
		final InfringementRecord r = createGoodsViaConstructor();
		assertEquals(this.infringementRecord, r);
		assertEquals(this.infringementRecord.hashCode(), r.hashCode());
	}

	private InfringementRecord createInfringementRecord() {
		return InfringementRecord.builder()
				.infringement(INFRINGEMENT)
				.routing(ROUTING)
				.goods(GOODS)
				.info(INFO)
				.build();
	}

	private InfringementRecord createGoodsViaConstructor() {
		return new InfringementRecord(INFRINGEMENT, ROUTING, GOODS, INFO);
	}

	private void assertViolations() {
		Set<ConstraintViolation<InfringementRecord>> violations = validator.validate(this.infringementRecord);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
