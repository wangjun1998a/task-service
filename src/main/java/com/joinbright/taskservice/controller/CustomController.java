package com.joinbright.taskservice.controller;

import com.joinbright.taskservice.service.CustomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author alin
 * @date 2020/7/6 10:14
 */

@Controller
public class CustomController {

    @Autowired
    private CustomService customService;


    @ResponseBody
    @GetMapping("/zendao/insertAll")
    @Transactional("zenTaoTransactionManager")
    public String insertZenTaoUser() {
        return customService.insertZenTaoUser();
    }

    @ResponseBody
    @GetMapping("/f1/insertAll")
    @Transactional("db2TransactionManager")
    public String insertInformationUsers() {
        return customService.insertInformationUsers();
    }

}
