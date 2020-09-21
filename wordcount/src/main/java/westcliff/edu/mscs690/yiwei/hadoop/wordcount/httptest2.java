package westcliff.edu.mscs690.yiwei.hadoop.wordcount;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yiweizheng
 */
public class httptest2 {
	
    static String  filePath = "/Users/yiweizheng/Desktop/txt";
    public static void main( String[] args ) {
    	
        List<String> uris = new ArrayList<>();
        uris.add("https://www.google.com/");
        uris.add("https://www.cnn.com/");
        uris.add("http://www.baidu.com/");

        for (String uri: uris) {
            String content = crawlPage(uri);
           // String filename = uri.substring(uri.indexOf("//"),uri.indexOf("com"));
            String filename = uri.substring(uri.indexOf("//"),uri.indexOf("com"));
            filename = filename.replaceAll("\\/:\\*\\?\"\\<\\>\\|", ".")+ ".txt";

            saveLocal(content,filename);
        }


    }

    /**
     * Save content to local
     * @param content
     * @param filename
     */
    public static void saveLocal(String content,String filename){
        DataOutputStream out = null;
        File file = new File(filePath);
        if(!file.exists())
            file.mkdirs();
        try {
            out = new DataOutputStream(new FileOutputStream(
                    new File(filePath +filename )));
            out.write(content.getBytes());
            out.flush();
            out.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * get the content from webpage
     * @param uri
     * @return content
     */
    public static String crawlPage(String uri){
        String content = "";
        HttpClient client = new HttpClient();
        //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port);
        HttpMethod method=new GetMethod(uri);
        try {
            client.executeMethod(method);
            //System.out.println(method.getStatusLine());
            //print the return info
            //content = method.getResponseBodyAsString();
            content = method.getResponseBodyAsString();
            System.out.println(content);

        }catch (IOException e){
            e.printStackTrace();
        }finally {
            //release the connection
            method.releaseConnection();
        }
        return content;
    }
}