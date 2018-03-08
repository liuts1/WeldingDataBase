package com.gy.utils.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.apache.commons.httpclient.protocol.Protocol;
import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.gy.utils.StringUtils;

public class HttpClientUtil {
	private final static Logger log = Logger.getLogger(HttpClientUtil.class);
	/**请求类型：HTTPS*/
	private static final String REQ_HTTPS = "https";
	/**HTTPS请求端口*/
	private static final Integer REQ_HTTPS_PORT = 443;
	
	/**请求超时时间_30秒*/
	private static final Integer TIME_OUT = 30 * 1000;
	
	/**
	 * <p>HttpClient_post请求</p>
	 * @param url
	 * @param params
	 * @return
	 * @throws Exception
	 */
	public static String getDataByHttpClient(String host,String url,Map<String,String> params)throws Exception{
		Protocol https = new Protocol(REQ_HTTPS,new MySecureProtocolSocketFactory(), REQ_HTTPS_PORT);
		Protocol.registerProtocol(REQ_HTTPS, https);
		HttpClient httpclient = null;
		PostMethod method = null;
		String result = "";
		try {
			httpclient = new HttpClient();
			httpclient.getHostConfiguration().setHost(host,REQ_HTTPS_PORT,https);
			method = new PostMethod(url);
			method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
			method.setRequestHeader("User-Agent", "Mozilla/4.0 (compatible;MSIE 6.0; Windows NT 5.1; SV1; .NET CLR 1.1.4322)");
			
			if (params != null && !params.isEmpty()) {
				int len = params.size();
				org.apache.commons.httpclient.NameValuePair[] np = new org.apache.commons.httpclient.NameValuePair[len];
				int index = 0;
				org.apache.commons.httpclient.NameValuePair itemParam = null;
				for(Map.Entry<String, String> map : params.entrySet()){
					itemParam = new org.apache.commons.httpclient.NameValuePair();
					itemParam.setName(map.getKey());
					itemParam.setValue(map.getValue());
					np[index] = itemParam;
					index++;
				}
				method.setRequestBody(np);
			}
			httpclient.executeMethod(method);
			BufferedReader bs = new BufferedReader(new InputStreamReader(method.getResponseBodyAsStream(), "utf-8"));
			String line = "";
			while ((line = bs.readLine()) != null) {
				result += line;
			}
			bs.close();
		} catch (Exception ex) {
			log.error("httpclient error,error info:" + ex.getMessage());
			throw ex;
		} finally {
			if (method != null) {
				method.releaseConnection();
			}
		}
		return result;
	}

