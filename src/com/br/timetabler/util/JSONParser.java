package com.br.timetabler.util;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;
 
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;
 
import android.util.Log;
 
			public class JSONParser {
			 
			    static InputStream is = null;
			    static JSONObject jObj = null;
			    static String json = "";
			 
			    // constructor
			    public JSONParser() {
			 
			    }
			 
			    public JSONObject getJSONFromUrl(String url, List<NameValuePair> params) {
			 
			        // Making HTTP request
			    	Log.i("url ", url);
			        try {
			            // defaultHttpClient
			            DefaultHttpClient httpClient = new DefaultHttpClient();
			            HttpPost httpPost = new HttpPost(url);
			            if (params != null) {
			            httpPost.setEntity(new UrlEncodedFormEntity(params));
			            }else
			            	Log.i("Null params", url);
			            
			            HttpResponse httpResponse = httpClient.execute(httpPost);
			            HttpEntity httpEntity = httpResponse.getEntity();
			            is = httpEntity.getContent();
			 
			        } catch (UnsupportedEncodingException e) {
			            e.printStackTrace();
			        } catch (ClientProtocolException e) {
			            e.printStackTrace();
			        } catch (IOException e) {
			            e.printStackTrace();
			        }
			      if (is!=null){
			        try {
			            BufferedReader reader = new BufferedReader(new InputStreamReader( is, "iso-8859-1"), 8);
			            StringBuilder sb = new StringBuilder();
			            String line = null;
			            while ((line = reader.readLine()) != null) {
			                sb.append(line + "\n");
			            }
			            is.close();
			            json = sb.toString();
			            Log.e("JSON", json);
			        } catch (Exception e) {
			            Log.e("Buffer Error", "Error converting result " + e.toString());
			        }
			  }
			        // try parse the string to a JSON object
			  if (is!=null){
			        try {
			            jObj = new JSONObject(json);
			        } catch (JSONException e) {
			            Log.e("JSON Parser", "Error parsing data " + e.toString());
			        }
			  }
			        // return JSON String
			        return jObj;
			 
			    }
			}