package ser321.assign6.mtmille5.server;

import java.io.Serializable;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Vector;
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
 * @version January 2020
 */


public class Album implements Serializable{
   //Album Name - the title of the album.
   public String albumName;
   //Artist - the artist or group name.
   public String artist;
   //Tracks - a collection of Track objects representing the music tracks in the album.
   public Vector<Track> tracks;
   //Image - a string URL to the Last.fm provided image (use the extralarge image url, which is 300x300 pixels).
   public String image;
   //Genre - an optional collection of genre names. Some Last.fm searches provide tag names in the search results with provide genre information.
   public Vector<String> genre;
   //Run Time - Total the run time for each track in the album to get the album's total run time. Run time should be in the form: hh:mm:ss.
   public String runTime;
   //Summary - an optional summary description of the album. Some Last.fm searches provide album summary information in the search results.
   public String summary;

   public Album(String albumName, String artist, Vector<Track> tracks, String image, Vector<String> genre, String runTime, String summary) {
      this.albumName = albumName;
      this.artist = artist;
      this.tracks = tracks;
      this.image = image;
      this.genre = genre;
      this.runTime = runTime;
      this.summary = summary;
   }

   public Album(String jsonString){
      this(new JSONObject(jsonString));
   }

   public Album(JSONObject jsonObj){
      try{
         //System.out.println("constructor from json received: "+
         //                   jsonObj.toString());
         int runTimeInt = 0;
         try{
         albumName = jsonObj.getJSONObject("album").getString("name");
         artist = jsonObj.getJSONObject("album").getString("artist");
         //tracks = jsonObj.getString("tracks");
         image = ((JSONObject)jsonObj.getJSONObject("album").getJSONArray("image").get(3)).getString("#text");
         System.out.println(image);
         JSONArray tracksArray = jsonObj.getJSONObject("album").getJSONObject("tracks").getJSONArray("track");
         tracks = new Vector<Track>();
         for(int i = 0; i < tracksArray.length(); i++) {
            Track track = new Track(tracksArray.getJSONObject(i));
            track.album = albumName;
            tracks.add(track);
            runTimeInt += Integer.parseInt(tracksArray.getJSONObject(i).getString("duration"));
         }
         JSONArray genreArray = jsonObj.getJSONObject("album").getJSONObject("tags").getJSONArray("tag");
         genre = new Vector<String>();
         for(int i = 0; i < genreArray.length(); i++) {
            genre.add(genreArray.getJSONObject(i).getString("name"));
         }
         int hours = runTimeInt / 3600;
         runTimeInt %= 3600;
         int minutes = runTimeInt / 60;
         runTimeInt %= 60;
         runTime = hours + ":" + minutes + ":" + runTimeInt;
         summary = jsonObj.getJSONObject("album").getJSONObject("wiki").getString("summary");
         System.out.println("constructed "+this.toJsonString()+" from json");
         }
         catch(Exception firstTry){
            albumName = jsonObj.getString("nameMTM");
            artist = jsonObj.getString("artistMTM");
            JSONArray joTracks = jsonObj.getJSONArray("tracksMTM");
            tracks = new Vector<Track>();
            for(int j = 0; j < joTracks.length(); j++){
               tracks.add(new Track(joTracks.getJSONObject(j)));
            }
            image = jsonObj.getString("imageMTM");
            JSONArray joGenres = jsonObj.getJSONArray("genresMTM");
            genre = new Vector<String>();
            for(int j = 0; j < joGenres.length(); j++){
               genre.add(joGenres.getString(j));
            }
            runTime = jsonObj.getString("runTimeMTM");
            summary = jsonObj.getString("summaryMTM");
         }
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("Exception in Album(JSONObject): "+ex.getMessage());
      }
   }

   public Track getTrack(String trackName){
      for(int i = 0; i < tracks.size(); i++){
         if( tracks.get(i).name.equals(trackName)){
            return tracks.get(i);
         }
      }
      return null;
   }

   public String toJsonString(){
      String ret = "{}";
      try{
         ret = this.toJson().toString(0);
      }catch(Exception ex){
         System.out.println("Exception in toJsonString: "+ex.getMessage());
      }
      return ret;
   }

   public JSONObject toJson(){
      JSONObject obj = new JSONObject();
      try{
         obj.put("nameMTM", albumName);
         obj.put("artistMTM", artist);
         JSONArray tracksJA = new JSONArray();
         for(int i = 0; i < tracks.size(); i++){
            tracksJA.put(tracks.get(i).toJson());
         }
         obj.put("tracksMTM", tracksJA); 
         obj.put("imageMTM", image);
         JSONArray genresJA = new JSONArray();
         for(int i = 0; i < genre.size(); i++){
            genresJA.put(genre.get(i));
         }
         obj.put("genresMTM", genresJA);

         obj.put("runTimeMTM", runTime);
         obj.put("summaryMTM", summary);
      }catch(Exception ex){
         System.out.println("Exception in toJson: "+ex.getMessage());
      }
      return obj;
   }

   public String getAlbumName() {
      return albumName;
   }
   public void setAlbumName(String albumName) {
      this.albumName = albumName;
   }
   public String getArtist() {
      return artist;
   }
   public void setArtist(String artist) {
      this.artist = artist;
   }
   public Vector<Track> getTracks() {
      return tracks;
   }
   public void setTracks(Vector<Track> tracks) {
      this.tracks = tracks;
   }
   public String getImage() {
      return image;
   }
   public void setImage(String image) {
      this.image = image;
   }
   public Vector<String> getGenre() {
      return genre;
   }
   public void setGenre(Vector<String> genre) {
      this.genre = genre;
   }
   public String getRunTime() {
      return runTime;
   }
   public void setRunTime(String runTime) {
      this.runTime = runTime;
   }
   public String getSummary() {
      return summary;
   }
   public void setSummary(String summary) {
      this.summary = summary;
   }

}
