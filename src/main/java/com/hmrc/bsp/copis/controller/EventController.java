package com.hmrc.bsp.copis.controller;


import com.hmrc.bsp.copis.domain.Event;
import com.hmrc.bsp.copis.exception.EventServiceException;
import com.hmrc.bsp.copis.repository.EventRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Comparator.comparing;
import static org.apache.commons.lang3.StringUtils.isBlank;

@RestController
@RequestMapping("/event")
@Slf4j
public class EventController {
	@Autowired
	private EventRepository repository;
	
	@RequestMapping("/findAllEvents")
	public List<Event> findAllEvents() {
		return repository.findAll();
	}
	
	@RequestMapping("/findEvent/{id}")
	public Event findEvent(@PathVariable String id) {
		validate("Event Id", id);
		return this.findAllEvents().stream()
				.filter(e -> e.getId().equalsIgnoreCase(id.trim()))
				.findAny().orElse(null);
	}
	
	@RequestMapping(value = "/findEventByCaseId/{caseId}", method = RequestMethod.GET)
	public List<Event> findEventByCaseId(@PathVariable String caseId) {
		validate("Event Case Id", caseId);
		return this.findAllEvents().stream()
				.filter(e -> e.getCaseId().equalsIgnoreCase(caseId.trim()))
                .sorted(comparing(Event::getTimestamp))
				.collect(Collectors.toList());
	}
	
	@RequestMapping(value = "/findEventByType/{type}", method = RequestMethod.GET)
	public List<Event> findEventByType(@PathVariable String type) {
		validate("Event Type", type);
		Event.Type t = Event.Type.valueOf(type.trim());
		return this.findAllEvents().stream()
				.filter(e -> e.getType() == t)
                .sorted(comparing(Event::getTimestamp))
				.collect(Collectors.toList());
	}
	
//	@RequestMapping(value = "/findEventByTimestamp/{timestamp}", method = RequestMethod.GET)
//	public List<Event> findEventByTimestamp(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
//		log.info(String.format("findEventByTimestamp: %s", timestamp.toString()));
//		if(timestamp == null) {
//			throw new IllegalArgumentException("Timestamp must be set.");
//		}
//		return this.findAllEvents().stream()
//				.filter(e -> e.getTimestamp().toLocalDateTime().equals(timestamp))
//                .sorted(comparing(Event::getTimestamp))
//				.collect(Collectors.toList());
//	}

	@GetMapping(value = "/findEventByTimestamp/{timestamp}")
	//@GetMapping(value = {"/findEventByTimestamp/{timestamp:\\d{4}\\-\\d{2}\\-\\d{2}T\\d{2}:\\d{2}\\+\\d{2}:\\d{2}\\[\\w*}/{zone:\\w*\\]}"})
	public List<Event> findEventByTimestamp(@PathVariable  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime timestamp) {
		log.info(String.format("findEventByTimestamp: %s", timestamp.toString()));
		if(timestamp == null) {
			throw new IllegalArgumentException("Timestamp must be set.");
		}
		return this.findAllEvents().stream()
				.filter(e -> e.getTimestamp().equals(timestamp))
				.sorted(comparing(Event::getTimestamp))
				.collect(Collectors.toList());
	}
	
	
	@RequestMapping(value = "/findEventByApplicationId/{applicationId}", method = RequestMethod.GET)
	public List<Event> findEventByApplicationId(@PathVariable String applicationId) {
		validate("Application Id", applicationId);
		return this.findAllEvents().stream()
				.filter(e -> e.getApplicationId().equalsIgnoreCase(applicationId.trim()))
                .sorted(comparing(Event::getTimestamp))
				.collect(Collectors.toList());
	}

	@PostMapping(value = "/create")
	public Event create(@RequestBody Event event) {
		Event e = this.findEvent(event.getId());
		if(e != null) {
			log.warn(String.format("Event with the id: %s already exists.", event.getId()));
		} else {
			e = this.repository.save(event);
			log.info(String.format("new Event: %s has been created successfully.", event.toString()));
		}
		return e;
	}
	
	@PostMapping(value = "/update")
	public Event update(@RequestBody Event event) {
		Event e = null;
		if(this.findEvent(event.getId()) == null) {
			log.warn(String.format("Event with the id: %s does not exist.", event.getId()));
		} else {
			e = this.repository.save(event);
			log.info(String.format("The Event: %s has been updated successfully.", event.toString()));
		}
		log.info(String.format("Event has been updated: %s.", e));
		return e;
	}
	
	@DeleteMapping(value = "/delete/{id}")
	public void delete(@PathVariable String id) {
		Event e = this.findEvent(id);
		if(e == null) {
			log.warn(String.format("Event with the id: %s does not exist.", id));
		} else {
			this.repository.delete(e);
			log.info(String.format("The Event with the id %s has been deleted successfully.", id));
		}
	}

	private static void validate(String field, String s) {
		if(isBlank(s)) {
			throw new EventServiceException(String.format("Invalid value: %s for field: %s.", s, field));
		}
	}
}
