package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));// 문자로 넘어오기 때문에 Integer 로 파싱 해줘야함
        log.info("username={}, age={}",username,age);
        response.getWriter().write("ok");
    }

    @ResponseBody//스프링이 뷰 조회를 하지않고 리턴값을 http 응답메세지에 넣어서 반환한다.(@RestController 와 같은 효과)
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                                 @RequestParam("age") int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String memberName, //파라미터 와 변수명이 같으면 () 생략 가능
                                 @RequestParam int memberAge){
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, //String, int 등 객체가 아닌 단순 타입이면 @RequestParam 도 생략 가능(별로)
                                 int age){
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam String username, //required = true 면 해당 요청에 반드시 파라미터가 있어야함(없으면 오류)
                                 @RequestParam(required = false) Integer age){                                //required = false 면 파라미터가 없어도 오류 안뜸-null 값이 들어감(디폴트는 true)
        log.info("username={}, age={}", username, age);//대신 int 에는 null 값이 들어갈수 없으므로 Integer 로 바꿔야함(객체에는 null 이 들어갈 수 있음)
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "0") int age){ //Integer 대신 defaultValue 사용 가능
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap){ //맵, 멀티밸류맵 으로도 받을수있음(모든 파라미터 조회)
        log.info("username={}, age={}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

//    @ResponseBody
//    @RequestMapping("/model-attribute-v1")
//    public String modelAttributeV1(@RequestParam String username, @RequestParam int age){
//        HelloData helloData=new HelloData();
//        helloData.setAge(age);
//        helloData.setUsername(username);
//
//        log.info("helloData={}",helloData.toString());
//
//
//        return "ok";
//    }

    //위에 주석처리된 내용과 같음
    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){//@RequestParam String username, @RequestParam int age 를 @ModelAttribute 로 바로 집어 넣을수 있음

        log.info("username={}, age={}",helloData.getUsername(),helloData.getAge());


        return "ok";
    }
}
