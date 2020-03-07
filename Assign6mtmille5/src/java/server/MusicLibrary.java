package ser321.assign6.mtmille5.server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;
import java.util.Vector;
import java.io.PrintWriter;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONTokener;



/*
 * Copyright 2020 Marcus Miller,
 *
 * This software is the intellectual property of the author, and can not be distributed, used, copied, or
 * reproduced, in whole or in part, for any purpose, commercial or otherwise. The author grants the ASU
 * Software Engineering program the right to copy, execute, and evaluate this work for the purpose of
 * determining performance of the author in coursework, and for Software Engineering program evaluation,
 * so long as this copyright and right-to-use statement is kept in-tact in such use.
 * All other uses are prohibited and reserved to the author.
 *
 * Purpose: An Album class for serializing between client and server.
 *
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Marcus Miller mtmille5@asu.edu
 * @version March 3, 2020
 */

public class MusicLibrary implements MusicLibraryInterface{
   private Hashtable<String,Album> aLib;
   private static final String fileName="albums.json";
   public MusicLibrary (){
      aLib = new Hashtable<String,Album>();
      restoreLibraryFromFile();
   }


   //getAlbumNames - a method to get the titles of albums stored in the library. It could return an array of string titles.
   public String[] getAlbumNames(){
      String[] result = null;
      try{
         Set<String> vec = aLib.keySet();
         result = vec.toArray(new String[]{});
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("exception in getAlbums: "+ex.getMessage());
      }
      return result;
   }
   //getAlbumNamesJsonRPC - a method to get the titles of albums stored in the library. It could return an array of string titles.
   public synchronized String getAlbumNamesJsonRPC(){
      String[] result = null;
      String str = "";
      try{
         Set<String> vec = aLib.keySet();
         result = vec.toArray(new String[]{});
         JSONArray array = new JSONArray();
         for(int i=0; i<result.length; i++){
            System.out.printf("%s%n",result[i]);
            array.put(result[i]);
         }
         str = array.toString();
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("exception in getAlbums: "+ex.getMessage());
      }
      return str;
   }
   //getAlbum - a method to get an Album object based on its title. You can assume that all albums in the library will have a unique title.
   public Album getAlbum(String title){
      return aLib.get(title);
   }
   //getAlbumJsonRPC - a method to get an Album object based on its title. You can assume that all albums in the library will have a unique title.
   public synchronized String getAlbumJsonRPC(String title){
      return aLib.get(title).toJsonString();
   }
   //addAlbum - a method to add an album to the library. Used for example when the user wants to add the result of a Last.fm search to the library.
   public synchronized boolean addAlbum(String albumJson){

      boolean result = false;
      System.out.println("Adding Album: ");
      try{
         Album album = new Album(albumJson);
         System.out.println("Constructed album");
         aLib.put(album.getAlbumName(), album);
         result = true;
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("exception in add: "+ex.getMessage());
      }
      return result;
   }
   public boolean addAlbum(Album album){
      if(album == null) {
         return false;
      }
      aLib.put(album.getAlbumName(), album);
      return true;
   }
   //removeAlbum - a method to remove an album from the library.
   public synchronized boolean removeAlbum(String title){
      boolean result = false;
      System.out.println("Removing "+ title);
      try{
         aLib.remove(title);
         result = true;
      }catch(Exception ex){
         System.out.println("exception in remove: " + ex.getMessage());
      }
      return result;
   }

   public Track getTrack(String albumTitle, String trackTitle){
      Track result = null;
      try{
         Vector<Track> tracks = aLib.get(albumTitle).getTracks();
         for(Track track: tracks) {
            if(track.getName().equals(trackTitle)) {
               result = track;
            }
         }
      }catch(Exception ex){
         System.out.println("exception in get: "+ex.getMessage());
      }
      return result;
   }
   public synchronized String getTrackJsonRPC(String albumTitle, String trackTitle){
      Track result = null;
      try{
         Vector<Track> tracks = aLib.get(albumTitle).getTracks();
         for(Track track: tracks) {
            if(track.getName().equals(trackTitle)) {
               result = track;
            }
         }
      }catch(Exception ex){
         System.out.println("exception in get: "+ex.getMessage());
      }
      return result.toJsonString();
   }
   public synchronized void addTrack(String albumJson, String trackTitle){
      Album album = new Album(albumJson);
      String albumTitle = album.albumName;
      System.out.println(albumTitle + "xxxxxxxxxxxxxxxxx");
      Track track = album.getTrack(trackTitle);
      if(aLib.containsKey(albumTitle)) {
         if(aLib.get(albumTitle).getTracks().contains(track)) {
            System.out.println("Track already exist");
         }
         else {
            System.out.println("Adding track");
            aLib.get(albumTitle).getTracks().add(track);
         }
      }
      else {
         System.out.println("Adding track and Album");
         Vector<Track> tracks = new Vector<Track>();
         tracks.add(track);
         Album newAlbum = new Album(album.getAlbumName(),album.getArtist(), tracks,
               album.getImage(), album.getGenre(), album.getRunTime(), album.getSummary());
         addAlbum(newAlbum);
      }

   }
   public synchronized void removeTrack(String albumTitle, String trackTitle) {
      Track track = getTrack(albumTitle, trackTitle);
      try {
         aLib.get(albumTitle).getTracks().remove(track);
         System.out.println("Successfully removed track");
      }
      catch(Exception e) {
         System.out.println("Failed to remove track");
      }
   }

