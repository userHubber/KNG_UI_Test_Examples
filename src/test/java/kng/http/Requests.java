package kng.http;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;

public class Requests extends KngHttpClient {

    private final Header userAgent = new BasicHeader("User-Agent", "UI KNG Test Robot");
//

    private HttpGet httpGet(String url, Map<String, String> headers) {
        HttpGet httpGet = new HttpGet(url);

        List<Header> $headers = new ArrayList<>();
        $headers.add(userAgent);

        Iterator<Map.Entry<String, String>> headersIterator = headers.entrySet().iterator();
        while (headersIterator.hasNext()) {
            Map.Entry<String, String> entry = headersIterator.next();
            $headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
        }

        $headers.forEach((header) -> {
            httpGet.setHeader(header);
        });
        return httpGet;
    }
//

    protected HttpResponse responseGet(String url, Map<String, String> headers) {
        try {
            return super.httpClient().execute(this.httpGet(url, headers));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
//----------------------------------------------------------------------------

    private HttpPost httpPost(String url, Map<String, String> headers, Map<String, String> params) {
        HttpPost httpPost = new HttpPost(url);
        List<Header> $headers = new ArrayList<>();
        $headers.add(userAgent);

        List<NameValuePair> $params = new ArrayList<>();
        Iterator<Map.Entry<String, String>> headersIterator = headers.entrySet().iterator();
        Iterator<Map.Entry<String, String>> paramsIterator = params.entrySet().iterator();

        while (headersIterator.hasNext()) {
            Map.Entry<String, String> entry = headersIterator.next();
            $headers.add(new BasicHeader(entry.getKey(), entry.getValue()));
        }

        $headers.forEach((header) -> {
            httpPost.setHeader(header);
        });

        while (paramsIterator.hasNext()) {
            Map.Entry<String, String> entry = paramsIterator.next();
            $params.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        try {
            httpPost.setEntity(new UrlEncodedFormEntity($params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return httpPost;
    }
//    

    protected HttpResponse responsePost(String url, Map<String, String> headers, Map<String, String> params) {
        try {
            return super.httpClient().execute(this.httpPost(url, headers, params));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
