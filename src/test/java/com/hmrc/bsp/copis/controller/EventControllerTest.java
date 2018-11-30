package com.hmrc.bsp.copis.controller;

import com.hmrc.bsp.copis.domain.Event;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@Slf4j
public class EventControllerTest {

	private Event event;
	private String baseUrl;
    private final LocalDateTime current = LocalDateTime.now();

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

	@Before
	public void setUp() throws Exception {
		this.event = createEvent();
		this.baseUrl = getBaseUrl(this.port);
		String url = baseUrl + "/create";
	    ResponseEntity<Event> response
	                = restTemplate.postForEntity(url, event, Event.class);
	    assertNotNull(response.getBody());
	    assertTrue(String.format("Invalid status code: %s returned.", response.getStatusCode()), response.getStatusCode().equals(HttpStatus.OK));
	}

	@After
	public void tearDown() throws Exception {
        try {
            this.restTemplate.delete(baseUrl + "/delete/" + this.event.getId());
        } catch (RestClientException e) {
            e.printStackTrace();
        }
    }
	

	@Test
    public void testFindAllEvents() throws Exception {
        String url = this.baseUrl + "/findAllEvents";
        log.info(String.format("Test url: %s", url));
        List list = getEventsFromRestCall(url);
        assertNotNull("Should return non-null list.", list);
        assertTrue(list.size() >= 1);
    }

    @Test
    public void testFindEventById() throws Exception {
	    String testMethod = "findEvent";
        String url = getTestUrl(this.baseUrl, testMethod, event.getId());
        log.info(String.format("Test url: %s", url));
        Event o = getEventFromRestCall(url);
        assertNotNull("Should return non-null Event.",o);
        assertEquals("event1", o.getId());

        testNonExistValue(testMethod, "event2");
        testNonExistValue(testMethod, null);
    }


    @Test
    public void testFindEventByCaseId() throws Exception {
        String testMethod = "findEventByCaseId";
        String url = getTestUrl(this.baseUrl, testMethod, event.getCaseId());
        log.info(String.format("Test url: %s", url));
        List list = getEventsFromRestCall(url);
        assertNotNull("Should return non-null Event.",list);
        assertTrue(list.size() > 0);

        testNonExistValueWithReturndList(testMethod, "caseId2");
        testNonExistValueWithReturndList(testMethod, "");
    }

    @Test
    public void testFindEventByType() throws Exception {
        String testMethod = "findEventByType";
        String url = getTestUrl(this.baseUrl, testMethod, event.getType().name());
        log.info(String.format("Test url: %s", url));
        List list = getEventsFromRestCall(url);
        assertNotNull("Should return non-null Event.",list);
        assertTrue(list.size() > 0);

        testNonExistValueWithReturndList(testMethod, Event.Type.OTHER.name());
        testNonExistValueWithReturndList(testMethod, null);
    }

    @Test
    public void testFindEventByTimestamp() throws Exception {
        String testMethod = "findEventByTimestamp";

//        HttpEntity<ZonedDateTime> entity = new HttpEntity<>(null, new HttpHeaders() );
//
//        ResponseEntity<List> response = restTemplate.exchange(
//                this.baseUrl + "/findEventByTimestamp/{isbn}",
//                HttpMethod.GET, entity, List.class, this.event.getTimestamp());
//        List<Event> list = response.getBody();
//        assertNotNull("Should return non-null Event.",list);
//        assertTrue(list.size() > 0);

        String url = getTestUrl(this.baseUrl, testMethod, this.event.getTimestamp().toString());
        log.info(String.format("Test url: %s", url));
        List list = getEventsFromRestCall(url);
     //   assertNotNull("Should return non-null Event.",list);
     //   assertTrue(list.size() > 0);

        testNonExistValueWithReturndList(testMethod, ZonedDateTime.now().plusDays(1).toString());
        testNonExistValueWithReturndList(testMethod, "");
    }

