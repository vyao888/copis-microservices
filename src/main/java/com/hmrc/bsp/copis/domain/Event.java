package com.hmrc.bsp.copis.domain;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Objects;

@Builder
@Data
@AllArgsConstructor
@Document(collection = "events")
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Event {

	public Event() {
	}

	@Id
	private String id;
	@NonNull
	private String caseId;
	@NonNull
	private Type type;
	@NonNull
	private String detail;
	@NonNull
	private LocalDateTime timestamp;
	@NonNull
	private String applicationId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Event event = (Event) o;
		return Objects.equals(id, event.id) &&
				Objects.equals(caseId, event.caseId) &&
				type == event.type &&
				Objects.equals(detail, event.detail) &&
				Objects.equals(timestamp, event.timestamp) &&
				Objects.equals(applicationId, event.applicationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, caseId, type, detail, timestamp, applicationId);
	}
	public static enum Type {
		ATT, CRT, OTHER;
	}
}
