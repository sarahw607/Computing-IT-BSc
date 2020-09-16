/**
 * Backing bean for holding order and order items data, request-scoped.
 * Although latitude and longitude are not used in our web application,
 * the API requires them.
 *
 * This class includes methods to call GET and POST operations in the
 * MegaMax API.
 */
package beans;

import rest.Orders;
import javax.enterprise.inject.Model;
import javax.json.JsonArray;
import javax.json.JsonObject;
import util.Utilities;

/**
 * @author TM352
 */
@Model
public class OrderBean
{
   //The first three attributes are for the order table
   //The clientId should be set before calling orderPost()
   private String clientId;
   private String latitude, longitude;

   //Remaining attributes are additional information for the order items
   //These fields should be set before calling orderItemsPost()   
   private String orderId; //add items to this order id
   private String widgetId;
   private String number;
   private String pence;

   /**
    * Initial values for some of the model data. We're not using latitude and
    * longitude in this prototype, so setting these to dummy values.
    */
   public OrderBean()
   {
      latitude = "0";
      longitude = "0";
   }

   public String getLatitude()
   {
      return latitude;
   }

   public void setLatitude(String latitude)
   {
      this.latitude = latitude;
   }

   public String getLongitude()
   {
      return longitude;
   }

   public void setLongitude(String longitude)
   {
      this.longitude = longitude;
   }

   public String getOrderId()
   {
      return orderId;
   }

   public void setOrderId(String orderId)
   {
      this.orderId = orderId;
   }

   public String getWidgetId()
   {
      return widgetId;
   }

   public void setWidgetId(String widgetId)
   {
      this.widgetId = widgetId;
   }

   public String getNumber()
   {
      return number;
   }

   public void setNumber(String num)
   {
      this.number = num;
   }

   public String getPence()
   {
      return pence;
   }

   public void setPence(String pence)
   {
      this.pence = pence;
   }

   public String getClientId()
   {
      return clientId;
   }

   public void setClientId(String clientId)
   {
      this.clientId = clientId;
   }

   /**
    * Get a list of current orders (all order ids, client ids and dates of
    * orders) and return it as an HTML table
    *
    * @param oucu the user's oucu
    * @param password the user's password
    * @return HTML table of current orders for the identified user
    */
   public String ordersGet(String oucu, String password)
   {
      Orders oclient = new Orders();
      String response = oclient.get_application_json(String.class, oucu, password);

      System.out.println("orders get Response was " + response);

      JsonObject jo = Utilities.getAsJsonObject(response);
      String status = jo.getString("status");
      JsonArray data = jo.getJsonArray("data");

      System.out.println("Status was " + jo.getString("status"));
      System.out.println("Data was " + jo.getJsonArray("data"));

      String result;

      if (Utilities.statusOkay(status))
      {
         result = Utilities.prettyOrders(data);
      }
      else
      {
         System.out.println("Message was " + jo.getString("message"));

         result = jo.getString("message");
      }

      return result;
   }

   /**
    * Get a list of current orders (all order ids, client ids and dates of
    * orders)
    *
    * @param oucu the user's oucu
    * @param password the user's password
    * @return an HTML table of the user's current orders
    */
   public String orderItemsGet(String oucu, String password)
   {
      return "EMA Q1 (f) orderItemsGet - To be completed";
   }

   /**
    * POST a new order - oucu and password from login bean, Client id from order
    * form.
    *
    * @param oucu the user's oucu
    * @param password the user's password
    * @return a JSON status string from the MegaMax server
    */
   public String orderPost(String oucu, String password)
   {
      return "EMA Q1(d) orderPost method in OrderBean - To be completed";
   }

   /**
    * POST items to an order - oucu and password from login bean, other
    * parameters from the bean state. Before calling this method, an order must
    * have been created already and items must have been added to the order.
    *
    * @param oucu the user's oucu
    * @param password the user's password
    * @return a JSON status string from the MegaMax server
    */
   public String orderItemsPost(String oucu, String password)
   {
      return "EMA Q1(e) orderItemsPost method in OrderBean - To be completed";
   }

}
