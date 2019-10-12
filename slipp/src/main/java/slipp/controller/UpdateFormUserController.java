package slipp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nextstep.mvc.tobe.JspView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import slipp.domain.User;
import slipp.support.db.DataBase;

@Controller
public class UpdateFormUserController {
    private static final Logger log = LoggerFactory.getLogger(UpdateFormUserController.class);

    @RequestMapping(value = "/users/updateForm", method = RequestMethod.GET)
    public ModelAndView getUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        log.debug("User : {}", userId);

        req.setAttribute("user", user);
        return new ModelAndView(new JspView("/user/updateForm.jsp"))
                .addObject("user", user);
    }
}
