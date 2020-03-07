package main.java.bus;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Result;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.graphdb.ResourceIterator;

import java.io.File;
import java.util.HashMap;
import java.util.Map;



public class BusSchedulerNeo4j
{
	public static GraphDatabaseService graphDb;
	
    private static void registerShutdownHook( final GraphDatabaseService graphDb )
    {
        // Registers a shutdown hook for the Neo4j instance so that it
        // shuts down nicely when the VM exits (even if you "Ctrl-C" the
        // running application).
        Runtime.getRuntime().addShutdownHook( new Thread()
        {
            @Override
            public void run()
            {
            	System.out.printf("DB Shutdown\n");
                graphDb.shutdown();
            }
        } );
    }
    
    public static void start_neo4jDB(){
    	graphDb = null;
    	try {
    		graphDb = new GraphDatabaseFactory().newEmbeddedDatabase(new File("Neo4jdb"));
    	}
    	catch(Exception e) {
    		e.printStackTrace();
    	}
        registerShutdownHook(graphDb);
        System.out.printf("DB started\n");
        add_bus_constraint();
        add_driver_constraint();
        add_bus_stop_constraint();
        add_segment_constraint();
        add_path_constraint();
        add_drives_constraint();
        add_takes_constraint();
        //on constraint
        //between constraint
        //made_of constraint
        
        
        //add_bus(27, 30, false);
        //delete_bus(27);

        //match_buses();

        //BusSchedulerNeo4j.load_bus_cvs();

        
    }
    
