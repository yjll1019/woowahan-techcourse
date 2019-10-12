package slipp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.support.db.DataBase;

@Controller
public class ListUserController {
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView findUsers(HttpServletRequest req, HttpServletResponse resp) {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ModelAndView(new JspView("redirect:/users/loginForm"));
        }

        req.setAttribute("users", DataBase.findAll());
        return new ModelAndView(new JspView("/user/list.jsp"));
    }
}
