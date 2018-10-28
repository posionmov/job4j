package ru.job4j.servlets;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * Сервлет сохранения картики на сервере
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class PictureServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при инициализации сервлета
     * Создает папку на сервере для хранения картинок если такой еще нет
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        File file = new File(getServletContext().getRealPath("/images/"));
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    /**
     * Метод, отрабатывающий при запросе метода POST
     * Сохраняет картинку из запроса в папке БД с правильным именем
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int lastAd = (int) req.getSession().getAttribute("lastAdId");
        ServletFileUpload sf = new ServletFileUpload(new DiskFileItemFactory());
        List<FileItem> fileItem = null;
        try {
            fileItem = sf.parseRequest(req);
        } catch (FileUploadException e) {
            e.printStackTrace();
        }
        for (FileItem item : fileItem) {
            try {
                // Сохраняет не в проект, а на сервере (в папке проекта)
                item.write(new File(getServletContext().getRealPath("/images/" + lastAd + ".jpg")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
