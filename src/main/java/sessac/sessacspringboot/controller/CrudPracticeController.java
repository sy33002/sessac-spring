package sessac.sessacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import sessac.sessacspringboot.dto.UserPrac;

@Controller
public class CrudPracticeController {
    @GetMapping("/register/practice")
    public String getRegister() {return "register";}

    @PostMapping("/register/practice")
    public String postRegister(@RequestBody UserPrac userPrac, Model model) {
        model.addAttribute("data", userPrac);
        return "userDetail";
    }
}
