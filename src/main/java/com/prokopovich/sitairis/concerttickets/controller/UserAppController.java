package com.prokopovich.sitairis.concerttickets.controller;

import com.prokopovich.sitairis.concerttickets.dto.NewClientDto;
import com.prokopovich.sitairis.concerttickets.dto.NewManagerDto;
import com.prokopovich.sitairis.concerttickets.dto.NewUserDto;
import com.prokopovich.sitairis.concerttickets.entity.Client;
import com.prokopovich.sitairis.concerttickets.entity.Manager;
import com.prokopovich.sitairis.concerttickets.entity.User;
import com.prokopovich.sitairis.concerttickets.exceptions.NoSuchEntityException;
import com.prokopovich.sitairis.concerttickets.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping
public class UserAppController {
    private final UserAppService userService;
    private final ManagerService managerService;
    private final ClientService clientService;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public UserAppController(UserAppService userService, ManagerService managerService, ClientService clientService) {
        this.managerService = managerService;
        this.userService = userService;
        this.clientService = clientService;
    }

    @GetMapping(value = {"/userList"})
    public ModelAndView userList(Model model) {
        List<User> users = userService.getAllUser(Sort.by(Sort.Direction.ASC, "firstName"));
        List<Manager> managers = managerService.getAllManager();
        List<Client> clients = clientService.getAllClient();
        List<User> administrators = userService.getByUserType("Администратор");
        log.info("user List" + users);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("userList");
        model.addAttribute("users", users);
        model.addAttribute("managers", managers);
        model.addAttribute("clients", clients);
        model.addAttribute("administrators", administrators);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/userList was called");
        return modelAndView;
    }

    @RequestMapping(value = "addUser", method = RequestMethod.GET)
    public  ModelAndView showAddUserPage(Model model) {
        ModelAndView modelAndView = new ModelAndView("addUser");
        NewUserDto userForm = new NewUserDto();
        NewManagerDto managerForm = new NewManagerDto();
        NewClientDto clientForm = new NewClientDto();
        model.addAttribute("userForm", userForm);
        model.addAttribute("managerForm", managerForm);
        model.addAttribute("clientForm", clientForm);
        model.addAttribute("currentUser", MainController.currentUser);
        log.info("/addUser - GET was called" + userForm);
        return modelAndView;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public ModelAndView saveUser(Model model, //
                                 @Valid @ModelAttribute("userForm") NewUserDto userDto, Errors errorsUser, //
                                 @Valid @ModelAttribute("managerForm") NewManagerDto managerDto, Errors errorsManager,
                                 @Valid @ModelAttribute("clientForm") NewClientDto clientDto, Errors errorsClient) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/addUser - POST was called" + userDto);
        if (errorsUser.hasErrors() || errorsManager.hasErrors() || errorsClient.hasErrors()) {
            modelAndView.setViewName("addUser");
            model.addAttribute("currentUser", MainController.currentUser);
        }
        else {
            modelAndView.setViewName("userList");
            User newUser = new User(
                    userDto.getUserId(),
                    userDto.getFirstName(),
                    userDto.getName(),
                    userDto.getPatronymic(),
                    userDto.getUserType(),
                    userDto.getLogin(),
                    new BCryptPasswordEncoder().encode(userDto.getPassword()));
            userService.addNewUser(newUser);
            if (newUser.getUserType().equals("Менеджер")){
                Manager newManager = new Manager(
                        newUser.getUserId(),
                        newUser,
                        managerDto.getPhone(),
                        managerDto.getEventAgency(),
                        0);
                managerService.addNewManager(newManager);
            }
            if (newUser.getUserType().equals("Покупатель")){
                Client newClient = new Client(
                        newUser.getUserId(),
                        newUser,
                        managerDto.getPhone(),
                        clientDto.getEmail(),
                        0);
                clientService.addNewClient(newClient);
            }
            model.addAttribute("users",  userService.getAllUser(Sort.by(Sort.Direction.ASC, "firstName")));
            model.addAttribute("currentUser", MainController.currentUser);
            return modelAndView;
        }
        return modelAndView;
    }

