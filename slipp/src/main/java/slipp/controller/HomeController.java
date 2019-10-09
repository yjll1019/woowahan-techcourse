package slipp.controller;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.support.db.DataBase;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView(new JspView("home.jsp"));
        modelAndView.addObject("users", DataBase.findAll());
        return modelAndView;
    }
}
