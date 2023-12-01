package sessac.sessacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class CrudPracticeController {
    @GetMapping("/register/practice")
    public String getRegister() {return "register";}

}
