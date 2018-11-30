package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.ActionType;
import javafx.util.Pair;
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

public class InfringementActionTest {

	private static final ActionType ACTION_TYPE = new ActionType("D", "Destruction under standard procedure");
	private static final LocalDate DECISION_DATE = LocalDate.now();
	private static final String DECISION_REASON = "Decision Reason";

	private static final String LONG_REASON = "First Item is 01.Checks site code and if unknown offers option to continue setting site to Unknown. File Created.First Item is 01.Checks site code and if unknown offers option to continue setting site to Unknown. File Created – Checks it is a valid date and if it is imports it otherwise leaves it blank. Length of version number is less than 51 and if it is imports it otherwise leaves it blank.";

	@Autowired
	private Validator validator;

	private InfringementAction infringementAction = null;

	@Before
	public void setUp() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
		this.infringementAction = createInfringementAction();
	}

	@After
	public void tearDown() throws Exception {
		this.infringementAction = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.infringementAction.equals(createInfringementActionViaConstructor()));
		assertTrue(this.infringementAction.hashCode() == createInfringementActionViaConstructor().hashCode());
		Map<String, InfringementAction> map = new HashMap<>();
		map.put(this.infringementAction.getActionType().toString() + this.infringementAction.getDecisionDate(), this.infringementAction);
		InfringementAction e = map.get(this.infringementAction.getActionType().toString() + this.infringementAction.getDecisionDate());
		e = new InfringementAction();
		assertNotNull(e);
	}

	@Test
	public void testInfringementActionType() {
		assertEquals(ACTION_TYPE, this.infringementAction.getActionType());
	}

	@Test
	public void testInfringementActionTypeNullOrEmptyFail() {
		this.infringementAction.setActionType(null);
		assertViolations();
	}

	@Test
	public void testInfringementActionDecisionDate() {
		assertEquals(DECISION_DATE, this.infringementAction.getDecisionDate());
	}

	@Test
	public void testInfringementActionDecisionDateInTheFutureFail() {
		this.infringementAction.setDecisionDate(DECISION_DATE.plusDays(1));
		assertViolations();
	}

	@Test
	public void testInfringementActionDecisionReason() {
		assertEquals(DECISION_REASON, this.infringementAction.getDecisionReason());
	}

	@Test
	public void testInfringementActionDecisionReasonTooLong() {
		this.infringementAction.setDecisionReason(LONG_REASON);
		assertViolations();
	}

	@Test
	public void testInfringementActionEqualsOrHashcode() {
		final InfringementAction r = InfringementAction.builder().actionType(ACTION_TYPE).decisionDate(DECISION_DATE).build();
		assertEquals(this.infringementAction, r);
		assertEquals(this.infringementAction.hashCode(), r.hashCode());
	}

	public static InfringementAction createInfringementAction() {
		return InfringementAction.builder()
				.actionType(ACTION_TYPE)
				.decisionDate(DECISION_DATE)
				.decisionReason(DECISION_REASON)
				.build();
	}

	private InfringementAction createInfringementActionViaConstructor() {
		return new InfringementAction(ACTION_TYPE, DECISION_DATE, DECISION_REASON);
	}

	private void assertViolations() {
		Set<ConstraintViolation<InfringementAction>> violations = validator.validate(this.infringementAction);
		assertThat(violations.size()).isGreaterThan(0);
	}
}
