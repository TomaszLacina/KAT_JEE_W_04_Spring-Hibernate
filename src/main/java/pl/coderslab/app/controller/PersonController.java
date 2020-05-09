package pl.coderslab.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.app.entity.Person;

@RequestMapping("/person")
@Controller
public class PersonController {

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getForm(Model model){
        model.addAttribute("person", new Person());
        return "personForm";
    }

    @RequestMapping(value = "/new", method = RequestMethod.POST)
    @ResponseBody
    public Person createPerson(@ModelAttribute Person person){
        return person;
    }

//    @RequestMapping(value = "/new", method = RequestMethod.POST)
//    @ResponseBody
//    public Person createPerson(@RequestParam String login, @RequestParam String password, @RequestParam String email){
//        Person person = new Person();
//        person.setLogin(login);
//        person.setPassword(password);
//        person.setEmail(email);
//
//        return person;
//    }


}
