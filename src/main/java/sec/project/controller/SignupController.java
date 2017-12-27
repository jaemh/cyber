package sec.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sec.project.domain.Signup;
import sec.project.repository.SignupRepository;
import java.security.Principal;
import javax.annotation.PostConstruct;
import java.util.List;
import java.util.logging.Logger;

@Controller
public class SignupController {

    @Autowired
    private SignupRepository signupRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/userPage")
    public String getUser(){
        return "userPage";
    }

    @GetMapping("/admin")
    public String getad(){

        return "admin";
    }

    @GetMapping("/adminPage")
    public String getAdmin(){

        return "redirect:/done";
    }

    @GetMapping("/done")
    public String done(){

        return "done";
    }

    @GetMapping("/menu")
    public String showAdmin(Principal principal){
        if(principal.getName() != null) {
            System.out.println(principal.getName());
        } else {
            System.out.println("********Not logged in********");
        }

        return "menu";
    }

    @GetMapping("/userList")
    public String listGET(Model model) {
        List<Signup> s = signupRepository.findAll();
        model.addAttribute("s", s);
        return "userList";
    }


    @RequestMapping("/")
    public String defaultMapping() {
        return "redirect:/menu";
    }

    @GetMapping("/create")
    public String create() {
        return "create";
    }

    @PostMapping("/create")
    public String submitForm(@RequestParam String name, @RequestParam String address) {
        Signup saveForm = new Signup();
        saveForm.setName(name);
        saveForm.setAddress(address);

        signupRepository.saveAndFlush(saveForm);

        return "redirect:/userList";
    }


}
