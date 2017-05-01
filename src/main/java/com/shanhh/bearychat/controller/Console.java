package com.shanhh.bearychat.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author dan
 * @since 2017-05-01 11:08
 */
@RestController
@RequestMapping("console")
public class Console {

    @RequestMapping(value = "flushusers", method = RequestMethod.GET)
    public String flushusers() throws IOException {
        return null;
    }

}
