package com.hmrc.bsp.copis.domain.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BaseReferenceTest {
    private static final String KEY = "12345";
    private static final String VALUE = "54321";

    private BaseReference baseReference = null;

    @Before
    public void setUp() throws Exception {
        this.baseReference = new BaseReference(KEY, VALUE);
    }

    @After
    public void tearDown() throws Exception {
        this.baseReference = null;
    }

    @Test
    public void testBaseReferenceConstructor() {
       final BaseReference br = new BaseReference("", "");
       assertNotNull(br);
       assertEquals("", br.getCode());
       assertEquals("", br.getDescription());
    }

    @Test
    public void testBaseReferenceConstructor2() {
        assertNotNull(this.baseReference);
       assertEquals(KEY, this.baseReference.getCode());
       assertEquals(VALUE, this.baseReference.getDescription());
    }

    @Test
    public void testBaseReferenceEqualsOrHashcode() {
        final BaseReference br = new BaseReference("", "");
       assertNotEquals(this.baseReference, br);
        assertNotEquals(this.baseReference.hashCode(), br.hashCode());
    }

}
