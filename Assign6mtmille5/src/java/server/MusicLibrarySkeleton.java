package ser321.assign6.mtmille5.server;

import java.net.*;
import java.io.*;
import java.util.*;
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
 * The student collection server creates a server socket.
 * When a client request arrives, which should be a JsonRPC request, a new
 * thread is created to service the call and create the appropriate response.
 * Byte arrays are used for communication to support multiple langs.
 *
 * @author Tim Lindquist ASU Polytechnic Department of Engineering
 * @author Marcus Miller mtmille5@asu.edu
 * @version March 3, 2020
 */

public class MusicLibrarySkeleton extends Object {
   MusicLibraryInterface albumsLib;

   public MusicLibrarySkeleton(MusicLibraryInterface albumsLib){
      this.albumsLib = albumsLib;
   }
   public String callMethod(String request){
      JSONObject result = new JSONObject();
      try{
         JSONObject theCall = new JSONObject(request);
         String method = theCall.getString("method");
         int id = theCall.getInt("id");
         JSONArray params = null;
         if(!theCall.isNull("params")){
            params = theCall.getJSONArray("params");
         }
         result.put("id",id);
         result.put("jsonrpc","2.0");
         if(method.equals("getAlbumNamesJsonRPC")){
            String names = albumsLib.getAlbumNamesJsonRPC();
            JSONArray array = new JSONArray(names);
            result.put("result",array);
         }else if(method.equals("addAlbum")){
            String albumJson = params.getString(0);
            boolean didAdd = albumsLib.addAlbum(albumJson);
            result.put("result", didAdd);
         }else if(method.equals("removeAlbum")){
            String albumTitle = params.getString(0);
            boolean didRemove = albumsLib.removeAlbum(albumTitle);
            result.put("result",didRemove);
         }else if(method.equals("getAlbumJsonRPC")){
            String albumTitle = params.getString(0);
            String albumJson = albumsLib.getAlbumJsonRPC(albumTitle);
            result.put("result",new JSONObject(albumJson));
         }else if(method.equals("getTrackJsonRPC")){
            String albumTitle = params.getString(0);
            String trackTitle = params.getString(1);
            String trackJson = albumsLib.getTrackJsonRPC(albumTitle, trackTitle);
            result.put("result",new JSONObject(trackJson));
         }else if(method.equals("addTrack")){
            String albumJson = params.getString(0);
            String trackJson = params.getString(1);
            albumsLib.addTrack(albumJson, trackJson);
            result.put("result",true);
         }else if(method.equals("removeTrack")){
            String albumTitle = params.getString(0);
            String trackTitle = params.getString(1);
            albumsLib.removeTrack(albumTitle, trackTitle);
            result.put("result",true);
         }else if(method.equals("saveLibraryToFile")){
            boolean isSaved = albumsLib.saveLibraryToFile();
            result.put("result", isSaved);
         }else if(method.equals("restoreLibraryFromFile")){
            boolean isLoaded = albumsLib.restoreLibraryFromFile();
            result.put("result", isLoaded);
         }else{
            result.put("result",0.0);
         }
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("exception in callMethod: "+ex.getMessage());
      }
      return result.toString();
   }
}
