package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.IPRType;
import javafx.util.Pair;
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

public class ProtectedRightsTest {
    private static final UUID UID = UUID.randomUUID();
    private static final IPRType IPR_TYPE = new IPRType("CTM", "Community Trademark");
    private static final String RIGHT_HOLDER = "LONDON";
    private static final LocalDate NOTIFICATION_DATE = LocalDate.now();
    private static final String LONG_RIGHT_HOLDER = "First Item is 01.Checks site code and if unknown offers option to continue setting site to Unknown. File Created.";

    @Autowired
    private Validator validator;

    private ProtectedRights protectedRights = null;


    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.protectedRights = createProtectedRights();
    }

    @After
    public void tearDown() throws Exception {
        this.protectedRights = null;
    }

    @Test
    public void testProtectedRightsConstructor() {
        final ProtectedRights r = new ProtectedRights();
       r.setUuid(UID);
       assertEquals(this.protectedRights, r);
    }

    @Test
    public void testProtectedRightsUuid() {
        assertEquals(UID, this.protectedRights.getUuid());
    }

    @Test
    public void testNullProtectedRightsUuidFail() {
        this.protectedRights.setUuid(null);
        assertViolations();
    }

    @Test
    public void testProtectedRightsIprType() {
        assertEquals(IPR_TYPE, this.protectedRights.getIprType());
    }

    @Test
    public void testProtectedRightsRightHolder() {
        assertEquals(RIGHT_HOLDER, this.protectedRights.getRightHolder());
    }

    @Test
    public void testProtectedRightsRightHolderTooLongFail() {
        this.protectedRights.setRightHolder(LONG_RIGHT_HOLDER);
        assertViolations();
    }

    @Test
    public void testProtectedRightsNotificationDate() {
        assertEquals(NOTIFICATION_DATE, this.protectedRights.getExOfficialNotificationDate());
    }

    @Test
    public void testProtectedRightsNotificationDateInTheFutureFail() {
        this.protectedRights.setExOfficialNotificationDate(NOTIFICATION_DATE.plusDays(1));
        assertViolations();
    }

    @Test
    public void testProtectedRightsEqualsOrHashcode() {
       final ProtectedRights r = ProtectedRights.builder().uuid(UID).build();
       assertEquals(this.protectedRights, r);
       assertEquals(this.protectedRights.hashCode(), r.hashCode());
    }

    public static ProtectedRights createProtectedRights() {
        return ProtectedRights.builder()
                .uuid(UID)
                .iprType(IPR_TYPE)
                .rightHolder(RIGHT_HOLDER)
                .exOfficialNotificationDate(NOTIFICATION_DATE)
                .build();
    }

    private ProtectedRights createProtectedRightsViaConstructor() {
        return new ProtectedRights(UID, IPR_TYPE, RIGHT_HOLDER, NOTIFICATION_DATE);
    }

    private void assertViolations() {
        Set<ConstraintViolation<ProtectedRights>> violations = validator.validate(this.protectedRights);
        assertThat(violations.size()).isGreaterThan(0);
    }
}
