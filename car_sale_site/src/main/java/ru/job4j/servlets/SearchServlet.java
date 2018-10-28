package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.Advertisement;
import ru.job4j.models.cars.*;
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
 * Сервлет поиска обьявлений в БД
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class SearchServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при запросе метода GET
     * Производит поиск в БД и возращает результат
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        Car car = new Car();
        car.setMark(new CarMark(Integer.valueOf(req.getParameter("mark"))));
        car.setCarModel(new CarModel(Integer.valueOf(req.getParameter("model"))));
        car.setBodyType(new CarBodyType(Integer.valueOf(req.getParameter("body"))));
        car.setYearOfManufactured(Integer.valueOf(req.getParameter("year")));
        car.setMileage(Integer.valueOf(req.getParameter("mileage")));
        car.setTransmission(new CarTransmission(Integer.valueOf(req.getParameter("trans"))));
        car.setEngine(new CarEngine(Integer.valueOf(req.getParameter("engine"))));
        car.setEngineCapacity(Float.valueOf(req.getParameter("capacity")));
        car.setPower(Float.valueOf(req.getParameter("power")));
        car.setDrive(new CarDrive(Integer.valueOf(req.getParameter("drive"))));
        car.setLeftRudder(Boolean.valueOf(req.getParameter("left")));
        car.setCarColor(new CarColor(Integer.valueOf(req.getParameter("color"))));
        Advertisement ad = new Advertisement();
        ad.setCar(car);
        List<Advertisement> ads = ValidateService.INSTANCE.findAd(ad,
                                                        Integer.valueOf(req.getParameter("priceFrom")),
                                                        Integer.valueOf(req.getParameter("priceTo")));
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("ads", ads);
        resp.getWriter().append(mapper.writeValueAsString(res)).flush();

    }
}
