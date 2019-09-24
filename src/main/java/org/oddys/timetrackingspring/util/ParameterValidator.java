package org.oddys.timetrackingspring.util;

import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;

@NoArgsConstructor
public class ParameterValidator {
    public boolean isValidAddActivityRecord(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("date"))) {
            request.setAttribute("messageKey", "param.empty.date");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("duration"))) {
            request.setAttribute("messageKey", "param.empty.duration");
            return false;
        }
        return true;
    }

    public boolean isValidAddUser(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("login"))) {
            request.setAttribute("errorMessage", "Please, enter your login");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            request.setAttribute("errorMessage", "Please, enter your password");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("firstName"))) {
            request.setAttribute("errorMessage", "Please, enter your first name");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("lastName"))) {
            request.setAttribute("errorMessage", "Please, enter your last name");
            return false;
        }
        return true;
    }

    public boolean isValidAddActivity(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("activityName"))) {
            request.setAttribute("messageKey", "param.activity.name");
            return false;
        }
        return true;
    }

    public boolean isValidSignIn(HttpServletRequest request) {
        if (StringUtils.isBlank(request.getParameter("login"))) {
            request.getSession().setAttribute("messageKey", "auth.error.nologin");
            return false;
        }
        if (StringUtils.isBlank(request.getParameter("password"))) {
            request.getSession().setAttribute("messageKey", "auth.error.nopassword");
            return false;
        }
        return true;
    }
}
