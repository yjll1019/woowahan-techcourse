package slipp.controller.api;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import nextstep.mvc.tobe.JsonView;
import nextstep.mvc.tobe.ModelAndView;
import nextstep.utils.JsonUtils;
import nextstep.web.annotation.Controller;
import nextstep.web.annotation.RequestMapping;
import nextstep.web.annotation.RequestMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import slipp.controller.api.exception.NotFoundUserException;
import slipp.domain.User;
import slipp.support.db.DataBase;


@Controller
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private ObjectMapper objectMapper = new ObjectMapper();

    @RequestMapping(value = "/api/users", method = RequestMethod.POST)
    public ModelAndView createUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String requestBody = req.getReader()
                .lines()
                .collect(Collectors.joining());

        logger.debug("requestBody : {}", requestBody);

        User user = JsonUtils.toObject(requestBody, User.class);
        DataBase.addUser(user);

        resp.setHeader("Location", "/api/users?userId=" + user.getUserId());
        resp.setStatus(HttpServletResponse.SC_CREATED);

        return new ModelAndView(new JsonView());
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.GET)
    public ModelAndView findUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");

        User user = DataBase.findUserById(userId);

        resp.setStatus(HttpServletResponse.SC_OK);
        return new ModelAndView(new JsonView()).addObject("user", user);
    }

    @RequestMapping(value = "/api/users", method = RequestMethod.PUT)
    public ModelAndView updateUser(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String userId = req.getParameter("userId");

        User user = DataBase.findUserById(userId);
        if (Objects.isNull(user)) {
            throw new NotFoundUserException();
        }

        String requestBody = req.getReader()
                .lines()
                .collect(Collectors.joining());

        logger.debug("requestBody : {}", requestBody);

        user.update(objectMapper.readValue(requestBody, User.class));
        DataBase.addUser(user);

        resp.setStatus(HttpServletResponse.SC_OK);
        return new ModelAndView(new JsonView());
    }
}