package ru.job4j.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Класс-фильтр.
 * Служит для проверки того, что пользователь зашел в систему. Если пользователь не зашел, то возращает его на страницу регистрации
 * @author Galanov Sergey
 * @since 17.10.2018
 * @version 1.0
 */
public class AuthFilter implements Filter {
    /**
     * Метод, отрабатывающий при инициализации фильтра
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Метод, обеспечивающий работу фильтра.
     * Пропускает запросы на адресы страниц регистрации и авторизации, а также их сервлеты при условии,
     *                                      что в запросе имеются строки, описанные в первом блоке if.
     * Если в адресе запроса е имеются разрешенные строки, то проверяется аттрибут сессии "login". Если
     *                                      он равен нулю, то кидает на страницу авторизации.
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getRequestURI().contains("index.html")
                            || req.getRequestURI().contains("register.html")
                            || req.getRequestURI().contains("create")
                            || req.getRequestURI().contains("login")) {
            chain.doFilter(request, response);
        } else {
            if (req.getSession().getAttribute("login") == null) {
                resp.sendRedirect(String.format("%s/index.html", req.getContextPath()));
            }
            chain.doFilter(request, response);
        }
    }

    /**
     * Метод, отрабатывающий при уничтожении фильтра
     */
    @Override
    public void destroy() {

    }
}
