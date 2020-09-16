/*
 * RESTful client class for the orders resource in the MegaMax API
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

/**
 * Jersey REST client generated for REST resource:EMA_API [orders]<br>
 * USAGE:
 * <pre>
 *        Orders client = new Orders();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author ad2472
 */
public class Orders
{
   private WebTarget webTarget;
   private Client client;
   private static final String BASE_URI = "http://137.108.93.222/openstack/api/";

   public Orders()
   {
      client = javax.ws.rs.client.ClientBuilder.newClient();
      webTarget = client.target(BASE_URI).path("orders");
   }

   /**
    * @param responseType Class representing the response
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

   /**
    * @param responseType Class representing the response
    * @param OUCU form parameter
    * @param password form parameter
    * @param client_id form parameter
    * @param latitude form parameter
    * @param longitude form parameter
    * @return response object (instance of responseType class)
    */
   public <T> T post_application_x_www_form_urlencoded(Class<T> responseType, String OUCU, String password, String client_id, String latitude, String longitude) throws ClientErrorException
   {
      String[] formParamNames = new String[]{"OUCU", "password", "client_id", "latitude", "longitude"};
      String[] formParamValues = new String[]{OUCU, password, client_id, latitude, longitude};
      return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(javax.ws.rs.client.Entity.form(getQueryOrFormParams(formParamNames, formParamValues)), responseType);
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
