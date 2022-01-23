package hello.servlet.basic.request;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

//http://localhost:8080/request-header?username=hello
@WebServlet(name="requestHeaderServlet", urlPatterns = "/request-header")
public class RequestHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        printStartLine(req);
        printHeaders(req);
        printHeaderUtils(req);
        printEtc(req);

        resp.getWriter().write("ok");
    }

    private void printStartLine(HttpServletRequest req) {
        System.out.println("--- Request-Line - start ---");
        System.out.println("req.getMethod() = " + req.getMethod()); //GET, POST
        System.out.println("req.getProtocol() = " + req.getProtocol()); //HTTP/1.1
        System.out.println("req.getScheme() = " + req.getScheme()); //http
        System.out.println("req.getRequestURL() = " + req.getRequestURL()); //http://localhost:8080/request-header
        System.out.println("req.getRequestURI() = " + req.getRequestURI()); //request-header
        System.out.println("req.getQueryString = " + req.getQueryString()); //username=hello
        System.out.println("req.isSecure() = " + req.isSecure()); //isHttps
        System.out.println("--- Request-Line - end ---");
    }

    private void printHeaders(HttpServletRequest req){
        System.out.println("--- Headers - start ---");
        /*Enumeration<String> headerNames = req.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            System.out.println("headerName = " + headerName + ":" + req.getHeader(headerName));
        }*/
        req.getHeaderNames().asIterator()
                .forEachRemaining(headerName-> System.out.println("headerName = " + headerName + ":" + req.getHeader(headerName)));
        System.out.println("--- Headers - end ---");
    }

//    Header 편리한 조회
    private void printHeaderUtils(HttpServletRequest req){
        System.out.println("--- Header 편의조회 - start ---");
        System.out.println("[Host 편의조회]");
        System.out.println("req.getServerName() = " + req.getServerName());
        System.out.println("req.getServerPort() = " + req.getServerPort());
        System.out.println();

        System.out.println("[Accept-Language 편의조회]");
        req.getLocales().asIterator()
                        .forEachRemaining(locale-> System.out.println("locale = " + locale));
        System.out.println("req.getLocale() = " + req.getLocale());
        System.out.println();

        System.out.println("[cookie 편의조회]");
        if(req.getCookies() != null){
            for(Cookie cookie : req.getCookies()){
                System.out.println("cookie.getName() = " + cookie.getName()+ ":" + cookie.getValue());
            }
        }
        System.out.println();

        System.out.println("[Content 편의조회]");
        System.out.println("req.getContentType() = " + req.getContentType());
        System.out.println("req.getContentLength() = " + req.getContentLength());
        System.out.println("req.getCharacterEncoding() = " + req.getCharacterEncoding());
        System.out.println();

        System.out.println("--- Header 편의조회 - end ---");

    }

    private void printEtc(HttpServletRequest req){
        System.out.println("--- 기타조회 - start ---");
        System.out.println("[Remote 정보]");
        System.out.println("req.getRemoteHost() = " + req.getRemoteHost());
        System.out.println("req.getRemoteAddr() = " + req.getRemoteAddr());
        System.out.println("req.getRemotePort() = " + req.getRemotePort());
        System.out.println();

        System.out.println("[Local 정보]");
        System.out.println("req.getLocalName() = " + req.getLocalName());
        System.out.println("req.getLocalAddr() = " + req.getLocalAddr());
        System.out.println("req.getLocalPort( = " + req.getLocalPort());
        System.out.println();
        System.out.println("--- 기타조회 - end ---");
    }


}
