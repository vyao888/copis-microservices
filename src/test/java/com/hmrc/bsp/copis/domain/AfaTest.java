package com.hmrc.bsp.copis.domain;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class AfaTest {
    private static final UUID UID = UUID.randomUUID();
    private static final String ID = "12345";
    private static final String PAPER_AFA = "Paper AFA";
    private static final LocalDate NOTIFICATION_DATE = LocalDate.now();
    private static final String LONG_ID = "This is a long AFA id which is not valid.";

    @Autowired
    private Validator validator;

    private Afa afa = null;

    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.afa = createAfa();
    }

    @After
    public void tearDown() throws Exception {
        this.afa = null;
    }

    @Test
    public void testAfaConstructor() {
       final Afa r = new Afa();
       r.setUuid(UID);
       assertEquals(this.afa, r);
    }

    @Test
    public void testAfaUuid() {
        assertEquals(UID, this.afa.getUuid());
    }

    @Test
    public void testNullAfaUuidFail() {
        this.afa.setUuid(null);
        assertViolations();
    }

    @Test
    public void testAfaId() {
        assertEquals(ID, this.afa.getId());
    }

    @Test
    public void testAfaIdTooLongFail() {
        this.afa.setId(LONG_ID);
        assertViolations();
    }


    @Test
    public void testPaperAfa() {
        assertEquals(PAPER_AFA, this.afa.getPaperAfa());
    }


    @Test
    public void testAfaNotificationDate() {
        assertEquals(NOTIFICATION_DATE, this.afa.getContactPersonNotificationDate());
    }

    @Test
    public void testAfaNotificationDateInTheFutureFail() {
        this.afa.setContactPersonNotificationDate(NOTIFICATION_DATE.plusDays(1));
        assertViolations();
    }

    @Test
    public void testAfaEqualsOrHashcode() {
       final Afa r = Afa.builder().uuid(UID).build();
       assertEquals(this.afa, r);
       assertEquals(this.afa.hashCode(), r.hashCode());
    }

    private Afa createAfa() {
        return Afa.builder()
                .uuid(UID)
                .id(ID)
                .paperAfa(PAPER_AFA)
                .contactPersonNotificationDate(NOTIFICATION_DATE)
                .build();
    }

    private Afa createAfaViaConstructor() {
        return new Afa(UID, ID, PAPER_AFA, NOTIFICATION_DATE);
    }

    private void assertViolations() {
        Set<ConstraintViolation<Afa>> violations = validator.validate(this.afa);
        assertThat(violations.size()).isGreaterThan(0);
    }
}
