package com.samanecorp.secureapp.controller;
import java.io.IOException;
import java.io.Serial;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.samanecorp.secureapp.dto.UserDTO;
import com.samanecorp.secureapp.service.LoginService;
@WebServlet(name = "singup", value = "/register")

public class SingUpServlet extends HttpServlet {
    @Serial
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);
    private LoginService loginService;

    @Override
    public void init(ServletConfig config) throws ServletException {

        loginService = new LoginService();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SingUpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        loadIndex(null, req, resp);
    }

    private void loadIndex(String message, HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("message", message);
        String LOGIN_PAGE = "jsp/singup.jsp";
        req.getRequestDispatcher(LOGIN_PAGE).forward(req, resp);
    }
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String fistName = req.getParameter("first_name");
        String lastName = req.getParameter("last_name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        logger.info("Tentative d'inscription avec {}", email);
        String message = null;
        try {

            UserDTO userDTO = new UserDTO();
            userDTO.setFirstName(fistName);
            userDTO.setLastName(lastName);
            userDTO.setEmail(email);
            userDTO.setPassword(password);
            boolean result = loginService.save(userDTO);
            if (result) {
                message = "informations ajoutées dans la base de données.";
                logger.info("{}", message);
            }
        } catch (Exception e) {
            message = "informations non ajoutées.";
            logger.error("{}", message);
        }
        loadIndex(message, req, resp);
    }
}
