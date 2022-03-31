package com.online.merchant.model.filter;

import org.junit.Test;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class ReadTransactionLoggingFilterTest {

    @Test
    public void testReadTransactionLoggingFilter() throws ServletException, IOException {
        final MockHttpServletResponse response = buildMockHttpServletResponse();
        new ReadTransactionLoggingFilter().doFilter(new MockHttpServletRequest("GET", "/transaction/v1/read"),
                response, new MockFilterChain());
        assertEquals("application/json", response.getContentType());
        assertEquals(1648736522000L, response.getDateHeader("date"));
        assertEquals(200, response.getStatus());
    }

    private MockHttpServletResponse buildMockHttpServletResponse() {
        final MockHttpServletResponse mockHttpServletResponse = new MockHttpServletResponse();
        mockHttpServletResponse.setContentType("application/json");
        mockHttpServletResponse.setDateHeader("date", 1648736522483L);
        return mockHttpServletResponse;
    }
}