    @Test
    public void testFindEventByNullTimestampObject() throws Exception {
        try {
            this.restTemplate.getForEntity(this.baseUrl+"/findEventByTimestamp", Event.class, (Event)null);
            fail();
        } catch (RestClientException e) {
        }
    }

    @Test
    public void testFindEventByApplicationId() throws Exception {
        String testMethod = "findEventByApplicationId";
        String url = getTestUrl(this.baseUrl, testMethod, event.getApplicationId());
        log.info(String.format("Test url: %s", url));
        List list = getEventsFromRestCall(url);
        assertNotNull("Should return non-null Event.",list);
        assertTrue(list.size() > 0);

        testNonExistValueWithReturndList(testMethod, "applicationId2");
        testNonExistValueWithReturndList(testMethod, "");
    }

    @Test
    public void testUpdateEvent() throws Exception {
	    Event e = createEvent();
	    e.setApplicationId("applicationId2");
        ResponseEntity<Event> response
                = this.restTemplate.postForEntity(baseUrl + "/update", e, Event.class);
        Event r = response.getBody();
        assertNotNull("Shpuld return non-null Order.", r);
        assertEquals("applicationId2", r.getApplicationId());

        e.setId("event2");
        response = restTemplate.postForEntity(baseUrl + "/update", e, Event.class);
        r = response.getBody();
        assertNull("Shpuld return null Order.", r);
    }

    @Test(expected = RestClientException.class)
    public void testUpdateWithNullEventObject() throws Exception {
        this.restTemplate.postForEntity(baseUrl + "/update", null, Event.class);
    }

    @Test
    public void testDeleteNonExistEvent() throws Exception {
        Event e = createEvent("id");
        this.restTemplate.delete(baseUrl + "/delete/" + this.event.getId());
        // show warning message but does not throw exceptions
        assertTrue(true);
    }

    private void testNonExistValueWithReturndList(String method, String pathVar) {
        String url = createUrlWithNonExistingValue(method, pathVar);
        List list = getEventsFromRestCall(url);
        if(list != null) {
            assertTrue("Should return null/empty Event list.",list.isEmpty());
        }
    }

    private void testNonExistValue(String method, String pathVar) {
        String url = createUrlWithNonExistingValue(method, pathVar);
        Event o = getEventFromRestCall(url);
        assertNull(o);
    }

    private String createUrlWithNonExistingValue(String method, String pathVar) {
        return String.format("%s/%s/%s", this.baseUrl, method, pathVar);
    }

    private Event getEventFromRestCall(String url) {
        ResponseEntity<Event> response = null;
        try {
            response = restTemplate.getForEntity(url, Event.class);
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
        return response.getBody();
    }

    private List getEventsFromRestCall(String url, Object obj) {
        ResponseEntity<List> response = null;
        try {
           // response = this.restTemplate.getForEntity(url, List.class, obj);
            response = this.restTemplate.getForEntity(url, List.class, obj);
            log.info(response.toString());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
        return response.getBody();
    }

    private List getEventsFromRestCall(String url) {
        ResponseEntity<List> response = null;
        try {
            response = this.restTemplate.getForEntity(url, List.class);
            log.info(response.toString());
        } catch (RestClientException e) {
            log.error(e.getMessage());
            return null;
        }
        return response.getBody();
    }

    private Event createEvent(String id) {
      return Event.builder()
                .id("id")
                .caseId("caseId1")
                .type(Event.Type.ATT)
                .timestamp(this.current)
                .applicationId("applicationId1")
                .detail("event:detail")
                .build();
    }

    private Event createEvent() {
        return Event.builder()
                .id("event1")
                .caseId("caseId1")
                .type(Event.Type.ATT)
                .timestamp(this.current)
                .applicationId("applicationId1")
                .detail("event:detail")
                .build();
    }

	private static String getBaseUrl(int port) {
        return String.format("http://localhost:%d/event", port);
    }

    private static String getTestUrl(String baseUrl, String method, String pathVar) {
        return String.format("%s/%s/%s", baseUrl, method, pathVar);
    }

    private static String asDataString(ZonedDateTime timestamp) {
	    return DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(timestamp);
    }
}
