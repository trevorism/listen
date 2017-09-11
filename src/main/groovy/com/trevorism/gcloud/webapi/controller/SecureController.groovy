package com.trevorism.gcloud.webapi.controller

import org.codehaus.jackson.jaxrs.Secure

import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Path("/api")
class SecureController {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    List<String> endpoints(){
        return ["get", "post"]
    }

    @Secure
    @GET
    @Path("get")
    @Produces(MediaType.APPLICATION_JSON)
    String get(){
        return "Get!"
    }

    @Secure
    @POST
    @Path("post")
    @Consumes(MediaType.APPLICATION_JSON)
    Map<String, String> post(Map<String, String> map){
        return map
    }
}
