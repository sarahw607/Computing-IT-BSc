/*
 * RESTful client class for the widgets resource in the MegaMax API.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

/**
 * Jersey REST client generated for REST resource:EMA_API [widgets]<br>
 * USAGE:
 * <pre>
 *        Widgets client = new Widgets();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ad2472
 */
public class Widgets
{
   private WebTarget webTarget;
   private Client client;
   private static final String BASE_URI = "http://137.108.93.222/openstack/api/";

   public Widgets()
   {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("widgets");
   }

   /**
    * Implements GET for this resource
    * @param responseType Class representing the response - use String.class
    * @param OUCU query parameter
    * @param password query parameter
    * @return response object (instance of responseType class)
    */
   public <T> T get_application_json(Class<T> responseType, String OUCU, String password) throws ClientErrorException
   {
      String[] queryParamNames = new String[]{"OUCU", "password"};
      String[] queryParamValues = new String[]{OUCU, password};
      ;
      javax.ws.rs.core.Form form = getQueryOrFormParams(queryParamNames, queryParamValues);
      javax.ws.rs.core.MultivaluedMap<String, String> map = form.asMap();
      for (java.util.Map.Entry<String, java.util.List<String>> entry : map.entrySet())
      {
         java.util.List<String> list = entry.getValue();
         String[] values = list.toArray(new String[list.size()]);
         webTarget = webTarget.queryParam(entry.getKey(), (Object[]) values);
      }
      return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
   }

   private Form getQueryOrFormParams(String[] paramNames, String[] paramValues)
   {
      Form form = new javax.ws.rs.core.Form();
      for (int i = 0; i < paramNames.length; i++)
      {
         if (paramValues[i] != null)
         {
            form = form.param(paramNames[i], paramValues[i]);
         }
      }
      return form;
   }

   public void close()
   {
      client.close();
   }
   
}
