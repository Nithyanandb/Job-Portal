package JobPortal.main.Controller;

import JobPortal.main.Entity.Users;
import JobPortal.main.Entity.UsersType;
import JobPortal.main.Repository.UsersRepository;
import JobPortal.main.Services.UsersService;
import JobPortal.main.Services.UsersTypeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@Controller
public class UsersController {

    private final UsersTypeService usersTypeService;
    private final UsersService usersService;

    @Autowired
    public UsersController(UsersTypeService usersTypeService, UsersService usersService) {
        this.usersTypeService = usersTypeService;
        this.usersService = usersService;
    }




    @GetMapping("/register")
    public String register(Model model) {
        List<UsersType> usersTypes = usersTypeService.getAll();
        model.addAttribute("getAllTypes", usersTypes);
        model.addAttribute("user",new Users());
        return "register";
    }

    @PostMapping("/register/new")
    public String userRegistration(@Valid Users users, Model model) {
//        System.out.println("User :: " + users);
        Optional<Users> optionalUsers =  usersService.getUserByEmail(users.getEmail());

        if (optionalUsers.isPresent()) {
            model.addAttribute("error"," Email is exist already try to login");
            List<UsersType> usersTypes = usersTypeService.getAll();
            model.addAttribute("getAllTypes", usersTypes);
            model.addAttribute("user",new Users());
            return "register";
        }
        usersService.addNew(users);
        return "dashboard";
    }


}
