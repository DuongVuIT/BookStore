package com.example.BookStore.Controller.admin;


import com.example.BookStore.entity.Author;
import com.example.BookStore.entity.User;
import com.example.BookStore.services.AuthorService;
import com.example.BookStore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/users")
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping
    public String showAllUsers(Model model) {
        List<User> userList = userService.getAllUser();
        model.addAttribute("users", userList);
        return "admin/users/list";
    }
    @GetMapping("/admin/users/add")
    public String showAddUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "admin/users/new_user";
    }
    @PostMapping("/admin/users/save")
    public String saveUser(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin/users";
    }
    @GetMapping("/admin/user/edit/{id}")
    public String showEditUserPage(@PathVariable("id") Integer id, Model model){
        User user = userService.get(id);
        if(user == null){
            return "notfound";
        }else {

            model.addAttribute("user",user);
            return "admin/users/edit";
        }
    }
    @GetMapping("/admin/users/delete/{id}")
    public String deleteUser(@PathVariable("id") Integer id){
        User user = userService.get(id);
        if(user == null){
            return "notfound";
        }else{
            userService.delete(id);
            return "redirect:/admin/users";
        }
    }
}
