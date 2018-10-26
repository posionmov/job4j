package ru.job4j.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.models.Advertisement;
import ru.job4j.models.cars.*;
import ru.job4j.store.DbStore;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchServlet extends HttpServlet {
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
        List<Advertisement> ads = DbStore.INSTANCE.findAd(ad, Integer.valueOf(req.getParameter("priceFrom")), Integer.valueOf(req.getParameter("priceTo")));

//        http://localhost:8080/carsale/search?mark=2&model=0&body=0&year=2019&mileage=900001&trans=0&engine=%D0%92%D1%8B%D0%B1%D0%B5%D1%80%D0%B8%D1%82%D0%B5%20%D1%82%D0%B8%D0%BF%20%D0%B4%D0%B2%D0%B8%D0%B3%D0%B0%D1%82%D0%B5%D0%BB%D1%8F&capacity=7&power=500&drive=0&left=true&color=0&priceFrom=0&priceTo=0


        Map<String, Object> res = new LinkedHashMap<>();
        res.put("ads", ads);
        resp.getWriter().append(mapper.writeValueAsString(res)).flush();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
