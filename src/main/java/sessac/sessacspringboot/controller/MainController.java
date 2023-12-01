package sessac.sessacspringboot.controller;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import sessac.sessacspringboot.dto.UserDTO;
import sessac.sessacspringboot.vo.UserVo;

@Controller
// @RestController: Controller 면서 모든 메소드가 @RsponseBody를 갖도록 하는 친구
public class MainController {
    @GetMapping("/api")
    public String getApi() {return "request";}

    // 1) get : ?key=value
    // /get/response1?name=abc
    @GetMapping("/get/response1")
    public String getResponse1(@RequestParam(value="name") String  name, @RequestParam(value="age") String age, Model model) {
        // @Requestparam: 요청의 파라미터를 매개변수로 받을 때 적는 이노테이션
        // value = ? 뒤에 넘어온 key
        // requitred 값을 설정할 수 있다. = true / false -> 기본값은 true
        //  required가 true로 되어있으면 ?뒤에 해당되는 key가 없을 경우 메소드를 찾지 못한다 = 400 error
        model.addAttribute("name", name);
        return "response";
    }
    @GetMapping("/get/response2")
    public String getResponse2(@RequestParam(value="name", required = false) String  name, Model model) {
        // @Requestparam: 요청의 파라미터를 매개변수로 받을 때 적는 이노테이션
        // value = ? 뒤에 넘어온 key
        model.addAttribute("name", name);
        return "response";
    }

    // /get/response3/이름/나이
    // url에 변수가 들어올 때 그 값을 가져오는 방법
    @GetMapping("/get/response3/{name}/{age}")
    public String getResponse3(@PathVariable(value="name") String n, // name으로 들어온 애를 n이라는 변수로 활용하겠다면!
                               @PathVariable String age, Model model) {
        model.addAttribute("name", n);
        model.addAttribute("age", "13");
        return "response";
    }


    @GetMapping({"/get/response4/{name}", "/get/response4/{name}/{age}"})
    public String getResponse4(@PathVariable(value="name") String n, // name으로 들어온 애를 n이라는 변수로 활용하겠다면!
                               @PathVariable(required = false) String age, Model model) {
        // @Pathvariable에 required 설정이 가능하나, 기본값은 true
        // @Pathvariable에 required를 설정할 때는 GetMapping에 url도 같이 설정해줘야 한다.
        // required값이 false인 친구는 뒤로 가야한다.
        model.addAttribute("name", n);
        model.addAttribute("age", age);
        return "response";
    }

    /////////////////////////////

