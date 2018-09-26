package ru.job4j.users;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Класс-фильтр для проверки того, что пользователь авторизовался
 *
 * @author Galanov Sergey
 * @version 1.0
 * @since 26.09.2018
 */
public class AuthFilter implements Filter {

    /**
     * Метод, срабатывающий при инициализации данного класса
     *
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Метод, обеспечивающий фильтрацию
     * Если в адресе запроса присутствуют строки "/signIn" и "/create", то переходит к следующему запросу
     * Если же таких строк в запросе нет, то проверяет аттрибут сессии:
     * Если аттрибут текущей сессии с именем "login" отсутствует, то ответ содержит редирект на страницу авторизации
     * После этого переходит к следующему запросу
     *
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = ((HttpServletRequest) servletRequest).getSession();
        if (request.getRequestURI().contains("/signIn") || request.getRequestURI().contains("/create")) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            if (session.getAttribute("login") == null) {
                response.sendRedirect(String.format("%s/signIn", request.getContextPath()));
                return;
            }
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    /**
     * Метод, срабатывающий при уничтожении обьекта данного класса
     */
    @Override
    public void destroy() {

    }
}
