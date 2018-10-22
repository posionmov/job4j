package ru.job4j.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр для установки кодирования ответа клиенту
 * @author Galanov Sergey
 * @since 22.10.2018
 * @version 1.0
 */
public class ResponseEncodingFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * Основной метод фильтра
     * Устанавливает кодировку UTF-8 на все ответы, исходящие из сервера
     * @param servletRequest
     * @param servletResponse
     * @param filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletResponse.setCharacterEncoding("utf-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
