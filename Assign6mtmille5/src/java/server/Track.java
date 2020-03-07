package ser321.assign6.mtmille5.server;
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

import java.io.Serializable;

import org.json.JSONObject;

public class Track implements Serializable{

   //Track Name - the title of the track.
   public String name;
   //Artist - the artist or group name.
   public String artist;
   //Rank Order - an integer indicating the track number of this track. You should number all tracks in an album ignoring (don't reset to 1) multiple disks.
   public String rankOrder;
   //Duration - a string indicating the track's run time in the form: mm:ss
   public int duration;
   //File Name - a string indicating the track's mp3 file name, such as ByeByeLove.mp3. This field is optional and only needed if you are going to support associating mp3 files with your library together with playback.
   public String fileName;
   //Album Name
   public String album;

   public Track(String name, String artist, String rankOrder, int duration, String fileName) {
      this.name = name;
      this.artist = artist;
      this.rankOrder = rankOrder;
      this.duration = duration;
      this.fileName = fileName;
   }

   public Track(String jsonString){
      this(new JSONObject(jsonString));
   }

   public Track(JSONObject jsonObj){
      try{
         //System.out.println("constructor from json received: "+
         //                   jsonObj.toString());
         try{
            name = jsonObj.getString("name");
            artist = jsonObj.getJSONObject("artist").getString("name");

            rankOrder = jsonObj.getJSONObject("@attr").getString("rank");
            duration = Integer.parseInt(jsonObj.getString("duration"));
            fileName = name + ".mp3";

            System.out.println("constructed "+this.toJsonString()+" from json");
         }catch(Exception firstTry){
            name = jsonObj.getString("trackMTM");
            artist = jsonObj.getString("artistMTM");
            rankOrder = jsonObj.getString("rankMTM");
            duration = jsonObj.getInt("durationMTM");
            fileName = jsonObj.getString("fileNameMTM");
            album = jsonObj.getString("albumMTM");
         }
      }catch(Exception ex){
         System.out.println("Exception in MediaDescription(JSONObject): "+ex.getMessage());
      }
   }

   public String toJsonString(){
      String ret = "";
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
         obj.put("trackMTM", name);
         obj.put("artistMTM", artist);
         obj.put("rankMTM", rankOrder);
         obj.put("durationMTM", duration);
         obj.put("fileNameMTM", fileName);
         obj.put("albumMTM", album);
      }catch(Exception ex){
         ex.printStackTrace();
         System.out.println("Exception in toJson: "+ex.getMessage());
      }
      return obj;
   }

   public String getName() {
      return name;
   }
   public void setName(String name) {
      this.name = name;
   }
   public String getArtist() {
      return artist;
   }
   public void setArtist(String artist) {
      this.artist = artist;
   }
   public String getRankOrder() {
      return rankOrder;
   }
   public void setRankOrder(String rankOrder) {
      this.rankOrder = rankOrder;
   }
   public int getDuration() {
      return duration;
   }
   public void setDuration(int duration) {
      this.duration = duration;
   }
   public String getFileName() {
      return fileName;
   }
   public void setFileName(String fileName) {
      this.fileName = fileName;
   }
}
