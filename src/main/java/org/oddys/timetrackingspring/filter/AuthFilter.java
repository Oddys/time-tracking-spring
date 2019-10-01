package org.oddys.timetrackingspring.filter;

import lombok.extern.slf4j.Slf4j;
import org.oddys.timetrackingspring.dto.UserDto;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Slf4j
public class AuthFilter implements Filter {
    private static final Set adminPages = Set.of(
            "/cabinet/user-data",
            "/cabinet/user-activity-requests",
            "/cabinet/change-user-activity-status");
    private static final Set userPages = Set.of();

    @Override
    public void init(FilterConfig filterConfig) {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        if (user == null) {
            resp.sendRedirect(req.getContextPath());
        } else if (adminPages.contains(req.getServletPath())
                && !"ADMIN".equals(user.getRoleName())) {
            resp.sendRedirect(req.getContextPath() + "/cabinet");
        } else if (userPages.contains(req.getServletPath())
                && !"USER".equals(user.getRoleName())) {
            resp.sendRedirect(req.getContextPath() + "/cabinet");
        }
        chain.doFilter(request, response);
    }
}
