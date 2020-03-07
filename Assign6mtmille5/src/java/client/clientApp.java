package ser321.assign6.mtmille5.client;

import ser321.assign2.lindquis.MediaLibraryGui;
import ser321.assign6.mtmille5.server.*;
import javax.swing.*;
import java.io.*;
import java.nio.file.Paths;
import java.nio.charset.Charset;
import javax.sound.sampled.*;
import java.beans.*;
import java.net.*;
import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.text.html.*;
import javax.swing.filechooser.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.lang.Runtime;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.net.URLConnection;
import java.time.Duration;

import org.json.JSONArray;


/**
 * Copyright 2020 Tim Lindquist, Marcus Miller,
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
 * Purpose: MediaLibraryApp instructor sample for solving Spring 2020 ser321 assignments.
 * This problem provides for browsing and managing information about
 * music albums. It uses a Swing JTree, and JTextField controls to 
 * realize a GUI with a split pane. The left pane contains an expandable
 * JTree of the media library.
 * This program is a sample controller for the non-distributed version of
 * the system.
 * The right pane contains components that allow viewing, modifying and adding
 * albums, tracks, and corresponding files.
 *
 * @author Tim Lindquist (Tim.Linquist@asu.edu),
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @author Marcus Miller mtmille5@asu.edu
 * @version March 3, 2020
 */
