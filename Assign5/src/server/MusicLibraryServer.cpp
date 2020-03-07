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
 * <p/>
 * Purpose: C++ class which serves as server for a collection of students. This
 * class is part of a student collection distributed application that uses JsonRPC.
 * Meant to run on OSX, Debian Linux, and Raspberry Pi Debian.
 * <p/>
 * Ser321 Principles of Distributed Software Systems
 * see http://pooh.poly.asu.edu/Ser321
 * @author Tim Lindquist Tim.Lindquist@asu.edu
 *         Software Engineering, CIDSE, IAFSE, ASU Poly
 * @author Marcus Miller mtmille5@asu.edu
 * @version February 21, 2020
 */

#include <jsonrpccpp/server.h>
#include <jsonrpccpp/server/connectors/httpserver.h>
// the include below requires that you've built the jsonrpccpp package manually
// with the switchs as follows:
// cmake ../ -DTCP_SOCKET_SERVER=YES -DTCP_SOCKET_CLIENT=YES
// make
// make doc    // this will build html docs in the build/doc/html directory
// sudo make install
//#include <jsonrpccpp/server/connectors/tcpsocketserver.h>
#include <json/json.h>
#include <iostream>
#include <stdio.h>
#include <stdlib.h>
#include <cstdlib>
#include <csignal>

#include "musiclibraryserverstub.h"
#include "MusicLibrary.hpp"

using namespace jsonrpc;
using namespace std;

class MusicLibraryServer : public musiclibraryserverstub {
public:
   MusicLibraryServer(AbstractServerConnector &connector, int port, string fileName);
   virtual std::string serviceInfo();
   virtual bool initLibraryFromJsonFile(const string &jsonFileName);
   virtual bool toJsonFile(const string &jsonFileName);
   virtual Json::Value getJsonAlbum(const string &name);
   virtual Json::Value getJsonTrack(const string &albumName, const string &trackName);
   virtual Json::Value getJsonAlbums();
   virtual void addJsonTrack(const string &albumName, const Json::Value &track, const Json::Value &searchedAlbum);
   virtual void addJsonAlbum(const Json::Value &album);
   virtual bool removeAlbum(const string &albumName);
   virtual bool removeTrack(const string &albumName, const string &trackName);

   
private:
   MusicLibrary * library;
   int portNum;
};

MusicLibraryServer::MusicLibraryServer(AbstractServerConnector &connector, int port, string fileName) :
                             musiclibraryserverstub(connector){
   library = new MusicLibrary();
   library->initLibraryFromJsonFile(fileName);
   portNum = port;
}

string MusicLibraryServer::serviceInfo(){
   std::string msg =
                "Music Library Server service.";
   stringstream ss;
   ss << portNum;
   cout << "serviceInfo called. Returning: Music Library Server service."
        << endl;
   return  msg.append(ss.str());
}

bool MusicLibraryServer::toJsonFile(const string &fileName){
   cout << "saving music library to " << fileName << endl;
   bool ret = library->toJsonFile(fileName);
   return ret;
}

bool MusicLibraryServer::initLibraryFromJsonFile(const string &jsonFileName){
   cout << "restoring music library from "<< jsonFileName << endl;
   bool ret = library->initLibraryFromJsonFile(jsonFileName);
   return ret;

}

void MusicLibraryServer::addJsonTrack(const string &albumName, const Json::Value &track, const Json::Value &searchedAlbum){
   cout << "Adding " << track["trackMTM"] << endl;
   library->addJsonTrack(albumName, track, searchedAlbum);
}
void MusicLibraryServer::addJsonAlbum(const Json::Value &album){
   cout << "Adding " << album["nameMTM"] << endl;
   library->addJsonAlbum(album);
}

bool MusicLibraryServer::removeAlbum(const string &albumName){
   cout << "Removing " << albumName << endl;
   bool ret = library->removeAlbum(albumName);
   return ret;
}
bool MusicLibraryServer::removeTrack(const string &albumName, const string &trackName){
   cout << "Removing " << trackName << endl;
   bool ret = library->removeTrack(albumName, trackName);
   return ret;
}

Json::Value MusicLibraryServer::getJsonAlbum(const string &name){
   cout << "Getting " << name << endl;
   return library->getJsonAlbum(name);
}
Json::Value MusicLibraryServer::getJsonTrack(const string &albumName, const string &trackName){
   cout << "Getting " << trackName << endl;
   return library->getJsonTrack(albumName, trackName);
}

Json::Value MusicLibraryServer::getJsonAlbums(){
   Json::FastWriter fw;
   std::string names = fw.write(library->getJsonAlbums());
   cout << "Get names returning: " << names  << endl;
   return library->getJsonAlbums();
}


void exiting(){
   std::cout << "Server has been terminated. Exiting normally" << endl;
   //ss.StopListening();
}

int main(int argc, char * argv[]) {
   // invoke with ./bin/studentRPCServer 8080
   int port = 8080;
   if(argc > 1){
      port = atoi(argv[1]);
   }
   HttpServer httpserver(port);
   MusicLibraryServer ss(httpserver, port, "albums.json");
   // to use tcp sockets instead of http uncomment below (comment above), and the include
   // for tcpsocketserver.h above. If not running locally, you will need to input ip & port
   // from command line for both server and client programs.
   //TcpSocketServer tcpserver("localhost",port);
   //MusicLibraryServer ss(tcpserver, port);
   std::atexit(exiting);
   auto ex = [] (int i) {cout << "server terminating with signal " << i << endl;
                         // ss.StopListening();
                         exit(0);
                         //return 0;
                        };
   // ^C
   std::signal(SIGINT, ex);
   // abort()
   std::signal(SIGABRT, ex);
   // sent by kill command
   std::signal(SIGTERM, ex);
   // ^Z
   std::signal(SIGTSTP, ex);
   cout << "Student collection server listening on port " << port
      //<< " press return/enter to quit." << endl;
        << " use ps to get pid. To quit: kill -9 pid " << endl;
   ss.StartListening();
   while(true){
   }
   //int c = getchar();
   ss.StopListening();
   return 0;
}
