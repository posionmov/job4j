package ru.job4j.users;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import ru.job4j.crud.User;
import ru.job4j.crud.ValidateService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Map;

import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.*;

/**
 * Класс для тестирование работы сервлетов
 * @author Galanov Sergey
 * @since 01.10.2018
 * @version 1.0
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({ValidateService.class})
public class ValidateTest {

    /**
     * Приватные поля класса
     * Содержат ссылки на обьекты сервлетов
     * Обьект классов request и response
     */
    private UsersServlet deleteServlet;
    private UserCreateServlet createServlet;
    private UserUpdateServlet updateServlet;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private HttpSession session;
    private static int idUser = 0;

    /**
     * Метод, отрабатывающий до запуска каждого теста
     * Задает значения для полей данного класса
     */
    @Before
    public void beforeTest() {
        this.deleteServlet = new UsersServlet();
        this.createServlet = new UserCreateServlet();
        this.updateServlet = new UserUpdateServlet();
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.session = mock(HttpSession.class);
    }

    /**
     * Метод, вызывающийся после окончания всех тестов данного класса
     * Удаляет из хранилища созданные для теста данные
     */
    @AfterClass
    public static void afterAllTests() {
        ValidateService.INSTANCE.delete(idUser);
    }

    /**
     * Метод тестирования добавления пользователя в хранилище данных
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenAddUserThenUserInStore() throws ServletException, IOException {
        when(request.getParameter("name")).thenReturn("name");
        when(request.getParameter("login")).thenReturn("login");
        when(request.getParameter("email")).thenReturn("email");
        when(request.getParameter("right")).thenReturn("1");
        when(request.getParameter("password")).thenReturn("password");
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/views/UsersCreate.jsp")).thenReturn(rd);
        createServlet.doPost(request, response);
//        Map<Integer, User> result = ValidateService.INSTANCE.findAll();
//        assertThat(result.containsValue(new User("name", "login", "email", 1, "password")), is(true));
        boolean userExist = ValidateService.INSTANCE.checkUser("name", "password") != null;
        System.out.println("add user = " + userExist);
        assertThat(userExist, is(true));
    }

    /**
     * Метод тестирования удаления пользователя
     * @throws ServletException
     * @throws IOException
     */
    @Test
    public void whenDeleteUserThenCantFindHimIStore() throws ServletException, IOException {
        User user = ValidateService.INSTANCE.checkUser("name", "password");
        int idForDelete = user.getId();
        when(request.getParameter("type")).thenReturn("delete");
        when(request.getParameter("exit")).thenReturn("no");
        when(request.getParameter("id")).thenReturn(String.valueOf(idForDelete));
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/views/UsersList.jsp")).thenReturn(rd);
        deleteServlet.doPost(request, response);
//        Map<Integer, User> result = ValidateService.INSTANCE.findAll();
        boolean userNotExist = ValidateService.INSTANCE.checkUser("name", "password") == null;
//        assertThat(result.containsValue(new User("name", "login", "email", 1, "password")), is(false));
        System.out.println("delete user = " + userNotExist);
        assertThat(userNotExist, is(true));
    }

    /**
     * Метод стестирвания обновления пользователя с правами администратора
     * upd: из за того, что предыдущий тест удалял пользователя, то этот тест заного запускает тест добавления пользователя
     *                  и затем уже изменяет данного пользователя
     */
    @Test
    public void whenUpdateUserThenCantFindThisUser() throws ServletException, IOException {
        this.whenAddUserThenUserInStore();
        User user = ValidateService.INSTANCE.checkUser("name", "password");
        int idForUpdate = user.getId();
        when(request.getParameter("id")).thenReturn(String.valueOf(idForUpdate));
        when(request.getParameter("name")).thenReturn("name1");
        when(request.getParameter("login")).thenReturn("login1");
        when(request.getParameter("email")).thenReturn("email1");
        when(request.getParameter("password")).thenReturn("password1");
        when(request.getSession()).thenReturn(session);
        when(request.getSession().getAttribute("right")).thenReturn("admin");
        when(request.getParameter("right")).thenReturn("1");
        RequestDispatcher rd = mock(RequestDispatcher.class);
        when(request.getRequestDispatcher("WEB-INF/views/UserUpdate.jsp")).thenReturn(rd);
        updateServlet.doPost(request, response);
        boolean oldUser = ValidateService.INSTANCE.checkUser("name", "password") == null;
        this.idUser = ValidateService.INSTANCE.checkUser("name1", "password1").getId();
        boolean newUser = ValidateService.INSTANCE.checkUser("name1", "password1") != null;
        assertThat(oldUser, is(true));
        assertThat(newUser, is(true));
    }
}
