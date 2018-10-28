package ru.job4j.servlets;

import org.json.JSONObject;
import ru.job4j.models.Advertisement;
import ru.job4j.models.User;
import ru.job4j.models.cars.*;
import ru.job4j.store.ValidateService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

/**
 * Сервлет создания обьявления
 * @author Galanov Sergey
 * @since 28.10.2018
 * @version 1.0
 */
public class CreateAdServlet extends HttpServlet {

    /**
     * Метод, отрабатывающий при запросе метода POST
     * Проверяет значение сессии и создает новое обьявление в БД
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("logged") != null) {
            User user = (User) req.getSession().getAttribute("logged");
            Car car = new Car();
            car.setDescr(req.getParameter("description"));
            car.setYearOfManufactured(Integer.valueOf(req.getParameter("year")));
            car.setMileage(Integer.valueOf(req.getParameter("mills")));
            car.setEngineCapacity(Float.valueOf(req.getParameter("capacity")));
            car.setPower(Float.valueOf(req.getParameter("power")));
            car.setLeftRudder(Boolean.valueOf(req.getParameter("left")));
            car.setPrice(Float.valueOf(req.getParameter("price")));
            car.setBodyType(new CarBodyType(Integer.valueOf(req.getParameter("body"))));
            car.setMark(new CarMark(Integer.valueOf(req.getParameter("mark"))));
            car.setTransmission(new CarTransmission(Integer.valueOf(req.getParameter("trans"))));
            car.setEngine(new CarEngine(Integer.valueOf(req.getParameter("engine"))));
            car.setDrive(new CarDrive(Integer.valueOf(req.getParameter("drive"))));
            car.setCarColor(new CarColor(Integer.valueOf(req.getParameter("color"))));
            car.setCarModel(new CarModel(Integer.valueOf(req.getParameter("model"))));
            car = ValidateService.INSTANCE.addCar(car);
            Advertisement newAd = new Advertisement();
            newAd.setDescription(req.getParameter("description"));
            newAd.setUser(user);
            newAd.setCreateDate(new Timestamp(System.currentTimeMillis()));
            newAd.setCar(car);
            newAd.setClose(false);
            newAd = ValidateService.INSTANCE.addAd(newAd);
            if (newAd.getId() != 0) {
                resp.getWriter().append(new JSONObject().put("newAd", true).toString()).flush();
                req.getSession().setAttribute("lastAdId", newAd.getId());
            } else {
                resp.getWriter().append(new JSONObject().put("newAd", false).toString()).flush();
            }
        }
    }
}