   //saveLibraryToFile - a method to serialize the library to a json file.
   public synchronized boolean saveLibraryToFile() {
      boolean ret = true;
      try{
         // Save list and terminate
         System.out.println("Saving Library");
         Set<String> keys = aLib.keySet();
         JSONArray albums = new JSONArray();
         for(String key: keys){
            JSONObject album = new JSONObject();
            album.put("artistMTM", aLib.get(key).artist);
            album.put("genresMTM", aLib.get(key).genre);
            album.put("imageMTM", aLib.get(key).image);
            album.put("nameMTM", aLib.get(key).albumName);
            album.put("runTimeMTM", aLib.get(key).runTime);
            album.put("summaryMTM", aLib.get(key).summary);
            JSONArray tracks = new JSONArray();
            for(int i = 0; i < aLib.get(key).tracks.size(); i++){
               Track track = aLib.get(key).tracks.get(i);
               JSONObject trackJSON = new JSONObject();
               trackJSON.put("albumMTM", aLib.get(key).albumName);
               trackJSON.put("artistMTM", track.artist);
               trackJSON.put("durationMTM", track.duration);
               trackJSON.put("fileNameMTM", track.name + ".mp3");
               trackJSON.put("rankMTM", track.rankOrder);
               trackJSON.put("trackMTM", track.name);
               tracks.put(trackJSON);
            }
            album.put("tracksMTM", tracks);
            albums.put(album);
         }
         // Write the object to the stream
         PrintWriter out = new PrintWriter(fileName);
         out.println(albums.toString(2));
         out.close();
      }
      catch(Exception ex){
         ex.printStackTrace();
         ret = false;
      }
      return ret;
   }
   //restoreLibraryFromFile - a method to (re)initialize the library by de-serializing from a json file.
   public synchronized boolean restoreLibraryFromFile(){
      boolean ret = true;
      try{
         aLib.clear();
         File f = new File(fileName);
         System.out.println(f.exists());
         FileInputStream is = new FileInputStream(f);

         JSONArray albums = new JSONArray(new JSONTokener(is));
         System.out.print(albums.toString());

         for(int i=0; i < albums.length(); i++){
            //for(int i=0; i < 1; i++){
            JSONObject obj= albums.getJSONObject(i);
            String artist = obj.getString("artistMTM");
            JSONArray genresJSON = obj.getJSONArray("genresMTM");
            Vector<String> genres = new Vector<String>(genresJSON.length());
            for(int j = 0; j < genresJSON.length(); j++){
               genres.add(genresJSON.getString(j));
            }

            String image = obj.getString("imageMTM");
            String name = obj.getString("nameMTM");
            String runTime = obj.getString("runTimeMTM");
            String summary = obj.getString("summaryMTM");
            JSONArray tracksJSON = obj.getJSONArray("tracksMTM");
            Vector<Track> tracks = new Vector<Track>(tracksJSON.length());
            for(int j = 0; j < tracksJSON.length(); j++){
               JSONObject trackJson = tracksJSON.getJSONObject(j);
               String trackName = trackJson.getString("trackMTM");
               String trackArtist = trackJson.getString("artistMTM");
               String rankOrder = trackJson.getString("rankMTM");
               int duration = trackJson.getInt("durationMTM");
               String trackFileName = trackJson.getString("fileNameMTM");
               Track track = new Track(trackName, trackArtist, rankOrder, 
                     duration, trackFileName);
               track.album = trackJson.getString("albumMTM");
               tracks.add(track);
            }
            Album album = new Album(name, 
                  artist, tracks, image, genres, runTime, summary);
            aLib.put(name, album);
         }
         }catch (Exception ex){
            ex.printStackTrace();
            System.out.println("Exception reading json file: "+ex.getMessage());
            ret = false;
         }
         return ret;

      }
   }
