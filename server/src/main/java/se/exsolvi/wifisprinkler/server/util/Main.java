package se.exsolvi.wifisprinkler.server.util;

public class Main {
    
    private static String INPUT_TEMPLATE = "{\n" +
            "  \"pathtemplate\": \"$context.resourcePath\",\n" +
            "  \"method\": \"$context.httpMethod\",\n" +
            "  \"requestbody\": $input.json('$'),\n" +
            "      #foreach($elem in $input.params().keySet())\n" +
            "        \"$elem\": {\n" +
            "            #foreach($innerElem in $input.params().get($elem).keySet())\n" +
            "        \"$innerElem\": \"$util.urlDecode($input.params().get($elem).get($innerElem))\"#if($foreach.hasNext),#end\n" +
            "      #end\n" +
            "        }#if($foreach.hasNext),#end\n" +
            "      #end\n" +
            "}";

  public static void main(String[] args) {
      System.out.println(INPUT_TEMPLATE);
    
}
}