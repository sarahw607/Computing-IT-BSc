/**
 * JSON processing utilities, supported by classes in the javax.json package
 * as part of Java EE. There are also many third party libraries that you can
 * use, such as google-gson.
 *
 * The methods prettyOrders and prettyWidgets illustrate how to build and HTML
 * table from a JsonArray.
 *
 */
package util;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonValue;

/**
 * @author tm352
 */
public class Utilities
{
   /**
    * Convert a string representation of a JSON object into a JsonObject.
    *
    * @param stringjson A string representation of a JSON response
    * @return stringjson converted to javax.json.JsonObject
    */
   public static JsonObject getAsJsonObject(String stringjson)
   {
      return Json.createReader(new StringReader(stringjson)).readObject();
   }

   /**
    * A utility method to convert a JsonArray to an HTML table. We need to know
    * what the object names are in the array, so we can get their values. One
    * way of finding out is to obtain the keySet()
    *
    * @param widgets a JsonArray containing JsonObjects representing rows of the
    * Widgets table on the MegaMax server.
    *
    * @return HTML table of widgets details, as a string. This table loads
    * images of widgets using their URLS.
    */
   public static String prettyWidgets(JsonArray widgets)
   {
      //opening tag for table - for efficiency we use a StringBuilder      
      StringBuilder temp = new StringBuilder("<table>");

      //formatting strings for the header of the table and rows. 
      //Each %s is to replaced by a string relating to widgets
      String header = "<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>";
      String row;

      //row= "<tr><td>%s</td><td>%s</td><td>%s</td></tr>";//plain text row format
      //embedded image version of row format
      row = "<tr><td>%s</td><td><img src='%s' alt='Widget image'></td><td>%s</td><td>%s</td></tr>";

      //add the header to the table string
      temp.append((String.format(header, "id", "url","description", "pence")));

      //iterate over the widgets array, formatting each object in the array
      //as a row in our html table
      for (int i = 0; i < widgets.size(); i++)
      {
         //each item in the array is a JsonObject. Get the next object.
         JsonObject jo = (JsonObject) widgets.get(i);

         //print out the names of values in this object
         //System.out.println("keys " + jo.keySet());
         //Each JsonObject represents a row of the table and contains some JsonValues.
         //We get the values, using their known names, and convert them to strings.

         String id = jo.getString("id");
         String url = jo.getString("url");
         String descrip = jo.getString("description");
         String pence = jo.getString("pence_price");

         //And now we can append the next row to the html table
         temp.append(String.format(row, id, url, descrip, pence));
      }

      //finally we end the table and return the result as a string
      temp.append("</table>");

      return temp.toString();
   }

   /**
    * Prettify Orders data
    *
    * @param orders JsonArray of orders
    * @return HTML table of orders data, as a String
    */
   public static String prettyOrders(JsonArray orders)
   {
      StringBuilder temp = new StringBuilder("<table>");

      String header = "<tr><th>%s</th><th>%s</th><th>%s</th><th>%s</th><th>%s</th></tr>";
      String row = "<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>";

      temp.append(String.format(header, "order id", "client_id", "date", "latitude", "longitude"));

      System.out.println(temp.toString());

      for (int i = 0; i < orders.size(); i++)
      {
         JsonObject jo = (JsonObject) orders.get(i);

         System.out.println("keys " + jo.keySet());

         String id = jo.getString("id");
         String client_id = jo.getString("client_id");
         String date = jo.getString("date");
         String lat = jo.getString("latitude");
         String lon = jo.getString("longitude");

         temp.append(String.format(row, id, client_id, date, lat, lon));

         System.out.println(String.format(row, id, client_id, date, lat, lon));
      }

      temp.append("</table>");

      return temp.toString();
   }

   /**
    * JsonObjects have many methods to tell you about them. A JSON value is one
    * of the following: an object (JsonObject), an array (JsonArray), a number
    * (JsonNumber), a string (JsonString), true (JsonValue.TRUE), false
    * (JsonValue.FALSE), or null (JsonValue.NULL). These are all immutable.
    *
    * @param obj JsonObject you want to know more about
    *
    */
   public static void tellMeAbout(JsonObject obj)
   {
      System.out.println("\nAbout your object ......................................");

      //Iterate over the keys (names)      
      for (String name : obj.keySet())
      {
         //inside a JsonObject, the value is a JsonValue
         JsonValue jv = obj.get(name);
         System.out.printf("%-20s is type %-10s with value %s%n", name, jv.getValueType(), jv);
      }
   }

   /**
    * Iterate over a JSON array, printing out information about it.
    *
    * @param ar the JsonArray you want to know more about
    */
   public static void tellMeAbout(JsonArray ar)
   {
      System.out.println("\nAbout your array ......................................");

      if (ar.size() == 0)
      {
         System.out.println("The array was empty.");
      }
      else
      {
         //Iterate over the values    
         for (int i = 0; i < ar.size(); i++)
         {
            JsonValue jv = (JsonValue) ar.get(i);
            System.out.printf("At index %s is %8s  %-10s%n", i, jv.getValueType(), jv);
         }
      }
   }

   /**
    * Check for success or failure of MegaMax API call. Status "error" indicates
    * an issue with completing the API operation, e.g. because an invalid user
    * name or password was provided. Status "failed" is used when the request is
    * valid but inappropriate for the data on the server, e.g. because a
    * provided client_id did not match one known by the server.
    *
    * @param status a String representing the status value in a response from
    * the MegaMax API
    * @return true if the status contains 'error' or 'fail', otherwise false.
    */
   public static boolean statusOkay(String status)
   {
      if (status == null)
      {
         return false;
      }

      //status includes quotes, so use 'contains'
      return !(status.contains("fail") || status.contains("error"));
   }

}
