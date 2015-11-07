package com.stylehood.webservice.demo.jaxrs;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import org.apache.commons.codec.binary.Base64;

public class DecryptionRequestWrapper extends HttpServletRequestWrapper {

    private HttpServletRequest originalRequest;

    public DecryptionRequestWrapper(HttpServletRequest request) {
        super(request);
        originalRequest = request;
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        ServletInputStream is = originalRequest.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String str;
        StringBuilder sb = new StringBuilder();
        while ((str = br.readLine()) != null) {
            sb.append(str);
        }
        Base64 base64 = new Base64();
        byte[] dataBytes = base64.decode(sb.toString());

        final InputStream in = new ByteArrayInputStream(dataBytes);
        ServletInputStream newInputStream = new ServletInputStream() {
            @Override
            public int read() throws IOException {
                return in.read();
            }
        };
        System.out.println("decrypted req : " + new String(dataBytes, "UTF-8"));
        return newInputStream;
    }
}
