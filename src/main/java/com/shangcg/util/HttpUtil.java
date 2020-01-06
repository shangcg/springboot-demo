package com.shangcg.util;

/**
 * @version v1.0
 * @Description: TODO
 * @Author: shangcg
 * @Date: 2020/1/6
 */

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@SuppressWarnings("all")
public class HttpUtil {
    public static void main(String args[]) throws Exception {
        new HttpUtil().TestRiQingAPI_SaleOrder();
    }


    public static void TestRiQingAPI_SaleOrder() throws Exception {

        String postData = getJson();
        //String url = "https://*****";
        String url = "http://localhost:8080/showUser?userId=55";
        HttpURLConnection conn = null;
        OutputStream out = null;
        String rsp = null;
        byte[] byteArray = postData.getBytes("utf-8");
        try {
            URL uri = new URL(url);
            conn = (HttpURLConnection) uri.openConnection();
            //忽略证书验证--Begin
//            conn.setHostnameVerifier(new TrustAnyHostnameVerifier());
            //忽略证书验证--End
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Host", uri.getHost());
            conn.setRequestProperty("Content-Type", "application/json");
            out = conn.getOutputStream();
            out.write(byteArray);
            out.close();
            if(conn.getResponseCode()==200) {
                rsp = getStreamAsString(conn.getInputStream(), "utf-8");
            }else {
                rsp = getStreamAsString(conn.getErrorStream(), "utf-8");
            }

            System.out.println(rsp);

        } catch (Exception e) {
            if(null!=out)
                out.close();
            e.printStackTrace();

        }

    }

    /**
     * getJson
     *
     */
    private static String getJson() {
        return "{" + "\"name\"" + ":" + "\"robo_blogs_zh123\"" + "}";
    }

    private static String getStreamAsString(InputStream stream, String charset) throws IOException {
        try {
            Reader reader = new InputStreamReader(stream, charset);
            StringBuilder response = new StringBuilder();

            final char[] buff = new char[1024];
            int read = 0;
            while ((read = reader.read(buff)) > 0) {
                response.append(buff, 0, read);
            }

            return response.toString();
        } finally {
            if (stream != null) {
                stream.close();
            }
        }
    }
}


//定制Verifier
class TrustAnyHostnameVerifier implements HostnameVerifier {
    public boolean verify(String hostname, SSLSession session) {
        return true;
    }
}
