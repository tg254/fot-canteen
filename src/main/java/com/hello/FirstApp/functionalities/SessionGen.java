package com.hello.FirstApp.functionalities;

import org.springframework.web.servlet.ModelAndView;

public class SessionGen {

    public ModelAndView logout(ModelAndView mv){


        mv.setViewName("index");
        return mv;
    }

}
