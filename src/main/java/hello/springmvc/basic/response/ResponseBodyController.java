package hello.springmvc.basic.response;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Controller //String 반환은 자동으로 뷰리졸버가 적용돼서 http 바디에 뷰가 들어감

//@RestController=@Controller+@ResponseBody String 반환은 http 바디에 반환값이 그대로 들어감
//전체 메서드에 @ResponseBody 설정하려면 사용. Rest API(Http API) 를 만들때 사용
public class ResponseBodyController {
    //문자로 반환(응답)처리 3가지
    @GetMapping("/response-body-string-v1")
    public void responseBodyV1(HttpServletResponse response) throws IOException {
        response.getWriter().write("ok");
    }

    @GetMapping("/response-body-string-v2")
    public ResponseEntity<String> responseBodyV2(HttpServletResponse response){

        return new ResponseEntity<>("ok", HttpStatus.OK); //HttpEntity 와 같은것
    }

    @ResponseBody
    @GetMapping("/response-body-string-v3")
    public String responseBodyV3(){

        return "ok";
    }

    //Json 으로 반환(응답) 처리 2가지

    @GetMapping("/response-body-json-v1")
    public ResponseEntity<HelloData> responseBodyJsonV1(){
        HelloData helloData=new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(12);

        return new ResponseEntity<>(helloData,HttpStatus.OK);
    }

    @ResponseStatus(HttpStatus.OK) //Json 형식으로 반환할때는 상태코드를 적을수 없기 때문에 어노테이션 사용
    @ResponseBody
    @GetMapping("/response-body-json-v2")
    public HelloData responseBodyJsonV2(){
        HelloData helloData=new HelloData();
        helloData.setUsername("userA");
        helloData.setAge(12);

        return helloData;
    }


}