    /**
     * httpClient_post表单请求
     * @param serverUrl 请求url
     * @param params 请求参数
     * @return 返回值
     * @throws Exception
     */
    public static String getDataByHttpClientFormPost(String serverUrl,Map<String,String> params)throws Exception{
		log.debug("oaapi=:" + serverUrl);
        if (!StringUtils.isConnect(serverUrl)) {
            throw new IllegalArgumentException("请求地址错误");
        }
        HttpURLConnection conn = null;
        OutputStream os = null;
        InputStream is = null;
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = null;
        try {
            URL url = new URL(serverUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded;charset=UTF-8");
            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
            // 设置超时时间 modify by zhaoxun 2016-11-16 Timeout=10 minute
            conn.setConnectTimeout(600 * 1000);
            // 设置请求参数
            StringBuffer param = new StringBuffer();
            if (params != null) {
                for (Map.Entry<String, String> paramMap : params.entrySet()) {
                    param.append("&").append(paramMap.getKey()).append("=").append(paramMap.getValue());
                }
            }
            // 删除第一个字符
            if (param.length() > 0) {
                param.deleteCharAt(0);
            }
            os = conn.getOutputStream();
            os.write(param.toString().getBytes("UTF-8"));
            os.flush();
            conn.connect();
            int code = conn.getResponseCode();
            if (code != 200) {
                return "";
            }
            is = conn.getInputStream();
            bis = new BufferedInputStream(is);
            baos = new ByteArrayOutputStream();
            byte[] temp = new byte[10240];
            int bytesRead= -1;
            while ((bytesRead = bis.read(temp, 0, temp.length)) > -1) {
                baos.write(temp,0,bytesRead);
            }
            String str = new String(baos.toByteArray(), "utf-8").trim();
            return str;
        } catch (Exception e) {
			log.error("HttpClientUtil请求失败！" + e.getMessage());
        } finally {
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    log.error("HttpClientUtil流关闭失败！");
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
					log.error("HttpClientUtil流关闭失败！" + e.getMessage());
                }
            }
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
					log.error("HttpClientUtil流关闭失败！" + e.getMessage());
                }
            }
            if (baos != null) {
                try {
                    baos.close();
                } catch (IOException e) {
					log.error("HttpClientUtil流关闭失败！" + e.getMessage());
                }
            }
        }
        return "";
    }
	
	
	public static String getDataFromHttpUrl(String url) {
		if(!StringUtils.isConnect(url)){
			return "";
		}
		// 构造HttpClient的实例
		HttpClient httpClient = new HttpClient();
		// 创建GET方法的实例
		GetMethod getMethod = new GetMethod(url);

		// 创建Post方法的实例
		// PostMethod method = new PostMethod("http://java.sun.com");
		// 使用系统提供的默认的恢复策略
		getMethod.getParams().setParameter(HttpMethodParams.RETRY_HANDLER,
				new DefaultHttpMethodRetryHandler());
		String str = "";
		try {
			// 执行getMethod
			int statusCode = httpClient.executeMethod(getMethod);
			if (statusCode != HttpStatus.SC_OK) {
				System.err.println("Method failed: "
						+ getMethod.getStatusLine()); // 打印服务器返回的状态
			}
			// 读取内容
			byte[] responseBody = getMethod.getResponseBody();
			// 处理内容，并转码
			str = new String(responseBody, "UTF-8");
		} catch (HttpException e) {
			// 发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error("Please check your provided http address!" + e.getMessage());
		} catch (IOException e) {
			// 发生网络异常
			log.error(e.getMessage());
		} finally {
			// 释放连接
			getMethod.releaseConnection();
		}
		return str;
	}

    /**
     * 上传文件
     * @param url
     * @param targetFile
     * @param params
     * @return
     */
    public static JSONObject postFile(String url, File targetFile, String fileName, Map<String, String> params) {
        HttpClient client = new HttpClient();
        PostMethod filePost = new PostMethod(url);
        try
        {
            // 设置参数
            List<Part> partList = new ArrayList<Part>();
            partList.add(new FilePart(fileName, targetFile));
            if (params != null) {
                for(Map.Entry<String, String> map : params.entrySet()) {
                    partList.add(new StringPart( map.getKey() , map.getValue()));
                }
            }
            Part[] parts = new  Part[partList.size()];
            partList.toArray(parts);
            filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
            client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
            int status = client.executeMethod(filePost);
            if (status == HttpStatus.SC_OK){
                byte[] responseBody =  filePost.getResponseBody();
                JSONObject json = JSONObject.parseObject(new String(responseBody, "UTF-8")) ;
                return json;
            } else {
				log.error("上传文件失败！");
            }
        } catch (Exception ex) {
			log.error("上传文件失败！" + ex.getMessage());
        } finally {
            filePost.releaseConnection();
        }
        return null;
    }

	public static String getDataByHttpClient(String url,String xml)throws ClientProtocolException,IOException{
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url);
		//设置请求和传输超时  modify by zhaoxun 2016-11-16 Timeout=10 minute
		RequestConfig config = RequestConfig.custom().setSocketTimeout(600000).setConnectTimeout(600000).build();
		StringEntity strEntity = new StringEntity(xml,"UTF-8");
		post.setEntity(strEntity);
		post.setConfig(config);
		CloseableHttpResponse response = null;
		try {
			response = httpClient.execute(post);
			HttpEntity entity = response.getEntity();
			if(null == entity){
				return null;
			}
			String result = EntityUtils.toString(entity,"UTF-8");
//			if(!StringUtils.checkStr(result)){
//				return null;
//			}
			return result;
		} catch (ClientProtocolException e) {
			log.error("httpClient异常：" + e.getMessage());
		} catch (IOException e) {
			log.error("httpClient异常：" + e.getMessage());
		}finally{
			if(null != response){
				try {
					response.close();
				} catch (IOException e) {
					log.error("httpClient异常：" + e.getMessage());
				}
			}
			if(null != httpClient){
				try {
					httpClient.close();
				} catch (IOException e) {
					log.error("httpClient异常：" + e.getMessage());
				}
			}
		}
		return null;
	}

    /**
     * oa放款、代扣接口
     * @param url
     * @param xml
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
    public static JSONObject getDataByHttpClientUK(String url,String xml) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        //设置请求和传输超时
        RequestConfig config = RequestConfig.custom().setSocketTimeout(20000).setConnectTimeout(20000).build();
        StringEntity strEntity = new StringEntity(xml,"UTF-8");
        post.setEntity(strEntity);
        post.setConfig(config);
        CloseableHttpResponse response = null;
        JSONObject jsonObject = new JSONObject();
        try {
            response = httpClient.execute(post);
            // http返回code
            StatusLine statusLine = response.getStatusLine();
            jsonObject.put("httpStatusCode", statusLine.getStatusCode());
            HttpEntity entity = response.getEntity();
            if(null == entity){
                return jsonObject;
            }
            String result = EntityUtils.toString(entity,"UTF-8");
            jsonObject.put("result", result);
            return jsonObject;
        } catch (ClientProtocolException e) {
			log.error("httpClient异常：" + e.getMessage());
        } catch (IOException e) {
			log.error("httpClient异常：" + e.getMessage());
        }finally{
            if(null != response){
                try {
                    response.close();
                } catch (IOException e) {
					log.error("httpClient异常：" + e.getMessage());
                }
            }
            if(null != httpClient){
                try {
                    httpClient.close();
                } catch (IOException e) {
					log.error("httpClient异常：" + e.getMessage());
                }
            }
        }
        return jsonObject;
    }

	 /**
     * <p>HttpClient_get请求</p>
     * <p>屏蔽Https验证</p>
     * @param url
     * @param params
     * @return
     * @throws Exception
     */
    public static String getHttpsDataByGetMethod(String url,Map<String,String> params)throws Exception{
        Protocol https = new Protocol("https",new MySecureProtocolSocketFactory(), 443);
        Protocol.registerProtocol("https", https);
        HttpClient httpclient = null;
        GetMethod method = null;
        String result = "";
		try {
            httpclient = new HttpClient();
            method = new GetMethod(url);
            //method.setRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=utf-8");
            method.setRequestHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
            //method.setRequestHeader("Accept-Charset", "GBK,utf-8;q=0.7,*;q=0.3");
            //method.setRequestHeader("Accept-Encoding", "gzip,deflate,sdch");
            //method.setRequestHeader("Accept-Language", "zh-CN,zh;q=0.8");
            method.setRequestHeader("Connection", "keep-alive");
            //method.setRequestHeader("Host", "kyfw.12306.cn");
            method.setRequestHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/21.0.1180.89 Safari/537.1");

            //method.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
            if (params != null && !params.isEmpty()) {
                int len = params.size();
                org.apache.commons.httpclient.NameValuePair[] np = new org.apache.commons.httpclient.NameValuePair[len];
                int index = 0;
                org.apache.commons.httpclient.NameValuePair itemParam = null;
                for(Map.Entry<String, String> map : params.entrySet()){
                    itemParam = new org.apache.commons.httpclient.NameValuePair();
                    itemParam.setName(map.getKey());
                    itemParam.setValue(map.getValue());
                    np[index] = itemParam;
                    index++;
                }
                method.setQueryString(np);
            }
            int cnt = httpclient.executeMethod(method);
            if (200 == cnt) {
                return  method.getResponseBodyAsString();
            }
        } catch (Exception ex) {
            //ex.printStackTrace();
            //result = "error\tcrm error " + ex.toString();
        } finally {
            if (method != null) {
                method.releaseConnection();
            }
        }
        return "";
    }
    
    /**
     * <p>百度请求</p>
     * @param urls
     * @return
     * @throws Exception
     *//*
    public static String getBaidu(String urls) throws Exception{
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        HttpURLConnection connection = null;
        String strRead = null;
        try {
            URL url = new URL(urls);
            connection = (HttpURLConnection) url
                    .openConnection();
            //设置超时单位毫秒
            connection.setConnectTimeout(1000);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("apikey",PropertiesUtils.getProperty("APIStore_key"));
            connection.connect();
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            result = sbf.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                connection.disconnect();
            }
        }
        return result;
    }*/


}
