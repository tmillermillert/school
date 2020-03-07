package ser321.assign6.mtmille5.server;

import java.net.*;
import java.io.*;
import java.util.*;

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
 * A class for TCP client-server connections with a threaded server that
 * implements JsonRPC method calls for a collection of Albums.
 *
 * Ser321 Foundations of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist Tim.Lindquist@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @author Marcus Miller mtmille5@asu.edu
 * @version March 3, 2020
 */
public class MusicLibraryTCPJsonRPCServer extends Thread {
   private Socket conn;
   private int id;
   private MusicLibrarySkeleton skeleton;
   public MusicLibraryTCPJsonRPCServer (Socket sock, int id,
         MusicLibraryInterface mLib) {
      this.conn = sock;
      this.id = id;
      skeleton = new MusicLibrarySkeleton(mLib);
   }
   public void run() {
      try {
         OutputStream outSock = conn.getOutputStream();
         InputStream inSock = conn.getInputStream();
         byte clientInput[] = new byte[10000]; // up to 1024 bytes in a message.
         int numr = inSock.read(clientInput,0,10000);
         if (numr != -1) {
            //System.out.println("read "+numr+" bytes");
            String request = new String(clientInput,0,numr);
            System.out.println("request is: "+request);
            String response = skeleton.callMethod(request);
            byte clientOut[] = response.getBytes();
            System.out.printf("Client length %d%n", clientOut.length);
            outSock.write(clientOut,0,clientOut.length);
            System.out.println("response is: "+response);
         }
         inSock.close();
         outSock.close();
         conn.close();
      } catch (IOException e) {
         System.out.println("I/O exception occurred for the connection:\n"+e.getMessage());
      }
   }

   public static void main (String args[]) {
      Socket sock;
      MusicLibraryInterface mLib = new MusicLibrary();
      int id=0;
      try {
         if (args.length != 1) {
            System.out.println("Usage: java ser321.assign6.mtmille5.server."+
                  "MusicLibraryTCPJsonRPCServer [portNum]");
            System.exit(0);
         }
         int portNo = Integer.parseInt(args[0]);
         if (portNo <= 1024) portNo=8888;
         ServerSocket serv = new ServerSocket(portNo);
         // accept client requests. For each request create a new thread to handle
         while (true) {
            System.out.println("Music Library server waiting for connects on port "
                  +portNo);
            sock = serv.accept();
            System.out.println("Music Library server connected to client: "+id);
            MusicLibraryTCPJsonRPCServer myServerThread =
               new MusicLibraryTCPJsonRPCServer(sock,id++,mLib);
            myServerThread.start();
         }
      } catch(Exception e) {e.printStackTrace();}
   }
}

