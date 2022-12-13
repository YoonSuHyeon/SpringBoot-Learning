package com.example.spring.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @PathVariable, 관련 테스트
 * http://localhost:8080/api/test
 */
@Controller
@RequestMapping("api/test/")
@Slf4j
public class TestController {

    /**
     * ex) http://localhost:8080/api/test/path1/sss
     * 여러개도 사용이 가능하다.
     */
    @GetMapping("path1/{path}")
    @ResponseBody
    public String getPath1(@PathVariable("path") String path) {
        log.info("path : {}", path);
        return "OK";
    }

    /**
     * ex) http://localhost:8080/api/test/path2/sss
     * map을 통해서도 받을 수 있다.
     */
    @GetMapping("path2/{path}")
    @ResponseBody
    public String getPath2(@PathVariable Map<String, String> pathMap) {
        log.info("path : {}", pathMap.get("path"));
        return "OK";
    }

    @PostMapping("path3/{path}")
    @ResponseBody
    public String getPath3(@PathVariable String path) {
        log.info("path : {}", path);
        return "OK";
    }

    /**
     * ex) http://localhost:8080/api/test/path4?path=ab+c
     *
     * @PathVariable은 URI 경로에서 값을 추출하기 때문에 인코딩되지 않지만  @RequestParam은 인코딩됩니다.
     * <p>
     * 따라서 @RequestParam  요청의 경우 매개변수는 URL 디코딩됩니다 .
     */
    @GetMapping("path3")
    @ResponseBody
    public String getPath4(@RequestParam String path) {
        log.info("path : {}", path);
        return "OK";
    }


}
