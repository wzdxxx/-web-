package com.bookcode.controller;

import com. bookcode.constant.Constant;
import org.springframework.web.bind.annotation.RestController;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java. util.Map;
import com.alibaba.fastjson.JSON;

@RestController
@Path("/jersey")
public class JerseyController {
    @GET
    @Path("/get")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> getMessage() {
        return Constant.map;
    }

    @POST
    @Path("/post")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces(MediaType.APPLICATION_JSON)
    public Map<String, Object> postMessage(Map<String, Object> param) {
        System.out.println(JSON.toJSONString(param));
        return Constant.map;
    }
}