package com.hmrc.bsp.copis.domain.reference;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SiteTest {
    private static final String KEY = "12345";
    private static final String VALUE = "54321";

    private Site site = null;

    @Before
    public void setUp() throws Exception {
        this.site = new Site(KEY, VALUE);
    }

    @After
    public void tearDown() throws Exception {
        this.site = null;
    }

    @Test
    public void testSiteConstructor() {
       final Site br = new Site("", "");
       assertNotNull(br);
       assertEquals("", br.getCode());
       assertEquals("", br.getDescription());
    }

    @Test
    public void testSiteConstructor2() {
        assertNotNull(this.site);
       assertEquals(KEY, this.site.getCode());
       assertEquals(VALUE, this.site.getDescription());
    }

    @Test
    public void testSiteEqualsOrHashcode() {
        final Site br = new Site("", "");
       assertNotEquals(this.site, br);
        assertNotEquals(this.site.hashCode(), br.hashCode());
    }
    
}
