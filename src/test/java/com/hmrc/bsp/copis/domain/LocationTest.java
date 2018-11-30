package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.BaseReference;
import com.hmrc.bsp.copis.domain.reference.CountryCode;
import com.hmrc.bsp.copis.domain.reference.TypeOfPlace;
import javafx.util.Pair;
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

public class LocationTest {
    private static final UUID UID = UUID.randomUUID();
    private static final BaseReference COUNTRY = new CountryCode("UK", "United Kingdom");
    private static final String PLACE = "LONDON";
    private static final TypeOfPlace PLACE_TYPE = new TypeOfPlace("AP", "Southend on sea");
    private static final String LONG_DEPART_PLACE = "First Item is 01. Checks site code and if unknown offers option to continue setting site to Unknown. File Created.";

    @Autowired
    private Validator validator;

    private Location location = null;

    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.location = createLocation();
    }

    @After
    public void tearDown() throws Exception {
        this.location = null;
    }

    @Test
    public void testLocationConstructor() {
        final Location r = new Location();
        r.setCountry(COUNTRY);
        r.setPlace(PLACE);
        r.setPlaceType(PLACE_TYPE);
        assertEquals(this.location, r);
    }

    @Test
    public void testLocationCountry() {
        assertEquals(COUNTRY, this.location.getCountry());
        this.location.setCountry(null);
        assertTrue(true);
    }

    @Test
    public void testLocationPlace() {
        assertEquals(PLACE, this.location.getPlace());
        this.location.setPlace("");
        assertTrue(true);
        this.location.setPlace(null);
        assertTrue(true);
    }

    @Test
    public void testLocationPlaceTooLongFail() {
        this.location.setPlace(LONG_DEPART_PLACE);
        assertViolations();
    }

    @Test
    public void testLocationPlaceType() {
        assertEquals(PLACE_TYPE, this.location.getPlaceType());
        this.location.setPlaceType(null);
        assertTrue(true);
    }

    @Test
    public void testLocationEqualsOrHashcode() {
        assertEquals(this.location, createLocationViaConstructor());
        assertEquals(this.location.hashCode(), createLocationViaConstructor().hashCode());
    }

    private Location createLocationViaConstructor() {
        return new Location(COUNTRY, PLACE, PLACE_TYPE);
    }

    private Location createLocation() {
        return Location.builder()
                .country(COUNTRY)
                .place(PLACE)
                .placeType(PLACE_TYPE)
                .build();
    }

    private void assertViolations() {
        Set<ConstraintViolation<Location>> violations = validator.validate(this.location);
        assertThat(violations.size()).isGreaterThan(0);
    }
}