    // post로 값을 전달할 때 그 값을 controller에서 받는 방법은 @RequestParam
    @PostMapping("/post/response1")
    public String postResponse1(@RequestParam(value="name") String name, Model model) {
        model.addAttribute("name", name);
        return "response";
    }
    @PostMapping("/post/response2")
    public String postResponse2(@RequestParam(value="name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return "response";
    }
    @PostMapping("/post/response3")
    @ResponseBody // res.send
    // return 하는 문자열의 template 파일을 불러오는 게 아니라
    // return 하는 문자열 그대로 값을 전달하는 것
    public String postResponse3(@RequestParam(value="name", required = false) String name, Model model) {
        model.addAttribute("name", name);
        return name;
    }

    // 실습
    @GetMapping("/practice123")
    public String getPractice123() {return "practice1";}

    @PostMapping("/post/practice123")
    @ResponseBody
    public String postPractice(@RequestParam String name, @RequestParam String gender,
                               @RequestParam int year, @RequestParam int month,@RequestParam int day,  @RequestParam String interest, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("gender", gender);
        model.addAttribute("year", year);
        model.addAttribute("month", month);
        model.addAttribute("day", day);
        model.addAttribute("interest", interest);
        String msg = "이름: " + name + ", 성별: " + gender + ", 생년월일 :" + year + month + day + ", 관심사 : " + interest;
        return msg;
    }

    // 일반 폼
    @GetMapping("/dto/response1")
    @ResponseBody
    public String dtoResponse1(@ModelAttribute UserDTO userDTO) {
        // 변수로 값을 하나씩 가져오는 게 아니라 객체에 담아서 가져오고 싶을 때 사용하는 방법
        // @ModelAttribute: html 폼 데이터를 컨트롤러로 전달할 때 객체에 매핑해주는 친구
        // 매핑 = setter 함수를 실행한다.
        // -> ?name=&age= -> setName() setAge()
        String msg = "이름 : " + userDTO.getName() + ", 나이 : " + userDTO.getAge();
        return msg;
    }

    @PostMapping("/dto/response2")
    @ResponseBody
    public String dtoResponse2(UserDTO userDTO) {
        // 아무것도 없는 상태 = @ModelAttribute 상태
        String msg = "이름 : " + userDTO.getName() + ", 나이 : " + userDTO.getAge();
        return msg;
    }

    @PostMapping("/dto/response3")
    @ResponseBody
    public String dtoResponse3(@RequestBody UserDTO userDTO) {
        // @RequestBody : 요청의 본문에 있는 데이터(body)를 받아와서 객체에 매핑 (필드값에 값을 주입)
        // 전달받은 요청의 형식이 json 또는 xml일 때만 실행 가능
        // 일반폼 전송 = www-x-form-urlencoded -> 전송할 수 없는 형태 -> 오류
        String msg = "이름 : " + userDTO.getName() + ", 나이 : " + userDTO.getAge();
        return msg;
    }

    // get 방식 - vo = null (modelAttribute = setter 함수를 실행)
    // post 방식 - vo = null
    // post 방식 - vo - requestbody = 오류
    @GetMapping("/vo/response1")
    @ResponseBody
    public String voResponse1(UserVo userVo) {
        String msg = "이름 : " + userVo.getName() + ", 나이 : " + userVo.getAge();
        return msg;
    }

    @PostMapping("vo/response2")
    @ResponseBody
    public String voResponse2(UserVo userVo) {
        String msg = "이름 : " + userVo.getName() + ", 나이 : " + userVo.getAge();
        return msg;
    }

    @PostMapping("vo/response3")
    @ResponseBody
    public String voResponse3(UserVo userVo) {
        String msg = "이름 : " + userVo.getName() + ", 나이 : " + userVo.getAge();
        return msg;
    }

    // DTO - axios
    @GetMapping("/axios/response1")
    @ResponseBody
    public String axiosAPI1(@RequestParam(value = "name") String name, @RequestParam(value = "age") String age){
        String msg = "이름 : " + name + "\n나이 :" + age;
        return msg;
    }
    @GetMapping("/axios/response2")
    @ResponseBody
    public String axiosAPI2(UserDTO userDTO){
            String msg = "이름 : " + userDTO.getName() + "\n나이 :" + userDTO.getAge();
        return msg;
    }
    @PostMapping("/axios/response3")
    @ResponseBody
    public String axiosAPI3(@RequestParam(value = "name", required = false) String name, @RequestParam(value = "age", required = false) String age){
        // @RequestParam = @ModelAttrivute = query string으로 넘어온 데이터를 읽을 수 있다.
        // @RequestParam에 require true =>
        String msg = "이름 : " + name + "\n나이 :" + age;
        return msg;
    }
    @PostMapping("/axios/response4")
    @ResponseBody
    public String axiosAPI4(UserDTO userDTO){
        // ModelAttribute는 json으로 넘어온 데이터를 읽지 못한다
        String msg = "이름 : " + userDTO.getName() + "\n나이 :" + userDTO.getAge();
        return msg;
    }
    @PostMapping("/axios/response5")
    @ResponseBody
    public String axiosAPI5(@RequestBody UserDTO userDTO){
        String msg = "이름 : " + userDTO.getName() + "\n나이 :" + userDTO.getAge();
        return msg;
    }
    // axios - get - (일반) - ? > o
    // axios get, dto > o
    // axios post - (RequestParam) -> required가 false일 때는 null, true일 때는 오류
    // axios post - requestbody x dto -> null
    // axios post - requestbody o dto -> o

    // GET 일반: 가능
    // GET VO : null = @ModelAttribute (setter함수를 실행할 수가 없어서)
    // Post 일반: 실패
    // Post VO - requestbody X: null = @ModelAttribute
    // Post VO - requestbody O:
    @GetMapping("/practice124")
    public String getPractice2() { return "practice2" ;}

    @PostMapping("/practice124")
    @ResponseBody
    public String axiosAPI6(@RequestBody UserVo userVo) {
        System.out.println(userVo);
        String msg = userVo.getName() + "회원가입 성공";
        return msg;
    }

}
