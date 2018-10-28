package ru.job4j.filters;

import javax.servlet.*;
import java.io.IOException;

/**
 * Фильтр для установки кодировки
 * @author Galanov Servey
 * @since 28.10.2018
 * @version 1.0
 */
public class EncodingFilter implements Filter {

    /**
     * Метод инициализации фильтра
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * Метод фильтра, устанавливающий кодировку ответа UTF-8
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        response.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }

    /**
     * Метод уничтожения лбьекта фильтра
     */
    @Override
    public void destroy() {

    }
}
