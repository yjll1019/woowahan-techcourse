package slipp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import slipp.domain.User;
import slipp.support.db.DataBase;

@Controller
public class UserController {
    @RequestMapping(value = "/users/form", method = RequestMethod.GET)
    public ModelAndView getJoinForm(HttpServletRequest req, HttpServletResponse resp) {
        return new ModelAndView(new JspView("/user/form.jsp"));
    }

    @RequestMapping(value = "/users/create", method = RequestMethod.POST)
    public ModelAndView createUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        DataBase.addUser(user);
        return new ModelAndView(new JspView("redirect:/"));
    }

    @RequestMapping(value = "/users/profile", method = RequestMethod.GET)
    public ModelAndView getProfile(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }

        return new ModelAndView(new JspView("/user/profile.jsp"))
                .addObject("user", user);
    }

    @RequestMapping(value = "/users/updateForm", method = RequestMethod.GET)
    public ModelAndView getUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        req.setAttribute("user", user);
        return new ModelAndView(new JspView("/user/updateForm.jsp"))
                .addObject("user", user);
    }

    @RequestMapping(value = "/users/update")
    public ModelAndView updateUser(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        User user = DataBase.findUserById(req.getParameter("userId"));
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        User updateUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        user.update(updateUser);

        return new ModelAndView(new JspView("redirect:/"));
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView findUsers(HttpServletRequest req, HttpServletResponse resp) {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ModelAndView(new JspView("redirect:/users/loginForm"));
        }

        req.setAttribute("users", DataBase.findAll());
        return new ModelAndView(new JspView("/user/list.jsp"));
    }
}
