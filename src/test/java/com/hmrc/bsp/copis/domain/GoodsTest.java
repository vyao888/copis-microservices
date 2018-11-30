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

public class GoodsTest {

	private static final SuspectedGoods SUSPECTED_GOODS = SuspectedGoodsTest.createSuspectedGoods();
	private static final InfringementAction INFRINGEMENT_ACTION = InfringementActionTest.createInfringementAction();
	private static final ProtectedRights PROTECTED_RIGHTS = ProtectedRightsTest.createProtectedRights();

	@Autowired
	private Validator validator;

	private Goods goods = null;

	
	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.goods = createGoods();
	}

	@After
	public void tearDown() throws Exception {
		this.goods = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.goods.equals(createGoodsViaConstructor()));
		assertTrue(this.goods.hashCode() == createGoodsViaConstructor().hashCode());
		Goods e = new Goods();
		assertNotNull(e);
	}

	@Test
	public void testGoodsSuspectedGoods() {
		assertEquals(SUSPECTED_GOODS, this.goods.getSuspectedGoods());
	}

	@Test
	public void testGoodsNullSuspectedGoodsFail() {
		this.goods.setSuspectedGoods(null);
		assertViolations();
	}

	@Test
	public void testGoodsInfringementAction() {
		assertEquals(INFRINGEMENT_ACTION, this.goods.getInfringementAction());
		this.goods.setInfringementAction(null);
		assertTrue(true);
	}

	@Test
	public void testGoodsProtectedRights() {
		assertEquals(PROTECTED_RIGHTS, this.goods.getProtectedRights());
		this.goods.setProtectedRights(null);
		assertTrue(true);
	}

	@Test
	public void testGoodsEqualsOrHashcode() {
		final Goods r = createGoods();
		assertEquals(this.goods, r);
		assertEquals(this.goods.hashCode(), r.hashCode());
	}

	public static Goods createGoods() {
		return Goods.builder()
				.suspectedGoods(SUSPECTED_GOODS)
				.infringementAction(INFRINGEMENT_ACTION)
				.protectedRights(PROTECTED_RIGHTS)
				.build();
	}

	private Goods createGoodsViaConstructor() {
		return new Goods(SUSPECTED_GOODS, INFRINGEMENT_ACTION, PROTECTED_RIGHTS);
	}

	private void assertViolations() {
		Set<ConstraintViolation<Goods>> violations = validator.validate(this.goods);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
