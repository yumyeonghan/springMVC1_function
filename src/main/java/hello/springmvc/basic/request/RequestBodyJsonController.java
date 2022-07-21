package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */

@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper= new ObjectMapper();//Json 받으려면 있어야함.

    @PostMapping("/request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}",messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //json 에서 넘어오는 이름과 객체의 필드 명이 같아야함

        log.info("helloData={}",helloData.toString());

        response.getWriter().write("ok");

    }

    //(HttpServletRequest request, HttpServletResponse response) 이거 사용 안하고 @RequestBody, @ResponseBody 사용해도 됨
    @ResponseBody
    @PostMapping("/request-body-json-v2")
    public String requestBodyJsonV2(@RequestBody String messageBody)throws IOException {

        log.info("messageBody={}",messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class); //json 에서 넘어오는 이름과 객체의 필드 명이 같아야함

        log.info("helloData={}",helloData.toString());

        return "ok";

    }

    //@RequestBody 에 바로 HelloData 객체 사용 가능(제일 간단)
    @ResponseBody
    @PostMapping("/request-body-json-v3")
    public String requestBodyJsonV3(@RequestBody HelloData helloData) {
        //json 에서 넘어오는 이름과 객체의 필드 명이 같아야함
        //메세지 컨버터가 이 코드를 대신 실행. HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        log.info("helloData={}",helloData.toString());

        return "ok";

    }

    //@ResponseBody 가 있으면 메세지 컨버터가 들어올때도 적용되지만 나갈때도 적용되어 변경된 객체를 Json 형식으로 반환 할 수도있음
    @ResponseBody
    @PostMapping("/request-body-json-v4")
    public HelloData requestBodyJsonV4(@RequestBody HelloData helloData) {

        log.info("helloData={}",helloData.toString());

        return helloData;

    }
}