public class clientApp extends MediaLibraryGui implements
TreeWillExpandListener,
   ActionListener,
   TreeSelectionListener {

      private static final boolean debugOn = true;
      private static final String cher = 
         "https://lastfm.freetls.fastly.net/i/u/300x300/3b54885952161aaea4ce2965b2db1638.png";
      private static final String pre = 
         "http://ws.audioscrobbler.com/2.0/?method=album.getinfo&artist=";
      private String url;
      private boolean stopPlaying;         //shared, but not synchronized with playing thread.
      private MusicLibraryStub library;
      private String lastFMKey;
      private Album searchedAlbum;
      private String searchedJson;

      public clientApp(String author, String hostId, String regPort, String key) {
         super(author);
         try{
            this.lastFMKey = key;
            library = new MusicLibraryStub(hostId,
                  Integer.parseInt(regPort));

            stopPlaying = false;

            // register this object as an action listener for menu item clicks. This will cause
            // my actionPerformed method to be called every time the user selects a menuitem.
            for(int i=0; i<userMenuItems.length; i++){
               for(int j=0; j<userMenuItems[i].length; j++){
                  userMenuItems[i][j].addActionListener(this);
               }
            }
            // register this object as an action listener for the Search button. This will cause
            // my actionPerformed method to be called every time the user clicks the Search button
            searchJButt.addActionListener(this);
            try{
               tree.addTreeSelectionListener(this);
               rebuildTree();
            }catch (Exception ex){
               ex.printStackTrace();
               JOptionPane.showMessageDialog(this,"Handling "+
                     " constructor exception: " + ex.getMessage());
            }
            try{
               /*
                * display an image just to show how the album or artist image can be 
                * displayed in the app's window. setAlbumImage is implemented by
                * MediaLibraryGui class. Call it with a
                * string url to a png file as obtained from an album search.
                */
               setAlbumImage(cher);
            }catch(Exception ex){
               System.out.println("unable to open Cher png");
            }
            setVisible(true);
         }
         catch(Exception e){
            e.printStackTrace();
         }
      }

      /**
       * A method to facilite printing debugging messages during development, but which can be
       * turned off as desired.
       *
       **/

      private void debug(String message) {
         if (debugOn)
            System.out.println("debug: "+message);
      }

      /**
       * Create and initialize nodes in the JTree of the left pane.
       * buildInitialTree is called by MediaLibraryGui to initialize the JTree.
       * Classes that extend MediaLibraryGui should override this method to 
       * perform initialization actions specific to the extended class.
       * The default functionality is to set base as the label of root.
       * In your solution, you will probably want to initialize by deserializing
       * your library and displaying the categories and subcategories in the
       * tree.
       * @param root Is the root node of the tree to be initialized.
       * @param base Is the string that is the root node of the tree.
       */
      public void buildInitialTree(DefaultMutableTreeNode root, String base){
         //set up the context and base name
         try{
            System.out.println(base);
            root.setUserObject(base);
         }catch (Exception ex){
            JOptionPane.showMessageDialog(this,"exception initial tree:"+ex);
            ex.printStackTrace();
         }
      }

      /**
       *
       * method to build the JTree of music shown in the left panel of the UI. The
       * field tree is a JTree as defined and initialized by MediaLibraryGui class.
       * it is defined to be protected so it can be accessed by extending classes.
       * This version of the method uses the music library to get the names of
       * tracks. Your solutions will need to replace this structure with one that
       * keeps information particular to both Album and Track (two classes Album.java,
       * and Track.java). Your music library will store and provide access to Album
       * and Track objects.
       * This method is provided to demonstrate one way to add nodes to a JTree based
       * on an underlying storage structure.
       * See also the methods clearTree, valueChanged, and getSubLabelled defined in this class.
       **/
      public void rebuildTree(){
         tree.removeTreeSelectionListener(this);
         //tree.removeTreeWillExpandListener(this);
         DefaultTreeModel model = (DefaultTreeModel)tree.getModel();
         DefaultMutableTreeNode root = (DefaultMutableTreeNode)model.getRoot();
         clearTree(root, model);
         root.setUserObject(getTitle());
         DefaultMutableTreeNode musicNode = new DefaultMutableTreeNode("Music");
         model.insertNodeInto(musicNode, root, model.getChildCount(root));
         DefaultMutableTreeNode searchedNode = new DefaultMutableTreeNode("Searched");
         model.insertNodeInto(searchedNode, root, model.getChildCount(root));
         // put nodes in the tree for all  registered with the library
         JSONArray musicList = new JSONArray(library.getAlbumNamesJsonRPC());
         if(musicList != null) {
            for (int i = 0; i<musicList.length(); i++){
               Album album = new Album(library.getAlbumJsonRPC(musicList.getString(i)));
               String albumName = album.getAlbumName();
               debug("Adding album with title:"+ albumName);
               DefaultMutableTreeNode albumNode = new DefaultMutableTreeNode(albumName);
               model.insertNodeInto(albumNode, musicNode, model.getChildCount(musicNode));
               for(Track track: album.getTracks()) {
                  DefaultMutableTreeNode trackNode =
                     new DefaultMutableTreeNode(track.getName());
                  model.insertNodeInto(trackNode, albumNode,
                        model.getChildCount(albumNode));
               }
            }
         }
         if(searchedAlbum != null){
            String albumName = searchedAlbum.getAlbumName();
            debug("Adding album with title:"+ albumName);
            DefaultMutableTreeNode albumNode = new DefaultMutableTreeNode(albumName);
            model.insertNodeInto(albumNode, searchedNode, model.getChildCount(searchedNode));
            for(Track track: searchedAlbum.getTracks()) {
               DefaultMutableTreeNode trackNode =
                  new DefaultMutableTreeNode(track.getName());
               model.insertNodeInto(trackNode, albumNode,
                     model.getChildCount(albumNode));
            }
         }
         // expand all the nodes in the JTree
         for(int r =0; r < tree.getRowCount(); r++){
            tree.expandRow(r);
         }
         tree.addTreeSelectionListener(this);
      }

      private void clearTree(DefaultMutableTreeNode root, DefaultTreeModel model){
         try{
            DefaultMutableTreeNode next = null;
            int subs = model.getChildCount(root);
            for(int k=subs-1; k>=0; k--){
               next = (DefaultMutableTreeNode)model.getChild(root,k);
               debug("removing node labelled:"+(String)next.getUserObject());
               model.removeNodeFromParent(next);
            }
         }catch (Exception ex) {
            System.out.println("Exception while trying to clear tree:");
            ex.printStackTrace();
         }
      }

      private DefaultMutableTreeNode getSubLabelled(DefaultMutableTreeNode root,
            String label){
         DefaultMutableTreeNode ret = null;
         DefaultMutableTreeNode next = null;
         boolean found = false;
         for(Enumeration<TreeNode> e = root.children();
               e.hasMoreElements();){
            next = (DefaultMutableTreeNode)e.nextElement();
            debug("sub with label: "+(String)next.getUserObject());
            if (((String)next.getUserObject()).equals(label)){
               debug("found sub with label: "+label);
               found = true;
               break;
            }
         }
         if(found)
            ret = next;
         else
            ret = null;
         return (DefaultMutableTreeNode)ret;
      }

      public void treeWillCollapse(TreeExpansionEvent tee) {
         debug("In treeWillCollapse with path: "+tee.getPath());
         tree.setSelectionPath(tee.getPath());
      }

      public void treeWillExpand(TreeExpansionEvent tee) {
         debug("In treeWillExpand with path: "+tee.getPath());
      }

      public void valueChanged(TreeSelectionEvent e){
         try{
            tree.removeTreeSelectionListener(this);
            DefaultMutableTreeNode node = (DefaultMutableTreeNode)
               tree.getLastSelectedPathComponent();
            if(node!=null){
               String nodeLabel = (String)node.getUserObject();
               debug("In valueChanged. Selected node labelled: "+nodeLabel);
               // is this a terminal node?
               String grandparentName = "";
               if(node.getLevel() == 3){
                  DefaultMutableTreeNode grandparent = (DefaultMutableTreeNode) node.getParent();
                  grandparent = (DefaultMutableTreeNode) grandparent.getParent();
                  grandparentName = (String) grandparent.getUserObject();
               }
               //System.out.println(grandparentName + "xxxxxxxxxxxxx");
               if(node.getLevel() == 3 
                  && grandparentName.equals("Music")){
                  Track track = new Track(
                     library.getTrackJsonRPC(node.getParent().toString(),nodeLabel));
                  trackJTF.setText(nodeLabel);
                  authorJTF.setText(track.getArtist());
                  albumJTF.setText(node.getParent().toString());
                  fileNameJTF.setText(track.getFileName());
                  int minutes = track.getDuration()/60;
                  int seconds = track.getDuration()%60;
                  String time = String.format("%02d:%02d",minutes,seconds);
                  timeJTF.setText(time);
                  rankJTF.setText(String.valueOf(track.getRankOrder()));
                  String albumName = (String)
                     ((DefaultMutableTreeNode)node.getParent()).getUserObject();
                  Album album = new Album(library.getAlbumJsonRPC(albumName));
                  summaryJTA.setText(album.getSummary());
                  genreJCB.removeAllItems();
                  for(int i = 0; i < album.getGenre().size(); i++) {
                     genreJCB.addItem(album.getGenre().get(i));
                  }
                  setAlbumImage(album.getImage());
               }
               else if(node.getLevel() == 3){
                  Track track = searchedAlbum.getTrack(nodeLabel);
                  trackJTF.setText(nodeLabel);
                  authorJTF.setText(track.getArtist());
                  albumJTF.setText(node.getParent().toString());
                  fileNameJTF.setText(track.getFileName());
                  int minutes = track.getDuration()/60;
                  int seconds = track.getDuration()%60;
                  String time = String.format("%02d:%02d",minutes,seconds);
                  timeJTF.setText(time);//fix to mm:ss
                  rankJTF.setText(track.getRankOrder());
                  Album album = searchedAlbum;
                  summaryJTA.setText(album.getSummary());
                  genreJCB.removeAllItems();
                  for(int i = 0; i < album.getGenre().size(); i++) {
                     genreJCB.addItem(album.getGenre().get(i));
                  }
                  setAlbumImage(album.getImage());

               }
               else if (node.getLevel() == 2 && node.getParent().toString().equals("Music")) {
                  trackJTF.setText("");
                  authorJTF.setText("");
                  albumJTF.setText(nodeLabel);
                  Album album = new Album (library.getAlbumJsonRPC(nodeLabel));
                  fileNameJTF.setText("");
                  timeJTF.setText(album.getRunTime());
                  rankJTF.setText("");
                  summaryJTA.setText(album.getSummary());
                  genreJCB.removeAllItems();
                  for(int i = 0; i < album.getGenre().size(); i++) {
                     genreJCB.addItem(album.getGenre().get(i));
                  }
                  setAlbumImage(album.getImage());
               }
               else if (node.getLevel() == 2){
                  trackJTF.setText("");
                  authorJTF.setText("");
                  albumJTF.setText(nodeLabel);
                  Album album = searchedAlbum;
                  fileNameJTF.setText("");
                  timeJTF.setText(album.getRunTime());
                  rankJTF.setText("");
                  summaryJTA.setText(album.getSummary());
                  genreJCB.removeAllItems();
                  for(int i = 0; i < album.getGenre().size(); i++) {
                     genreJCB.addItem(album.getGenre().get(i));
                  }
                  setAlbumImage(album.getImage());

               }
            }
         }catch (Exception ex){
            ex.printStackTrace();
         }
         tree.addTreeSelectionListener(this);
      }

      public void actionPerformed(ActionEvent e) {
         try{
            tree.removeTreeSelectionListener(this);
            if(e.getActionCommand().equals("Exit")) {
               System.exit(0);
            }else if(e.getActionCommand().equals("Save")) {
               try {
                  library.saveLibraryToFile();
               }
               catch(Exception exp) {
                  exp.printStackTrace();
               }
            }else if(e.getActionCommand().equals("Restore")) {
               try {
                  library.restoreLibraryFromFile();
               }
               catch(Exception exp2) {
                  exp2.printStackTrace();
               }

               rebuildTree();
            }else if(e.getActionCommand().equals("AlbumAdd")) {
               library.addAlbum(searchedJson);
               rebuildTree();

            }else if(e.getActionCommand().equals("TrackAdd")) {
               DefaultMutableTreeNode node = (DefaultMutableTreeNode) 
                  tree.getLastSelectedPathComponent();
               if(node != null && node.getLevel() == 3){
                  String albumName = (String)
                     ((DefaultMutableTreeNode)node.getParent()).getUserObject();
                  String trackName = (String) node.getUserObject();
                  System.out.println("Adding track");
                  if(albumName.equals(searchedAlbum.albumName)){
                     library.addTrack(searchedJson, trackName);
                     rebuildTree();
                  }
               }

            }else if(e.getActionCommand().equals("Search")) {
               searchedJson = fetchURL(artistSearchJTF.getText().trim(),
                     albumSearchJTF.getText().trim());
               searchedAlbum = new Album(searchedJson);
               rebuildTree();
            }else if(e.getActionCommand().equals("Tree Refresh")) {
               rebuildTree();
            }else if(e.getActionCommand().equals("TrackRemove")) {
               DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                  tree.getLastSelectedPathComponent();
               String albumName = (String)
                  ((DefaultMutableTreeNode)node.getParent()).getUserObject();
               String trackName = (String) node.getUserObject();
               System.out.println("Removing Track");
               library.removeTrack(albumName, trackName);
               rebuildTree();
            }else if(e.getActionCommand().equals("AlbumRemove")) {
               DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                  tree.getLastSelectedPathComponent();
               if(node!=null){
                  String nodeLabel = (String)node.getUserObject();
                  library.removeAlbum(nodeLabel);
               }
               rebuildTree();
            }else if(e.getActionCommand().equals("AlbumPlay") ||
                  e.getActionCommand().equals("TrackPlay")){
               System.out.println("AlbumPlay not implemented");
            }
            tree.addTreeSelectionListener(this);
         }
         catch(Exception e2){
            e2.printStackTrace();
         }
      }
      /**
       *
       * a method to make a web request. Note that this method will block execution
       * for up to 20 seconds while the request is being satisfied. Better to use a
       * non-blocking request.
       * @param aUrl the String indicating the query url for the lastFM api search
       * @return the String result of the http request.
       *
       **/
      public String fetchURL(String artist, String album) {
         String aUrl = pre+artist+
            "&album="+album+
            "&api_key="+lastFMKey+"&format=json";
         System.out.println("calling fetchURL with url: "+aUrl);
         StringBuilder sb = new StringBuilder();
         URLConnection conn = null;
         InputStreamReader in = null;
         try {
            URL url = new URL(aUrl);
            conn = url.openConnection();
            if (conn != null)
               conn.setReadTimeout(20 * 1000); // timeout in 20 seconds
            if (conn != null && conn.getInputStream() != null) {
               in = new InputStreamReader(conn.getInputStream(),
                     Charset.defaultCharset());
               BufferedReader br = new BufferedReader(in);
               if (br != null) {
                  int ch;
                  // read the next character until end of reader
                  while ((ch = br.read()) != -1) {
                     sb.append((char)ch);
                  }
                  br.close();
               }
            }
            in.close();
         } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Exception in url request:"+ ex.getMessage());
         }
         return sb.toString();
      }

      /**
       *
       * A method to do asynchronous url request printing the result to System.out
       * @param aUrl the String indicating the query url for the lastFM api search
       *
       **/
      public void fetchAsyncURL(String aUrl){
         try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
               .uri(URI.create(aUrl))
               .timeout(Duration.ofMinutes(1))
               .build();
            client.sendAsync(request, BodyHandlers.ofString())
               .thenApply(HttpResponse::body)
               .thenAccept(System.out::println)
               .join();

         }catch(Exception ex){
            ex.printStackTrace();
            System.out.println("Exception in fetchAsyncUrl request: "+ex.getMessage());
         }
      }
      public boolean sezToStop(){
         return stopPlaying;
      }

      public static void main(String args[]) {
         String name = "Marcus.Miller";
         String hostId = "localhost";
         String regPort = "1099";
         String key = "lastfmkey";
         if (args.length >= 3){
            hostId = args[0];
            regPort = args[1];
            key = args[2];
            name = args[3];
         }
         try{
            String url = "http://"+hostId+":"+regPort+"/";
            System.out.println("opening connection to: "+url);
            clientApp mla = new clientApp(name, hostId, regPort,
                  key);
         }catch (Exception ex){
            ex.printStackTrace();
         }
      }
   }
