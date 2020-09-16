/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rest;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;

/**
 * Jersey REST client generated for REST resource:EMA_API [order_items]<br>
 * USAGE:
 * <pre>
 *        OrderItems client = new OrderItems();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author User
 */
public class OrderItems
{

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://137.108.93.222/openstack/api/";

    public OrderItems()
    {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("order_items");
    }

    /**
     * @param responseType Class representing the response
     * @param oUCU query parameter
     * @param password query parameter
     * @return response object (instance of responseType class)
     */
    public <T> T get_application_json(Class<T> responseType, String oUCU,
            String password) throws ClientErrorException
    {
        String[] queryParamNames = new String[]{"OUCU", "password"};
        String[] queryParamValues = new String[]{oUCU, password};
        ;
        javax.ws.rs.core.Form form = getQueryOrFormParams(queryParamNames,
                queryParamValues);
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
     * @param oUCU form parameter
     * @param password form parameter
     * @param order_id form parameter
     * @param widget_id form parameter
     * @param number form parameter
     * @param pence_price form parameter
     * @return response object (instance of responseType class)
     */
    public <T> T post_application_x_www_form_urlencoded(Class<T> responseType,
            String oUCU, String password, String order_id, String widget_id,
            String number, String pence_price) throws ClientErrorException
    {
        String[] formParamNames = new String[]{"OUCU", "password", "order_id", "widget_id", "number", "pence_price"};
        String[] formParamValues = new String[]{oUCU, password, order_id, widget_id, number, pence_price};
        return webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED).post(javax.ws.rs.client.Entity.form(getQueryOrFormParams(formParamNames,
                formParamValues)), responseType);
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
