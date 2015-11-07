package com.stylehood.webservice.demo.jaxrs;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityFilter implements Filter {

    @Override
    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) sr;
        HttpServletResponse res = (HttpServletResponse) sr1;

        String dummyToken = "abcdefghijk";
        String requestUri = req.getRequestURI();

        ObjectMapper om = new ObjectMapper();
        JsonResponse<Map<String, String>> jsonResponse = new JsonResponse<>();
        Map<String, String> dataMap = new HashMap<>();

        if (requestUri.endsWith("/login")) {
            dataMap.put("auth", dummyToken);
            jsonResponse.setData(dataMap);
            om.writeValue(res.getWriter(), jsonResponse);

        } else {
            String token = req.getHeader("auth");
            if (!dummyToken.equals(token)) {
                jsonResponse.setSuccess(false);
                jsonResponse.setMessage("incorrect token");
                om.writeValue(res.getWriter(), jsonResponse);
            } else {
                //do the decryption
                DecryptionRequestWrapper resWrapper = new DecryptionRequestWrapper(req);
                fc.doFilter(resWrapper, sr1);
            }
        }

    }

    @Override
    public void init(FilterConfig fc) throws ServletException {
    }

    @Override
    public void destroy() {
    }
}
