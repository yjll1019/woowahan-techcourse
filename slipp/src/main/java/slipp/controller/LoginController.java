package slipp.controller;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.domain.User;
import slipp.support.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {
    @RequestMapping(value = "/users/login", method = RequestMethod.GET)
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) {
        ModelAndView modelAndView;
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        User user = DataBase.findUserById(userId);

        if (user != null && user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            return new ModelAndView(new JspView("redirect:/"));
        }

        modelAndView = new ModelAndView(new JspView("/user/login.jsp"));
        modelAndView.addObject("loginFailed", true);
        return modelAndView;
    }
}
