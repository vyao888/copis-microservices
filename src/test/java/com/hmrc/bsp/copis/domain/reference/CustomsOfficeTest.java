package com.hmrc.bsp.copis.domain.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomsOfficeTest {
    private static final String KEY = "12345";
    private static final String VALUE = "54321";

    private CustomsOffice customsOffice = null;

    @Before
    public void setUp() throws Exception {
        this.customsOffice = new CustomsOffice(KEY, VALUE);
    }

    @After
    public void tearDown() throws Exception {
        this.customsOffice = null;
    }

    @Test
    public void testCustomsOfficeConstructor() {
       final CustomsOffice br = new CustomsOffice("", "");
       assertNotNull(br);
       assertEquals("", br.getCode());
       assertEquals("", br.getDescription());
    }

    @Test
    public void testCustomsOfficeConstructor2() {
        assertNotNull(this.customsOffice);
       assertEquals(KEY, this.customsOffice.getCode());
       assertEquals(VALUE, this.customsOffice.getDescription());
    }

    @Test
    public void testCustomsOfficeEqualsOrHashcode() {
        final CustomsOffice br = new CustomsOffice("", "");
       assertNotEquals(this.customsOffice, br);
        assertNotEquals(this.customsOffice.hashCode(), br.hashCode());
    }
    
}
