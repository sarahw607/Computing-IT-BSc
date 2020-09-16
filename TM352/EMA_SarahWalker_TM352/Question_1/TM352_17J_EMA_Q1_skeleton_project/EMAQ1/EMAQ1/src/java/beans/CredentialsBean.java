/**
 * Backing bean for user's oucu and password, which is needed for
 * each interaction with the MegaMax server. The class includes a
 * method to call the GET method on the widgets REST resource.
 */
package beans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.json.JsonObject;
import rest.Widgets;
import util.Utilities;

/**
 * A backing bean to remember the customer's name (oucu) and password over
 * multiple requests within the same session.
 *
 * @author TM352
 */
@Named
@SessionScoped
public class CredentialsBean implements Serializable
{
   private String name;
   private String pwd;

   public CredentialsBean()
   {
   }

   public String getName()
   {
      return name;
   }

   public void setName(String name)
   {
      this.name = name;
   }

   public String getPwd()
   {
      return pwd;
   }

   public void setPwd(String pwd)
   {
      this.pwd = pwd;
   }

   /**
    * Compute a hexadecimal colour string based on an input string
    *
    * @param str an arbitrary string
    * @return a colour code for use in a web page
    */
   public String computeColour(String str)
   {
      if (str == null)
      {
         return "#000000";
      }

      String temp = String.format("#%X", str.hashCode());
      return temp.substring(0, 7);
   }

   /**
    * Return a string-based HTML representation of a table containing
    * information about widgets on sale at MegaMax. Note, we assume that name
    * and pwd have been set already.
    *
    * This method constructs a rest-client object and uses it to call
    * the MegaMax API. The string response is then converted to a JsonObject and
    * interpreted based on our knowledge of the MegaMax API, using the utility
    * method prettyWidgets().
    *
    * @return a string containing an HTML table of widgets that we can output as
    * part of a web page *
    */
   public String widgetsGet()
   {
      //Construct a rest client to access the Widgets resource
      Widgets wclient = new Widgets();

      //Get a response from the API for the GET method as a String
      //The user's name and password are assumed to have already been set, so 
      //we just access these values here when calling the API.
      String response = wclient.get_application_json(String.class, name, pwd);

      //Convert the response to a JsonObject to allow easier manipulation
      JsonObject jo = Utilities.getAsJsonObject(response);

      String status = jo.getString("status");

      //Note that any println statements go the GlassFish Server console in NetBeans
      System.out.println("Status was " + jo.getString("status"));

      String result;

      //See if there will be data to process
      if (Utilities.statusOkay(status))
      {
         JsonArray data = jo.getJsonArray("data");
         System.out.println("Data was " + jo.getJsonArray("data"));
         result = Utilities.prettyWidgets(data);
      }
      else //no data to process
      {
         System.out.println("Message was " + jo.getString("message"));

         //In this case there is no data to process; we just return the "message" value
         //from the MegaMax server telling us what went wrong
         result = jo.getString("message");
      }

      //It is good practice to close the rest client, to release resources
      wclient.close();

      return result;
   }
}
