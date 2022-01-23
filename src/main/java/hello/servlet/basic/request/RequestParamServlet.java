package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 1. 파라미터 전송 기능
 * http://localhost:8080/request-param?username=hello&age=20
 * <p>
 * 2. 동일한 파라미터 전송 가능
 * http://localhost:8080/request-param?username=hello&username=kim&age=20
 */
@WebServlet(name="requestParamServlet", urlPatterns = "/request-param")
public class RequestParamServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //전체 파라미터 조회
        System.out.println("[전체 파라미터 조회] - start");
        req.getParameterNames().asIterator()
                .forEachRemaining(param -> System.out.println("param = " + param + " : " + req.getParameter(param)));
        System.out.println("[전체 파라미터 조회] - end");
        System.out.println();

        System.out.println("[단일 파라미터 조회] - start");
        System.out.println("req.getParameter(\"username\") = " + req.getParameter("username"));
        System.out.println("req.getParameter(\"age\") = " + req.getParameter("age"));
        System.out.println("[단일 파라미터 조회] - end");
        System.out.println();

        System.out.println("[이름이 같은 복수 파라미터 조회] - start");
        String[] usernames = req.getParameterValues("username");
        for (String username : usernames) {
            System.out.println("username = " + username);
        }
        System.out.println("[이름이 같은 복수 파라미터 조회] - end");
        System.out.println();

        resp.getWriter().write("ok");
    }
}
