package com.dianping.sansi.sansilab.controllers;

import com.dianping.sansi.sansilab.entities.Result;
import com.dianping.sansi.sansilab.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/5/29.
 */
@Controller
@RequestMapping(value = "/test")
public class TestController {

    @Autowired
    FileService fileService;

    @RequestMapping(value = "/number/{number}",method = RequestMethod.GET)
    @ResponseBody
    public Result number(@PathVariable("number") Integer number) {
        Result result = new Result();
        result.put("number", number);
        return result;
    }
}
