/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helloWorld;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author User
 */
@Path("helloWorld")
public class HelloWorld
{

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of HelloWorld
     */
    public HelloWorld()
    {
    }

    /**
     * retrieves representation of an instance of helloWorld.HelloWorld
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("text/html")
    public String getHtml()
    {
        return "<html><body><h1>Welcome to the World, Megamax Corporation</h1></body></html>";
    }

    /**
     * PUT method for updating or creating an instance of HelloWorld
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.TEXT_HTML)
    public void putHtml(String content)
    {
    }
}
