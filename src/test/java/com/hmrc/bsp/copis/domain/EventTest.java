package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class EventTest {

	private Event event = null;
	private LocalDateTime current = LocalDateTime.now();

	@Before
	public void setUp() throws Exception {
		event = createEvent();
	}

	@After
	public void tearDown() throws Exception {
		this.event = null;
	}

	@Test
	public void testConstructor() {
		assertTrue(this.event.equals(createEventViaConstructor()));
		assertTrue(this.event.hashCode()==createEventViaConstructor().hashCode());
		Map<String, Event> map = new HashMap<>();
		map.put(this.event.getId(), this.event);
		Event e = map.get(this.event.getId());
		assertNotNull(e);
		e = new Event();
		assertNotNull(e);
	}

	@Test
	public void testEventId() {
		assertEquals("event1", this.event.getId());
		try {
			Event.builder().id("").build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEventCaseId() {
		assertEquals("caseId1", this.event.getCaseId());
		try {
			Event.builder().caseId(null).build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEventType() {
		assertEquals(Event.Type.ATT, this.event.getType());
		try {
			Event.builder().type(null).build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEvenDetail() {
		assertEquals("event:detail", this.event.getDetail());
		try {
			Event.builder().detail(null).build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEvenTimestamp() {
		assertEquals(current, this.event.getTimestamp());
		try {
			Event.builder().timestamp(null).build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEventApplicationId() {
		assertEquals("applicationId1", this.event.getApplicationId());
		try {
			Event.builder().applicationId(null).build();
			fail();
		} catch (Exception e) {
		}
	}

	@Test
	public void testEquals() {
		assertEquals(this.event, createEventViaConstructor());
	}

	private Event createEvent() {
		return Event.builder()
				.id("event1")
				.caseId("caseId1")
				.type(Event.Type.ATT)
				.timestamp(current)
				.applicationId("applicationId1")
				.detail("event:detail")
				.build();
	}

	private Event createEventViaConstructor() {
		return new Event("event1", "caseId1", Event.Type.ATT, "event:detail", current, "applicationId1");
	}
}
