package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RoutingTest {
    private static final UUID UID = UUID.randomUUID();
    private static final Location DEPART = createDepart();
    private static final Location ENTRY = createEntry();
    private static final Location TRANSHIPMENT = createTranshipment();
    private static final BaseReference DESTINATION_COUNTRY = new CountryCode("DE", "Germany");
    private static final TypeOfTransport TRANSPORT_MEANS = new TypeOfTransport("3", "Road Transport");
    private static final CustomsProcedure CUSTOMS_PROCEDURE  = new CustomsProcedure("IMP", "Import");
    private static final TrafficType TRAFFIC_TYPE_USED = new TrafficType("COM", "Commercial");

    @Autowired
    private Validator validator;

    private Routing routing = null;

    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.routing = createRouting();
    }

    @After
    public void tearDown() throws Exception {
        this.routing = null;
    }

    @Test
    public void testRoutingConstructor() {
        final Routing r = new Routing();
       r.setUuid(UID);
       assertEquals(this.routing, r);
    }

    @Test
    public void testRoutingUuid() {
        assertEquals(UID, this.routing.getUuid());
    }

    @Test
    public void testNullRoutingUuidFail() {
        this.routing.setUuid(null);
        assertViolations();
    }

    @Test
    public void testRoutingDepart() {
        assertEquals(DEPART, this.routing.getDeparture());
    }

    @Test
    public void testNullRoutingDepartFail() {
        this.routing.setDeparture(null);
        assertViolations();
    }

    @Test
    public void testRoutingTranshipment() {
        assertEquals(TRANSHIPMENT, this.routing.getTranshipment());
        this.routing.setTranshipment(null);
        assertTrue(true);
    }

    @Test
    public void testRoutingEntry() {
        assertEquals(ENTRY, this.routing.getEntry());
    }

    @Test
    public void testNullRoutingEntryFail() {
        this.routing.setEntry(null);
        assertViolations();
    }

    @Test
    public void testRoutingDestinationCountry() {
        assertEquals(DESTINATION_COUNTRY, this.routing.getDestinationCountry());
        this.routing.setDestinationCountry(null);
        assertTrue(true);
    }

    @Test
    public void testRoutingTransportMeans() {
        assertEquals(TRANSPORT_MEANS, this.routing.getTransportMeans());
        this.routing.setTransportMeans(null);
        assertTrue(true);
    }

    @Test
    public void testRoutingCustomersProcedure() {
        assertEquals(CUSTOMS_PROCEDURE, this.routing.getCustomersProcedure());
        this.routing.setCustomersProcedure(null);
        assertTrue(true);
    }

    @Test
    public void testRoutingTrafficTypeUsed() {
        assertEquals(TRAFFIC_TYPE_USED, this.routing.getTrafficTypeUsed());
        this.routing.setTrafficTypeUsed(null);
        assertTrue(true);
    }

    @Test
    public void testRoutingEqualsOrHashcode() {
       final Routing r = Routing.builder().uuid(UID).build();
       assertEquals(this.routing, r);
       assertEquals(this.routing.hashCode(), r.hashCode());
    }

    private Routing createRoutingViaConstructor() {
        return new Routing(UID, DEPART, TRANSHIPMENT, ENTRY, DESTINATION_COUNTRY,  TRANSPORT_MEANS, CUSTOMS_PROCEDURE, TRAFFIC_TYPE_USED);
    }

    public static Routing createRouting() {
        return Routing.builder()
                .uuid(UID)
                .departure(DEPART)
                .entry(ENTRY)
                .transhipment(TRANSHIPMENT)
                .destinationCountry(DESTINATION_COUNTRY)
                .transportMeans(TRANSPORT_MEANS)
                .customersProcedure(CUSTOMS_PROCEDURE)
                .trafficTypeUsed(TRAFFIC_TYPE_USED)
                .build();
    }

    private void assertViolations() {
        Set<ConstraintViolation<Routing>> violations = validator.validate(this.routing);
        assertThat(violations.size()).isGreaterThan(0);
    }

    private static Location createDepart() {
        final BaseReference COUNTRY = new CountryCode("UK", "United Kingdom");
        final String PLACE = "LONDON";
        final TypeOfPlace PLACE_TYPE = new TypeOfPlace("AP", "Southend on sea");
        return new Location(COUNTRY, PLACE, PLACE_TYPE);
    }

    private static Location createEntry() {
        final BaseReference COUNTRY = new CountryCode("FR", "France");
        final String PLACE = "Paris";
        final TypeOfPlace PLACE_TYPE = new TypeOfPlace("AP", "Charles de Gaulle");
        return new Location(COUNTRY, PLACE, PLACE_TYPE);
    }

    private static Location createTranshipment() {
        return new Location();
    }
}
