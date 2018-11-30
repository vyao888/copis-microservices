package com.hmrc.bsp.copis.domain;

import com.hmrc.bsp.copis.domain.reference.*;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

public class SuspectedGoodsTest {
    private static final UUID UID = UUID.randomUUID();
    private static final Integer ID = 12345678;
    private static final String DESCRIPTION = "UK";
    private static final CategoryOfGoods GOODS_CATEGORY = new CategoryOfGoods("5b", "Bags includes wallets, purses, cigarette cases and other similar goods carried in the pocket/bag");
    private static final BaseReference COUNTRY_ORIGIN = new EUCountryCode("FR", "France");
    private static final Intervention INTERVENTION = new Intervention("AA", "AFA Application");
    private static final Long QUANTITY = 9999999999L;
    private static final BigDecimal VALUE = new BigDecimal("9999999999.999");
    private static final Boolean PERISHABLE = true;
    private static final UnitOfMeasure MEASURE_UNIT = new UnitOfMeasure("PCE", "Percentage");
    private static final Boolean SMALL_CONSIGNMENT_PROCEDURE = false;
    private static final String LONG_DESCRIPTION = "First Item is 01.Checks site code and if unknown offers option to continue setting site to Unknown. File Created.First Item is 01.Checks site code and if unknown offers option to continue setting site to Unknown. File Created – Checks it is a valid date and if it is imports it otherwise leaves it blank. Length of version number is less than 51 and if it is imports it otherwise leaves it blank.";

    @Autowired
    private Validator validator;

    private SuspectedGoods suspectedGoods = null;

    @Before
    public void setUp() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
        this.suspectedGoods = createSuspectedGoods();
    }

    @After
    public void tearDown() throws Exception {
        this.suspectedGoods = null;
    }

    @Test
    public void testConstructor() {
        assertTrue(this.suspectedGoods.equals(createSuspectedGoodsViaConstructor()));
        assertTrue(this.suspectedGoods.hashCode() == createSuspectedGoodsViaConstructor().hashCode());
        Map<UUID, SuspectedGoods> map = new HashMap<>();
        map.put(this.suspectedGoods.getUuid(), this.suspectedGoods);
        SuspectedGoods e = map.get(this.suspectedGoods.getUuid());
        assertNotNull(e);
        e = new SuspectedGoods();
        assertNotNull(e);
    }

    @Test
    public void testSuspectedGoodsConstructor() {
        final SuspectedGoods r = new SuspectedGoods();
       r.setUuid(UID);
       assertEquals(this.suspectedGoods, r);
    }

    @Test
    public void testSuspectedGoodsUuid() {
        assertEquals(UID, this.suspectedGoods.getUuid());
    }

    @Test
    public void testNullSuspectedGoodsUuidFail() {
        this.suspectedGoods.setUuid(null);
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsId() {
        assertEquals(ID, this.suspectedGoods.getId());
    }

    @Test
    public void testNullSuspectedGoodsIdFail() {
        this.suspectedGoods.setId(null);
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsDescription() {
        assertEquals(DESCRIPTION, this.suspectedGoods.getDescription());
    }

    @Test
    public void testSuspectedGoodsLongDescriptionFail() {
        this.suspectedGoods.setDescription(LONG_DESCRIPTION);
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsIntervention() {
        assertEquals(INTERVENTION, this.suspectedGoods.getIntervention());
    }

    @Test
    public void testTooLongSuspectedGoodsCategory() {
        assertEquals(GOODS_CATEGORY, this.suspectedGoods.getGoodsCategory());
    }

    @Test
    public void testSuspectedGoodsOriginCountry() {
        assertEquals(COUNTRY_ORIGIN, this.suspectedGoods.getOriginCountry());
    }

    @Test
    public void testSuspectedGoodsQuantity() {
        assertEquals(QUANTITY, this.suspectedGoods.getQuantity());
    }

    @Test
    public void testSuspectedGoodsZeroOrNegativeQuantityFail() {
        this.suspectedGoods.setQuantity(0l);
        assertViolations();
        this.suspectedGoods.setQuantity(-1l);
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsTooBigQuantityFail() {
        this.suspectedGoods.setQuantity(QUANTITY.longValue() + 1);
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsValue() {
        assertEquals(VALUE, this.suspectedGoods.getValue());
    }

    @Test
    public void testSuspectedGoodsZeroOrNegativeValueFail() {
        this.suspectedGoods.setValue(BigDecimal.ZERO);
        assertViolations();
        this.suspectedGoods.setValue(new BigDecimal("-1.0"));
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsTooBigValueFail() {
        this.suspectedGoods.setValue((new BigDecimal(VALUE.doubleValue() + 1.0)));
        assertViolations();
    }

    @Test
    public void testSuspectedGoodsPerishable() {
        assertEquals(PERISHABLE, this.suspectedGoods.getPerishable());
    }

    @Test
    public void testSuspectedGoodsMeasureUnit() {
        assertEquals(MEASURE_UNIT, this.suspectedGoods.getMeasureUnit());
    }

    @Test
    public void testSuspectedGoodsSmallConsignmentProcedure() {
        assertEquals(SMALL_CONSIGNMENT_PROCEDURE, this.suspectedGoods.getSmallConsignmentProcedure());
    }

    @Test
    public void testSuspectedGoodsEqualsOrHashcode() {
       final SuspectedGoods r = SuspectedGoods.builder().uuid(UID).build();
       assertEquals(this.suspectedGoods, r);
       assertEquals(this.suspectedGoods.hashCode(), r.hashCode());
    }

    public static SuspectedGoods createSuspectedGoods() {
        return SuspectedGoods.builder()
                .uuid(UID)
                .id(ID)
                .description(DESCRIPTION)
                .goodsCategory(GOODS_CATEGORY)
                .originCountry(COUNTRY_ORIGIN)
                .intervention(INTERVENTION)
                .quantity(QUANTITY)
                .value(VALUE)
                .perishable(PERISHABLE)
                .measureUnit(MEASURE_UNIT)
                .smallConsignmentProcedure(SMALL_CONSIGNMENT_PROCEDURE)
                .build();
    }

    private SuspectedGoods createSuspectedGoodsViaConstructor() {
        return new SuspectedGoods(UID, ID, DESCRIPTION, GOODS_CATEGORY, COUNTRY_ORIGIN, INTERVENTION,
                QUANTITY, VALUE, PERISHABLE, MEASURE_UNIT, SMALL_CONSIGNMENT_PROCEDURE);
    }

    private void assertViolations() {
        Set<ConstraintViolation<SuspectedGoods>> violations = validator.validate(this.suspectedGoods);
        assertThat(violations.size()).isGreaterThan(0);
    }
}
