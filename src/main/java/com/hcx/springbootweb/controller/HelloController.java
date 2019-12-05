package com.hcx.springbootweb.controller;

import com.hcx.springbootweb.dao.DepartmentDao;
import com.hcx.springbootweb.dao.EmployeeDao;
import com.hcx.springbootweb.entities.Department;
import com.hcx.springbootweb.entities.Employee;
import com.hcx.springbootweb.exception.UserNotExistException;
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

    @RequestMapping("/hcx")
    public String hello(@RequestParam("user") String user){
        if (user.equals("hcx")){
            throw new UserNotExistException();
        }
        return "hcx";
    }

    /**
    * @Author HCX
    * @Description //TODO 用户登录到主界面
    * @Date 16:47 2019-12-04
    * @param username
* @param password
* @param map
* @param session
    * @return java.lang.String
    * @exception
    **/
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

    /**
    * @Author HCX
    * @Description //TODO 展示雇员信息页面
    * @Date 16:47 2019-12-04
    * @param model
    * @return java.lang.String
    * @exception
    **/
    @GetMapping("/emps")
    public String showEmps(Model model){
        Collection<Employee> emps = employeeDao.getAll();
        model.addAttribute("emps",emps);

        return "practice/list";
    }

    /**
    ** @param model
    * @return java.lang.String
    * @exception
    **/
    @GetMapping("/emp")
    public String toAddPage(Model model){
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        return  "practice/add";
    }

    /**
    * @Author HCX
    * @Description //TODO 保存一个雇员，并重定向到展示页面
    * @Date 16:46 2019-12-04
    * @param employee
    * @return java.lang.String
    * @exception
    **/
    @PostMapping("/emp")
    public String addEmp(Employee employee){
        employeeDao.save(employee);

        return "redirect:/emps";
    }

    /**
    * @Author HCX
    * @Description //TODO 转到编辑页面，并将选择数据回显到界面
    * @Date 16:47 2019-12-04
    * @param id
    * @param model
    * @return java.lang.String
    * @exception
    **/
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id,Model model){

        Employee employee = employeeDao.get(id);
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts",departments);
        model.addAttribute("emp",employee);

        return "practice/add";

    }

    /**
    * @Author HCX 
    * @Description //TODO 修改雇员信息
    * @Date 17:01 2019-12-04
    * @param employee
    * @return java.lang.String
    * @exception       
    **/
    @PutMapping("/emp")
    public String updateEmp(Employee employee){
        employeeDao.save(employee);

        return "redirect:/emps";
    }
    
    /**
    * @Author HCX 
    * @Description //TODO 删除员工信息
    * @Date 19:47 2019-12-04
    * @param id
    * @return java.lang.String
    * @exception       
    **/
    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeDao.delete(id);

        return "redirect:/emps";
    }
}
