package com.ms.junz.filter;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.SecureRandom;

/**
 * Created by jerryjzhang on 2016/6/9.
 */
public class CsrfTokenFilter implements Filter{
    private final static String CSRF_TOKEN_SESSION_KEY = "XSRF-TOKEN";
    private final static String CSRF_TOKEN_COOKIE_NAME = "XSRF-TOKEN";
    private final static char[] CHARSET = new char[] { 'A', 'B', 'C', 'D', 'E',
            'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '0', '1', '2', '3', '4',
            '5', '6', '7', '8', '9' };
    private final static SecureRandom sr = new SecureRandom();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        HttpSession session = req.getSession();
        if(session.getAttribute(CSRF_TOKEN_SESSION_KEY) == null){
            String token = generateRandomId(32);
            session.setAttribute(CSRF_TOKEN_SESSION_KEY, token);
            res.addCookie(new Cookie(CSRF_TOKEN_COOKIE_NAME, token));
        }
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
    }

    public static String generateRandomId(int len) {
        StringBuilder sb = new StringBuilder();

        for (int i = 1; i < len + 1; i++) {
            int index = sr.nextInt(CHARSET.length);
            char c = CHARSET[index];
            sb.append(c);

            if ((i % 4) == 0 && i != 0 && i < len) {
                sb.append('-');
            }
        }

        return sb.toString();
    }
}
