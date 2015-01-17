package Model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.web.DefaultRedirectStrategy;

public class FailureAuthentificationStrategy extends DefaultRedirectStrategy {

    public void sendRedirect(HttpServletRequest request,
            HttpServletResponse response, String url) throws IOException {
        String urlWithOriginalQueryString = url + "?" + request.getQueryString();
        String srcurl = request.getHeader("referer");
        if(srcurl.contains("adminlogin")){
        	super.sendRedirect(request, response, "/loginfail?type=adminerror");
        }
        else{
        super.sendRedirect(request, response, "/reg?logresult=fail");
        }
    }

}
