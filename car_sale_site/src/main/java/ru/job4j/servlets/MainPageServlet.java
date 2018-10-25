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

public class MainPageServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        DbStore store = DbStore.INSTANCE;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("utf-8");
        List<Advertisement> ads = DbStore.INSTANCE.getAllAd();
        List<CarMark> marks = DbStore.INSTANCE.getAllMarks();
        List<CarTransmission> transmissions = DbStore.INSTANCE.getAllTransmissions();
        List<CarEngine> engines = DbStore.INSTANCE.getAllEngines();
        List<CarDrive> drives = DbStore.INSTANCE.getAllDrives();
        List<CarColor> colors = DbStore.INSTANCE.getAllColors();
        List<CarBodyType> bodies = DbStore.INSTANCE.getAllBodyTypes();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> res = new LinkedHashMap<>();
        res.put("ads", ads);
        res.put("marks", marks);
        res.put("transmissions", transmissions);
        res.put("engines", engines);
        res.put("drives", drives);
        res.put("colors", colors);
        res.put("bodies", bodies);

        resp.getWriter().append(mapper.writeValueAsString(res)).flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
