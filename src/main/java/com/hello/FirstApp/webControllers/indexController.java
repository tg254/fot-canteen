package com.hello.FirstApp.webControllers;

import com.hello.FirstApp.exception.CustomExceptionRequest;
import com.hello.FirstApp.exception.ExceptionControl;
import com.hello.FirstApp.functionalities.PDFGen;
import com.hello.FirstApp.modals.*;
import com.hello.FirstApp.services.*;
import com.sun.deploy.util.SessionState;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Need to handle Incorrect and Null value acceptances

@Controller
@SessionAttributes({"username", "role", "extra"})
public class indexController {
    Log log = LogFactory.getLog(ExceptionControl.class);
    public static String uploadDirectory = System.getProperty("user.dir")+"\\src\\main\\resources\\static\\uploads";



    @Autowired
    private  UserService userService;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

//    List<Doctor> doctor_list = doctor_serve.readAllDoctors();

    @RequestMapping("/")
    public RedirectView index(){
        return new RedirectView("/home");
    }

    @RequestMapping("home")
    public ModelAndView indexLoad(){
        List<Item> items = itemService.readAllItems();
        ModelAndView mv = new ModelAndView();
        mv.addObject("items", items);
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping("orderSubmit")
    public ModelAndView makePlayer(Order order, @RequestParam("qty") String qty, @RequestParam("item") String item, @RequestParam("buyer") String buyer){

        Integer itemInt = Integer.parseInt(item);
        Integer qtyInt = Integer.parseInt(qty);
        Optional<Item> selectedItem =itemService.readItem(itemInt);
        Optional<User> selectedBuyer = userService.getUserByUsername(buyer);

        selectedItem.get().setQty(selectedItem.get().getQty()-qtyInt);
        itemService.updateItem(selectedItem.get());

        Order newOrder = new Order();
        newOrder.setQty(qtyInt);
        newOrder.setItem(selectedItem.get());
        newOrder.setMeal_type(selectedItem.get().getType());
        newOrder.setStatus("NOT PAID");
        newOrder.setTotal(selectedItem.get().getPrice()*qtyInt);
        newOrder.setBuyer(buyer);
//        newOrder.setItem(itemInt);

        orderService.createOrder(newOrder);

        ModelAndView mv = new ModelAndView();
//        System.out.println("DF DATA - " + player.getName());
//        mv.addObject("player", player);
        mv.setViewName("redirect:/home");
        return mv;
    }

    @RequestMapping("user_profile")
    public ModelAndView userOpenPageLoad(){

//        Items
        List<Item> items = itemService.readAllItems();
        List<Order> orders = orderService.readAllOrders();
        ModelAndView mv = new ModelAndView();
        mv.addObject("items", items);
        mv.addObject("orders", orders);
        mv.setViewName("user_profile");
        return mv;
    }

    @RequestMapping("item_add")
    public ModelAndView addItem(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("item_form");
        return mv;
    }

    @RequestMapping("itemSubmit")
    public ModelAndView itemSubmitLoad(Item item, @RequestParam("upimage") MultipartFile file, @RequestParam("seller") String seller) throws IllegalStateException, IOException {
        new File(uploadDirectory).mkdir();
        System.out.println("PATH - " + uploadDirectory);
//        File Upload
        file.transferTo(new File(uploadDirectory+"\\"+file.getOriginalFilename()));

        item.setImage(file.getOriginalFilename());
        item.setSeller(seller);
        itemService.createItem(item);
        ModelAndView mv = new ModelAndView();
        mv.addObject("item", item);
        mv.setViewName("redirect:/user_profile");
        return mv;
    }

    @RequestMapping("register_user")
    public ModelAndView registerLoad(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("register");
        return mv;
    }

    @RequestMapping("registerUserSubmit")
    public ModelAndView registerSubmitLoad(User user, @RequestParam("pass") String pass, @RequestParam("cpass") String cpass){
        if(!pass.equals(cpass)){
            throw new IllegalArgumentException();
        }
        user.setPassword(pass);
        userService.createUser(user);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.setViewName("redirect:/login");
        return mv;
    }

    @RequestMapping("login")
    public ModelAndView loginLoad(){

        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }

    @RequestMapping("loginSubmit")
    public ModelAndView loginSubmitLoad(@RequestParam("username") String username, @RequestParam("password") String password, ModelMap mv){
        System.out.println("LOGIN LAUNCHED");

        List<User> currentUser = userService.readAllUsers();
        for (User user : currentUser) {
            System.out.println("LOGIN - " + username + " "+password+" - USER - " + user.getUsername() + "||" + user.getPassword());
            if ((user.getPassword().equals(password))&&(user.getUsername().equals(username))) {
//                    Set Session
                System.out.println("LOGIN COMPLETED");

                mv.addAttribute("username", user.getUsername());
                mv.addAttribute("role", "Buyer");
                if(user.getType().equals("Seller")){
                    mv.addAttribute("role", "Seller");
                }

                return new ModelAndView("redirect:/home", mv);
            }
        }

        return new ModelAndView("redirect:/login");
    }

    @RequestMapping("/logout")
    public RedirectView logoutLoad(SessionStatus status){
        status.setComplete();
        RedirectView mv = new RedirectView("/home");

        return mv;
    }

//    Throw Controller specific exceptions
    @ExceptionHandler(value = Exception.class)
    public ModelAndView handleSpecificExceptions(HttpServletRequest req, Exception ex, Model model){
        HttpStatus badReq = HttpStatus.BAD_REQUEST;

        log.error(ex);
        model.addAttribute("status", badReq.value());
        model.addAttribute("message", ex.getMessage());
        model.addAttribute("cause", ex.getCause());
        return new ModelAndView("redirect:/error");
    }


}
