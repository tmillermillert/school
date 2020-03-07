package ser321.assign6.mtmille5.client;

import ser321.assign6.mtmille5.server.*;
import java.net.*;
import java.io.*;
import java.util.ArrayList;
import org.json.JSONObject;
import org.json.JSONArray;

/**
 * Copyright 2016 Tim Lindquist, Marcus Miller
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * A class for client-server connections with a threaded server.
 * The student collection client proxy implements the server methods
 * by marshalling/unmarshalling parameters and results and using a TCP
 * connection to request the method be executed on the server.
 * Byte arrays are used for communication to support multiple langs.
 *
 * @author Tim Lindquist ASU Polytechnic Department of Engineering
 * @author Marcus Miller mtmille5@asu.edu
 * @version March 3, 2020
 */

public class MusicLibraryStub extends Object implements MusicLibraryInterface {

   private static final int buffSize = 10000;
   private static int id = 0;
   private String host;
   private int port;

   public MusicLibraryStub (String host, int port){
      this.host = host;
      this.port = port;
   }

   public String callMethod(String method, Object[] params){
      JSONObject theCall = new JSONObject();
      String ret = "{}";
      try{
         theCall.put("method",method);
         theCall.put("id",id);
         theCall.put("jsonrpc","2.0");
         ArrayList<Object> al = new ArrayList();
         for (int i=0; i<params.length; i++){
            al.add(params[i]);
         }
         JSONArray paramsJson = new JSONArray(al);
         theCall.put("params",paramsJson);
         Socket sock = new Socket(host,port);
         OutputStream os = sock.getOutputStream();
         InputStream is = sock.getInputStream();
         int numBytesReceived;
         int bufLen = 10000;
         String strToSend = theCall.toString();
         System.out.printf("Sending: %s%n", strToSend);
         byte bytesReceived[] = new byte[buffSize];
         byte bytesToSend[] = strToSend.getBytes();
         System.out.printf("Length: %d%n", bytesToSend.length);
         os.write(bytesToSend,0,bytesToSend.length);
         numBytesReceived = is.read(bytesReceived,0,bufLen);
         ret = new String(bytesReceived,0,numBytesReceived);
         os.close();
         is.close();
         sock.close();
      }catch(Exception ex){
         System.out.println("exception in callMethod: "+ex.getMessage());
      }
      return ret;
   }

   public boolean saveLibraryToFile() {
      boolean ret = false;
      String result = callMethod("saveLibraryToFile", new Object[]{});
      JSONObject res = new JSONObject(result);
      ret = res.optBoolean("result",false);
      return ret;
   }

   public boolean restoreLibraryFromFile() {
      boolean ret = false;
      String result = callMethod("restoreLibraryFromFile", new Object[]{});
      JSONObject res = new JSONObject(result);
      ret = res.optBoolean("result",false);
      return ret;
   }

   public String getAlbumNamesJsonRPC() {
      String ret;
      String result = callMethod("getAlbumNamesJsonRPC", new Object[]{});
      System.out.println(result);
      JSONObject res = new JSONObject(result);
      System.out.println(res.toString());
      ret = res.optJSONArray("result").toString();
      return ret;
   }

   public boolean addAlbum(String albumJson){
      boolean ret = false;
      String result = callMethod("addAlbum", 
            new Object[]{albumJson});
      JSONObject res = new JSONObject(result);
      ret = res.optBoolean("result", false);
      return ret;
   }

   public boolean removeAlbum(String title){
      boolean ret = false;
      String result = callMethod("removeAlbum", 
            new Object[]{title});
      JSONObject res = new JSONObject(result);
      ret = res.optBoolean("result", false);
      return ret;
   }

   public String getAlbumJsonRPC(String albumTitle){
      String result = callMethod("getAlbumJsonRPC", 
            new Object[]{albumTitle});
      System.out.println(result);
      JSONObject res = new JSONObject(result);
      String albumJson = res.optJSONObject("result").toString();
      System.out.println(albumJson);
      return albumJson;
   }

   public String getTrackJsonRPC(String albumTitle,
         String trackTitle){
      String result = callMethod("getTrackJsonRPC", 
            new Object[]{albumTitle, trackTitle});
      JSONObject res = new JSONObject(result);
      String trackJson = res.optString("result");
      return trackJson;
   }
   public void addTrack(String albumJson, String trackJson){
      String result = callMethod("addTrack",
            new Object[]{albumJson, trackJson});
   }

   public void removeTrack(String albumTitle, String trackTitle){
      String result = callMethod("removeTrack",
            new Object[]{albumTitle, trackTitle});
   }
}