    @PostMapping(value = {"/addClient"})
    public ModelAndView saveClient(Model model, @Valid @ModelAttribute("clientForm") NewClientDto clientDto,
                                   Errors errors) {
        ModelAndView modelAndView = new ModelAndView();
        log.info("/registration - POST was called" + clientDto);
        if (errors.hasErrors()) {
            modelAndView.setViewName("registration");
        }
        else {
            User newUser = new User(
                    clientDto.getUserInfo().getUserId(),
                    clientDto.getUserInfo().getFirstName(),
                    clientDto.getUserInfo().getName(),
                    clientDto.getUserInfo().getPatronymic(),
                    "Покупатель",
                    clientDto.getUserInfo().getLogin(),
                    new BCryptPasswordEncoder().encode(clientDto.getUserInfo().getPassword()));
            userService.addNewUser(newUser);
            Client newClient = new Client(
                    newUser.getUserId(),
                    newUser,
                    clientDto.getPhone(),
                    clientDto.getEmail(),
                    0);
            clientService.addNewClient(newClient);
            MainController.currentUser = newUser;
            model.addAttribute("userInfo",  clientService.getByClientId(newUser.getUserId()));
            modelAndView.setViewName("redirect:/infoClient/" + MainController.currentUser.getUserId());
            return modelAndView;
        }
        return modelAndView;
    }

    @RequestMapping(value = "/editUser/{userId}", method = RequestMethod.GET)
    public ModelAndView editPage(@PathVariable("userId") int id) throws NoSuchEntityException {
        User user = userService.getByUserId(id).orElseThrow(()-> new NoSuchEntityException("User not found") );
        Manager manager;
        Client client;
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("editUser");
        modelAndView.addObject("user", user);
        if (user.getUserType().equals("Менеджер"))
            manager = managerService.getByManagerId(id).orElseThrow(()-> new NoSuchEntityException("User not found") );
        else manager = new Manager();

        if (user.getUserType().equals("Покупатель"))
            client = clientService.getByClientId(id).orElseThrow(()-> new NoSuchEntityException("User not found") );
        else client = new Client();

        modelAndView.addObject("manager", manager);
        modelAndView.addObject("client", client);
        modelAndView.addObject("currentUser", MainController.currentUser);
        log.info("/editUser was called");
        return modelAndView;
    }

    @RequestMapping(value = "/editUser", method = RequestMethod.POST)
    public ModelAndView editUser( @Valid @ModelAttribute("user") User user, Errors errorsUser,
                                  @Valid @ModelAttribute("manager") Manager manager, Errors errorsManager,
                                  @Valid @ModelAttribute("client") Client client, Errors errorsClient) {
        log.info("/editUser - POST was called" + user);
        userService.addNewUser(user);
        if (user.getUserType().equals("Менеджер")) {
            manager.setManagerId(user.getUserId());
            manager.setUserInfo(user);
            log.info("/editUser - manager: " + manager);
            managerService.addNewManager(manager);
        }
        if (user.getUserType().equals("Покупатель")) {
            client.setClientId(user.getUserId());
            client.setUserInfo(user);
            log.info("/editUser - manager: " + client);
            client.setPhone(manager.getPhone());
            clientService.addNewClient(client);
        }
        ModelAndView modelAndView = new ModelAndView();
        if(MainController.currentUser.getUserType().equals("Покупатель"))
            modelAndView.setViewName("redirect:/infoClient/" + MainController.currentUser.getUserId());
        else {
            if (MainController.currentUser.getUserType().equals("Менеджер"))
                modelAndView.setViewName("redirect:/infoManager/" + MainController.currentUser.getUserId());
            else modelAndView.setViewName("redirect:/userList");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/deleteUser/{userId}", method = RequestMethod.GET)
    public ModelAndView deleteUser(@PathVariable("userId") int id) throws NoSuchEntityException {
        User user = userService.getByUserId(id).orElseThrow(()-> new NoSuchEntityException("User not found") );
        userService.deleteUser(user);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/userList");
        return modelAndView;
    }
}
