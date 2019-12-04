package com.hcx.springbootweb.controller;

import com.hcx.springbootweb.dao.DepartmentDao;
import com.hcx.springbootweb.dao.EmployeeDao;
import com.hcx.springbootweb.entities.Department;
import com.hcx.springbootweb.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

/**
 * @ClassName HelloController
 * @Description TODO
 * @Author 贺楚翔
 * @Date 2019-12-03 13:30
 * @Version 1.0
 **/
@Controller
public class HelloController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    @ResponseBody
    @RequestMapping("/hcx")
    public String hello(){
        return "hcx";
    }

    @PostMapping("/user/login")
    public String toList(@RequestParam("username") String username, @RequestParam("password") String password, Map<String,Object> map, HttpSession session){
        if (!StringUtils.isEmpty(username) && "123456".equals(password)){
            session.setAttribute("loginUser",username);
            return "redirect:/main.html";
        }else {
            map.put("msg","用户名密码错误");
            return "login";
        }
    }

    @GetMapping("/emps")
    public String showEmps(Model model){
        Collection<Employee> emps = employeeDao.getAll();
        model.addAttribute("emps",emps);

        return "practice/list";
    }

//    @PostMapping("/addUser")
//    public String addEmps(){
//        return "practice/add";
//    }

    @GetMapping("/emp")
    public String addEmps(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return  "practice/add";
    }
}
