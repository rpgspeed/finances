package com.finances.controllers;

import com.finances.utils.ScheduledUpdate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

@Controller
public class RefreshController {

    private final ScheduledUpdate scheduledUpdate;

    public RefreshController(ScheduledUpdate scheduledUpdate) {
        this.scheduledUpdate = scheduledUpdate;
    }

    //    autoRefresh
    @RequestMapping(value = "autoRefresh", method = RequestMethod.POST)
    String getStock(String autoRefresh, Model model){
        scheduledUpdate.setTriggerEnabled(Boolean.parseBoolean(autoRefresh));
        return "redirect:/";
    }
}
