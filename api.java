
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.xml.crypto.Data;
import java.net.URI;
import java.util.Scanner;



public class api {

    private String getinfoSKU(String sku){
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.wegmans.io/products/" + sku + "?api-version=2018-10-18");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Cache-Control", "no-cache");
            request.setHeader("Subscription-Key", "4d05540fdc7a46acb024ca5313c6a80b");


            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
                convertjson(EntityUtils.toString(entity));
                return EntityUtils.toString(entity);
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    private void getinfoSEARCH(String search){
        HttpClient httpclient = HttpClients.createDefault();

        try
        {
            URIBuilder builder = new URIBuilder("https://api.wegmans.io/products/search?query=" + search + "&results=100&page=1&api-version=2018-10-18");

            URI uri = builder.build();
            HttpGet request = new HttpGet(uri);
            request.setHeader("Cache-Control", "no-cache");
            request.setHeader("Subscription-Key", "4d05540fdc7a46acb024ca5313c6a80b");


            HttpResponse response = httpclient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null)
            {
                System.out.println(EntityUtils.toString(entity));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void convertjson(String sku){
        Data data = new Gson().fromJson(getinfoSKU(sku), Data.class);
        System.out.println(data);
    }

    public static void main(String[] args) {
        api func = new api();
        Scanner input = new Scanner(System.in);
        System.out.print("Insert sku: ");
        String sku = Integer.toString(input.nextInt());
        func.getinfoSKU(sku);
        func.convertjson(sku);
        //System.out.print("Insert product: ");
        //String search = input.next();
        //func.getinfoSEARCH(search);
    }

}
