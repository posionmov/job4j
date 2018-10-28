package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import ru.job4j.models.Advertisement;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;
import ru.job4j.store.DbStore;
import ru.job4j.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервлет получения и закрытия обьявлений
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class ListAdsServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при инициализации сервлета
     * Создает подключение к БД
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        DbStore store = DbStore.INSTANCE;
    }

    /**
     * Метод, отрабатывающий при запросе метода GET
     * Возращает список обьявлений, марок автомобилей, коробок передач, двигателей, приводов, цветов, кузовов
     *                      и текущего пользователя
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Advertisement> ads = ValidateService.INSTANCE.getAllAd();
        List<CarMark> marks = ValidateService.INSTANCE.getAllMarks();
        List<CarTransmission> transmissions = ValidateService.INSTANCE.getAllTransmissions();
        List<CarEngine> engines = ValidateService.INSTANCE.getAllEngines();
        List<CarDrive> drives = ValidateService.INSTANCE.getAllDrives();
        List<CarColor> colors = ValidateService.INSTANCE.getAllColors();
        List<CarBodyType> bodies = ValidateService.INSTANCE.getAllBodyTypes();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("ads", ads);
        res.put("marks", marks);
        res.put("transmissions", transmissions);
        res.put("engines", engines);
        res.put("drives", drives);
        res.put("colors", colors);
        res.put("bodies", bodies);
        res.put("login", req.getSession().getAttribute("logged") != null);
        res.put("curUser", req.getSession().getAttribute("logged")!= null ? req.getSession().getAttribute("logged") : new User(0));
        resp.getWriter().append(mapper.writeValueAsString(res)).flush();
    }

    /**
     * Метод, отрабатывающий при запросе метода POST
     * Устанавливает значение в поле close обьекта обьявления в БД
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Advertisement ad = new Advertisement(Integer.valueOf(req.getParameter("id")));
        resp.getWriter().append(new JSONObject().put("close", DbStore.INSTANCE.closeAd(ad)).toString()).flush();
    }
}
