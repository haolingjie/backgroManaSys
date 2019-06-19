package com.platform.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.impl.cookie.BasicClientCookie;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author weidong Http工具
 */
public class HttpClientUtil {

    private static final String EMPTY_STR = "";
    private static CookieStore cookieStore = null;
    private static String charset = "UTF-8";
    private static PoolingHttpClientConnectionManager cm;

    private HttpClientUtil() {
    }

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    public static String doGet(String url, Map<String, String> param, Map<String, String> headers) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);
            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);
            // 设置请求头
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpGet.addHeader(key, headers.get(key));
                }
            }

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                if (httpclient != null) {
                    httpclient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resultString;
    }


    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String sendGet(String url, String param) {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                // System.supplier.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
        } catch (Exception e) {
            // System.supplier.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }
        // 使用finally块来关闭输入流
        finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            URL urlentity = new URL(url);
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    public static String doPostJson(String url, String json, Map<String, String> headers) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 设置请求头
            if (headers != null) {
                for (String key : headers.keySet()) {
                    httpPost.addHeader(key, headers.get(key));
                }
            }
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return resultString;
    }

    private static void init() {
        if (cm == null) {
            cm = new PoolingHttpClientConnectionManager();
            cm.setMaxTotal(50);// 整个连接池最大连接数
            cm.setDefaultMaxPerRoute(5);// 每路由最大连接数，默认值是2
        }
    }

    /**
     * 通过连接池获取HttpClient
     *
     * @return
     */
    private static CloseableHttpClient buildHttpClient() {
        init();
        HttpClientBuilder builder = HttpClients.custom().setConnectionManager(cm);
        return cookieStore != null ? builder.setDefaultCookieStore(cookieStore).build() : builder.build();
    }

    /**
     * @param url
     * @return
     */
    public static HttpResult get(String url) {
        HttpGet httpGet = new HttpGet(url);
        return getHttpResult(httpGet, url);
    }

    @SuppressWarnings("rawtypes")
    public static HttpResult get(String url, Map<String, Object> params) throws URISyntaxException {
        StringBuilder strBuilder = new StringBuilder();
        for (Entry entry : params.entrySet()) {
            strBuilder.append(entry.getKey());
            strBuilder.append("=");
            strBuilder.append(entry.getValue());
            strBuilder.append("&");
        }
        String paraStr = strBuilder.toString();
        paraStr = paraStr.endsWith("&") ? paraStr.substring(0, paraStr.length() - 1) : paraStr;
        String targetUrl = url + "?" + paraStr;
        return get(targetUrl);
    }

    public static HttpResult get(String url, Map<String, Object> headers, Map<String, Object> params)
            throws URISyntaxException {
        URIBuilder ub = new URIBuilder();
        ub.setPath(url);

        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        ub.setParameters(pairs);

        HttpGet httpGet = new HttpGet(ub.build());
        for (Entry<String, Object> param : headers.entrySet()) {
            httpGet.addHeader(param.getKey(), String.valueOf(param.getValue()));
        }
        return getHttpResult(httpGet, url);
    }

    public static HttpResult post(String url) {
        HttpPost httpPost = new HttpPost(url);
        return getHttpResult(httpPost, url);
    }

    public static HttpResult post(String url, Map<String, Object> params) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
        httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
        return getHttpResult(httpPost, url);
    }

    @SuppressWarnings("deprecation")
    public static HttpResult post(String url, Map<String, Object> headers, Map<String, Object> params)
            throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        boolean isJsonMime = false;
        String headerValue;
        for (Entry<String, Object> param : headers.entrySet()) {
            headerValue = String.valueOf(param.getValue());
            httpPost.addHeader(param.getKey(), headerValue);
            if (!isJsonMime) {
                isJsonMime = headerValue.contains("application/json");
            }
        }

        if (isJsonMime) {
            httpPost.setEntity(new StringEntity(JSON.toJSONString(params), "application/json", charset));
        } else {
            ArrayList<NameValuePair> pairs = covertParams2NVPS(params);
            httpPost.setEntity(new UrlEncodedFormEntity(pairs, charset));
        }

        return getHttpResult(httpPost, url);
    }

    @SuppressWarnings("deprecation")
    public static HttpResult postJson(String url, String json) throws UnsupportedEncodingException {
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json; charset=UTF-8");
        httpPost.setEntity(new StringEntity(json, "application/json", charset));
        return getHttpResult(httpPost, url);
    }

    private static ArrayList<NameValuePair> covertParams2NVPS(Map<String, Object> params) {
        ArrayList<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Entry<String, Object> param : params.entrySet()) {
            pairs.add(new BasicNameValuePair(param.getKey(), String.valueOf(param.getValue())));
        }

        return pairs;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    private static HttpResult getHttpResult(HttpRequestBase request, String url) {
        HttpResult dataResp = new HttpResult();
        CloseableHttpClient httpClient = buildHttpClient();
        try {
            CloseableHttpResponse response = httpClient.execute(request);
            setCookieStore(response, url);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity);
                response.close();
                return dataResp.setHeader(response.getAllHeaders()).setContent(result);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 处理Http请求
     *
     * @param request
     * @return
     */
    @SuppressWarnings("unused")
    private static String getResult(HttpRequestBase request, String url) {
        return getHttpResult(request, url).getContent();
    }

    private static void setCookieStore(HttpResponse httpResponse, String url) {
        cookieStore = new BasicCookieStore();
        Header firstHead = httpResponse.getFirstHeader("Set-Cookie");
        if (firstHead != null) {
            String setCookie = firstHead.getValue();
            if (setCookie.contains("JSESSIONID")) {
                String JSESSIONID = setCookie.substring("JSESSIONID=".length(), setCookie.indexOf(";"));
                BasicClientCookie cookie = new BasicClientCookie("JSESSIONID", JSESSIONID);
                cookie.setVersion(0);
                cookie.setDomain(getDomain(url));
                cookie.setPath("/");
                cookieStore.addCookie(cookie);
            }
        }
    }

    private static String getDomain(String url) {
        Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
        Matcher m = p.matcher(url);
        if (m.find()) {
            return m.group();
        }
        return EMPTY_STR;
    }
}
