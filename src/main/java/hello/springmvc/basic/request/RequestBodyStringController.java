package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {
    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스트림은 바이트 코드이기 때문에 문자로 받으려면 어떤 인코딩으로 받을지 정해줘야함

        log.info("messageBody={}",messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2") //스트림을 처음부터 받을수 있음. (이거도 불편)
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);//스트림은 바이트 코드이기 때문에 문자로 받으려면 어떤 인코딩으로 받을지 정해줘야함

        log.info("messageBody={}",messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {
        String messageBody = httpEntity.getBody(); //HttpEntity 는 http 메세지를 스팩화 함. 내가 설정한 스트링으로 자동으로 변환해줌(메세지 컨버터)
        log.info("messageBody={}",messageBody);

        return new HttpEntity<>("ok");
    }

    //HttpEntity 기능을 사용할수 있는 어노테이션 제공-@RequestBody, @ResponseBody
    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) throws IOException {

        log.info("messageBody={}",messageBody);

        return "ok";
    }
}
