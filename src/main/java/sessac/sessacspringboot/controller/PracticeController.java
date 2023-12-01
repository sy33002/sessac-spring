package sessac.sessacspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PracticeController {

//    @GetMapping("practice")
//    public void getPractice(Model mdl) {
//        int age = 17;
//
//        mdl.addAttribute("age", age);
//    }

    @GetMapping("people")
    public void getPeople(Model mdl) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("kim", 20));
        people.add(new Person("lee", 21));
        people.add(new Person("park", 22));
        people.add(new Person("yoo", 23));

        mdl.addAttribute("people", people);
    }
    class Person {
        private String name;
        private int age;
        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }

    @GetMapping("/introduce/{name}")
    public String getIntroduce(@PathVariable String name, Model model) {
        model.addAttribute("name", "홍길동");
        return "response";
    }
    @GetMapping("/introduce2")
    public String getIntroduce2(@RequestParam String name, @RequestParam String age, Model model) {
        model.addAttribute("name", name);
        model.addAttribute("age", age);
        return "response";
    }
}
