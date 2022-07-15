package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController// 원래는 return 값이 뷰 이름으로 반환되는데  RestController 을 사용하면 string 이 반환된다.(restApi 의 핵심 컨트롤러)
public class LogTestController {

    //private final Logger log= LoggerFactory.getLogger(getClass()); 내 클래스 지정 (@Slf4j 롬복 사용시 log 생성 안해도 됨)

    @RequestMapping("/log-test")
    private String logTest(){
        String name="Spring";

        System.out.println("name = " + name); //과거에는 이렇게 사용(운영단계에서 유저수에 따라 폭탄맞음)

        //지금은 반드시 log 사용, trace 가 가장 깊은(낮은)단계
        //logging.level.hello.springmvc=trace 으로 properties 설정시 trace~error 까지 모든 정보 출력
        //운영서버에서는 info 레벨로 설정, 개발할땐 trace 로 설정
        log.trace("trace log={}",name);
        log.debug("debug log={}",name);
        log.info("info log={}", name);
        log.warn("warn log={}",name);
        log.error("error log={}",name);

        return "ok";
    }

}
