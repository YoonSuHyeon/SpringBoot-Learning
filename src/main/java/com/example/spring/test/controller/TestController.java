package com.example.spring.test.controller;

import lombok.Data;
import lombok.NoArgsConstructor;
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
     * Params으로 넣었을때와 body에 넣었을 경우는 다르게 작용된다 .
     *
     * <p>
     * 따라서 @RequestParam  요청의 경우 매개변수는 URL 디코딩됩니다 .
     */
    @GetMapping("path4")
    @ResponseBody
    public String getPath4(@RequestParam String path) {
        log.info("path : {}", path);
        return "OK";
    }

    @PostMapping("path5")
    @ResponseBody
    public String getPath5(@RequestParam String path) {
        log.info("path : {}", path);
        return "OK";
    }

    /**
     * ex) http://localhost:8080/api/test/path5
     * body에 아무런 값도 넣지 않으면 400에러 발생
     * body :{path : "ab+c"}
     */
    @GetMapping("path6")
    @ResponseBody
    public String getPath6(@RequestBody String path) {
        log.info("path : {}", path);
        return "OK";
    }

    /**
     * 같이 사용할 수 있다. 하지만 path와 같이 동일하게 사용하게되면 이상한 값이 나올수 있다.
     * 쿼리스트링으로 path의 값을 넣고 body에 form-data로 넣게 되면 둘이 합쳐져서 나오게된다.
     * from-data는 인코딩이 되기때문에 사용에 유의해야한다.
     */
    @PostMapping("/path7")
    @ResponseBody
    public String getPath7(@RequestParam String path, @ModelAttribute Path path2) {
        log.info("path : {}", path);
        log.info("path : {}", path2);
        return "OK";
    }

    @Data
    public static class Path {
        private String path;
    }

    /**
     * 쿼리스트링으로 사용하는경우 아래 API 사용 가능
     */
    @PostMapping("/path8")
    @ResponseBody
    public String getPath8(@RequestParam String path, @RequestBody Path path2) {
        log.info("path : {}", path);
        log.info("path : {}", path2);
        return "OK";
    }

}