    public static void load_bus_cvs(){
        //load bus
        try (Transaction tx = graphDb.beginTx()){
                Result result = graphDb.execute(
                        "LOAD CSV WITH HEADERS FROM  'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=0' " 
                        + "AS line "
                        + "MERGE (:BUS { BUS_ID: toInteger(line.BUS_ID), "
                        + "CAPACITY: toInteger(line.CAPACITY), "
                        + "IS_OPERATIONAL: toBoolean(line.IS_OPERATIONAL)})" );
                while ( result.hasNext() )
                {
                    HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                    for ( String key : result.columns() )
                    {
                        System.out.printf( "%s = %s", key, row.get( key ) );
                    }
                    System.out.printf( "%n");
                    result.close();
                }
                tx.success();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
  //load driver
    public static void load_driver_cvs(){
        
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=981714178' AS line "
                    + "MERGE (:DRIVER { DRIVER_ID: toInteger(line.DRIVER_ID), NAME: line.NAME, "
                    + "PHONE_NUMBER: toInteger(line.PHONE_NUMBER), SHIFT: line.SHIFT}) ");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //load bus stop
    public static void load_bus_stop_cvs(){
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=311717044' AS line "
                    + "MERGE (:BUS_STOP { BUS_STOP_ID: toInteger(line.BUS_STOP_ID), LONGITUDE: toInteger(line.LONGITUDE), "
                    + "LATITUDE: toInteger(line.LATITUDE)}) " );
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //load segment
    public static void load_segment_cvs(){    
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=521537797' AS line "
                    + "MERGE (:SEGMENT { SEGMENT_ID: toInteger(line.SEGMENT_ID), DURATION: toInteger(line.DURATION), "
                    + "LENGTH: toInteger(line.LENGTH)}) ");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        //load path

    public static void load_path_cvs(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=36308958' AS line "
                    + "MERGE (:PATH { PATH_ID: toInteger(line.PATH_ID), DURATION: toInteger(line.DURATION), "
                    + "LENGTH: toInteger(line.LENGTH)})" );
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //create constraint on bus
    public static void add_bus_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "CREATE CONSTRAINT ON (b:BUS) ASSERT b.BUS_ID IS UNIQUE;");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //create constraint on driver
    public static void add_driver_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "CREATE CONSTRAINT ON (d:DRIVER) ASSERT d.DRIVER_ID IS UNIQUE;");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    //create constraint on bus stop
    public static void add_bus_stop_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "CREATE CONSTRAINT ON (bs:BUS_STOP) ASSERT bs.BUS_STOP_ID IS UNIQUE;");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //create constraint on segment
    public static void add_segment_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "CREATE CONSTRAINT ON (s:SEGMENT) ASSERT s.SEGMENT_ID IS UNIQUE;");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //create constraint on path
    public static void add_path_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "CREATE CONSTRAINT ON (p:PATH) ASSERT p.PATH_ID IS UNIQUE;");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        

        //create drives

    public static void add_drives_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=1107580017' AS line "
                   + "MATCH (d:DRIVER {DRIVER_ID: toInteger(line.DRIVER_ID)}) "
                   + "MATCH (b:BUS {BUS_ID: toInteger(line.BUS_ID)}) "
                   + "MERGE (d)-[:DRIVES {DRIVES_ID: line.DRIVER_ID + line.BUS_ID + line.DATE_TIME, "
                   + "DATE_TIME: datetime(line.DATE_TIME)}]->(b)"
                   + "MERGE (b)-[:DRIVES {DRIVES_ID: line.DRIVER_ID + line.BUS_ID + line.DATE_TIME, "
                   + "DATE_TIME: datetime(line.DATE_TIME)}]->(d)");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        

    //load takes

    public static void add_takes_constraint(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=1029307145' AS line "
                    + "MATCH (p:PATH {PATH_ID: toInteger(line.PATH_ID)}) "
                    + "MATCH (d:DRIVER {DRIVER_ID: toInteger(line.DRIVER_ID)}) "
                    + "MERGE (d)-[:TAKES {TAKES_ID: line.PATH_ID + line.DRIVER_ID + line.DATE_TIME, "
                    + "DATE_TIME: datetime(line.DATE_TIME)}]->(p) "
                    + "MERGE (p)-[:TAKES {TAKES_ID: line.PATH_ID + line.DRIVER_ID + line.DATE_TIME, "
                    + "DATE_TIME: datetime(line.DATE_TIME)}]->(d)" );
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        

    //load on

    public static void load_on_cvs(){ 
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=1429936831' AS line "
                    + "MATCH (p:PATH {PATH_ID: toInteger(line.PATH_ID)}) "
                    + "MATCH (b:BUS {BUS_ID: toInteger(line.BUS_ID)}) "
                    + "MERGE (b)-[:ON {ON_ID: line.PATH_ID + line.BUS_ID + line.DATE_TIME, "
                    + "DATE_TIME: datetime(line.DATE_TIME)}]->(p) "
                    + "MERGE (p)-[:ON {ON_ID: line.PATH_ID + line.BUS_ID + line.DATE_TIME, "
                    + "DATE_TIME: datetime(line.DATE_TIME)}]->(b) " );
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //load made of

    public static void load_made_of_cvs(){
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=1418176721' AS line "
                    + "MATCH (p:PATH {PATH_ID: toInteger(line.PATH_ID)}) "
                    + "MATCH (s:SEGMENT {SEGMENT_ID: toInteger(line.SEGMENT_ID)}) "
                    + "MERGE (p)-[:MADE_OF {MADE_OF_ID: line.PATH_ID + line.SEGMENT_ID + line.SEQUENCE_NUMBER, "
                    + "SEQUENCE_NUMBER: toInteger(line.SEQUENCE_NUMBER)}]->(s) "
                    + "MERGE (s)-[:MADE_OF {MADE_OF_ID: line.PATH_ID + line.SEGMENT_ID + line.SEQUENCE_NUMBER, "
                    + "SEQUENCE_NUMBER: toInteger(line.SEQUENCE_NUMBER)}]->(p)" );
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
        
    //load between
    public static void load_between_cvs(){
        try (Transaction tx = graphDb.beginTx()){
            Result result = graphDb.execute(
                    "LOAD CSV WITH HEADERS FROM 'https://docs.google.com/spreadsheets/u/1/d/1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE/export?format=csv&id=1u3Q9F4bC7IZ31NCECrIoQDwgnwEBJMc5mX-sHY386VE&gid=915397771' AS line "
                    + "MATCH (bs:BUS_STOP {BUS_STOP_ID: toInteger(line.BUS_STOP_ID)}) "
                    + "MATCH (s:SEGMENT {SEGMENT_ID: toInteger(line.SEGMENT_ID)}) "
                    + "MERGE (bs)-[:BETWEEN {BETWEEN_ID: line.BUS_STOP_ID + line.SEGMENT_ID + line.IS_STARTING_BUS_STOP, "
                    + "IS_STARTING_BUS_STOP: toBoolean(line.IS_STARTING_BUS_STOP)}]->(s) "
                    + "MERGE (s)-[:BETWEEN {BETWEEN_ID: line.BUS_STOP_ID + line.SEGMENT_ID + line.IS_STARTING_BUS_STOP, "
                    + "IS_STARTING_BUS_STOP: toBoolean(line.IS_STARTING_BUS_STOP)}]->(bs) ");
            while ( result.hasNext() )
            {
                HashMap<String, Object> row = (HashMap<String, Object>) result.next();
                for ( String key : result.columns() )
                {
                    System.out.printf( "%s = %s", key, row.get( key ) );
                }
                System.out.printf( "%n");
            }
            result.close();
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    // match Buses
    public static Map<Integer, Map<String, Object>> match_buses(){
        
        String query = "MATCH (b:BUS) RETURN b.IS_OPERATIONAL AS `3.IS OPERATIONAL`, b.CAPACITY AS `2.CAPACITY`, b.BUS_ID AS `1.BUS ID` ORDER BY b.BUS_ID";
        Result result = null;
        Map<Integer, Map<String, Object>> table = new HashMap<Integer, Map<String, Object>>();
        int columnNum = 0;
        try (Transaction tx = graphDb.beginTx()){
            result = graphDb.execute(query);
            
            while ( result.hasNext() )
            {
                Map<String, Object> row = (Map<String, Object>) result.next();
                table.put(columnNum++, row);
            }
            result.close();
            
            tx.success();
            

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return table;

    }
    
    // add bus
    public static void add_bus(int bus_id, int capacity, boolean is_operational){
        try (Transaction tx = graphDb.beginTx()){
            Node n = graphDb.createNode(Label.label( "BUS" ));

            n.setProperty( "BUS_ID", bus_id);
            n.setProperty( "CAPACITY", capacity);
            n.setProperty("IS_OPERATIONAL", is_operational);
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    // delete bus
    public static void delete_bus(int bus_id){
        try (Transaction tx = graphDb.beginTx()){

            String query = "MATCH (b:BUS) WHERE b.BUS_ID = " + String.valueOf(bus_id) + " DELETE b";
            graphDb.execute(query); 
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }
    // update bus
    public static void update_bus(int bus_id, int capacity, boolean is_operational){
        try (Transaction tx = graphDb.beginTx()){
            
            String query = "MATCH (b {BUS_ID: " + String.valueOf(bus_id) + "}) "
                   + "SET b.IS_OPERATIONAL =  " + String.valueOf(is_operational)
                   + " ,b.CAPACITY = " + String.valueOf(capacity);
            graphDb.execute(query); 
            tx.success();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    // match segments
    public static Map<Integer, Map<String, Object>> match_segments(){
        
        String query = "MATCH (s:SEGMENT) RETURN s.SEGMENT_ID AS `1.SEGMENT_ID`, s.DURATION AS `2.DURATION`, s.LENGTH AS `3.LENGTH` ORDER BY s.SEGMENT_ID";
        Result result = null;
        Map<Integer, Map<String, Object>> table = new HashMap<Integer, Map<String, Object>>();
        int columnNum = 0;
        try (Transaction tx = graphDb.beginTx()){
            result = graphDb.execute(query);
            
            while ( result.hasNext() )
            {
                Map<String, Object> row = (Map<String, Object>) result.next();
                table.put(columnNum++, row);
            }
            result.close();
            
            tx.success();
            

        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return table;

    }
    
  //US46 - Task 66 - Implement methods to pull data on individual buses
    // load bus
    public static Result get_buses() {
    	try (Transaction tx = graphDb.beginTx()){
    		Result resultBus = graphDb.execute("MATCH(n) RETURN n");//Query does not work and needs to be updated
    		String resultBusS = resultBus.resultAsString();
    		//System.out.println(resultBusS);
    		return resultBus;
    	}
    	catch (Exception e) {
            e.printStackTrace();
            return null;
        }
		
    }
}