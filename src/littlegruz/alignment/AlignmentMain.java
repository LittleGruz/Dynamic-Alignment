package littlegruz.alignment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.Map.Entry;
import java.util.logging.Logger;

import littlegruz.alignment.entities.Deed;
import littlegruz.alignment.entities.AlignedPlayer;
import littlegruz.alignment.listeners.DeedEntityListener;
import littlegruz.alignment.listeners.DeedPlayerListener;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Event;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class AlignmentMain extends JavaPlugin{
   Logger log = Logger.getLogger("This is MINECRAFT!");
   private HashMap<String, Deed> alignmentMap;
   private HashMap<String, AlignedPlayer> playerMap;
   private final DeedEntityListener entityListener = new DeedEntityListener(this);
   private final DeedPlayerListener playerListener = new DeedPlayerListener(this);
   private File alignmentFile;
   private File playerFile;
   
   public void onDisable(){
      //Save the deed attributes
      try{
         BufferedWriter bw = new BufferedWriter(new FileWriter(alignmentFile));
         Iterator<Map.Entry<String, Deed>> it = alignmentMap.entrySet().iterator();
         
         bw.write("<Deed Name> <Good Score> <Bad Score>\n");
         while(it.hasNext()){
            Entry<String, Deed> mp = it.next();
            bw.write(mp.getValue().getName() + " "
                  + mp.getValue().getGood() + " "
                  + mp.getValue().getBad() + "\n");
         }
         bw.close();
      }catch(IOException e){
         log.info("Error saving alignment file");
      }
      
      //Save the player data
      try{
         BufferedWriter bw = new BufferedWriter(new FileWriter(playerFile));
         Iterator<Map.Entry<String, AlignedPlayer>> it = playerMap.entrySet().iterator();
         
         while(it.hasNext()){
            Entry<String, AlignedPlayer> mp = it.next();
            bw.write(mp.getValue().getName() + " "
                  + mp.getValue().getGood() + " "
                  + mp.getValue().getBad() + " "
                  + mp.getValue().getRank() + "\n");
         }
         bw.close();
      }catch(IOException e){
         log.info("Error saving player file");
      }
      log.info("Dynamic Alignment has shutdown.");
   }

   public void onEnable(){
    //Create the directory and files if needed
      new File(getDataFolder().toString()).mkdir();
      alignmentFile = new File(getDataFolder().toString() + "/deedAlignments.txt");
      playerFile = new File(getDataFolder().toString() + "/playerData.txt");
      
      //Load the alignment data
      alignmentMap = new HashMap<String, Deed>();
      try{
         BufferedReader br = new BufferedReader(new FileReader(alignmentFile));
         StringTokenizer st;
         String input;
         String name;
         while((input = br.readLine()) != null){
            if(input.compareToIgnoreCase("<Deed Name> <Good Score> <Bad Score>") == 0){
               continue;
            }
            st = new StringTokenizer(input, " ");
            name = st.nextToken();
            alignmentMap.put(name, new Deed(name, Integer.getInteger(st.nextToken()), Integer.getInteger(st.nextToken())));
         }
         
      }catch(FileNotFoundException e){
         log.info("No original alignment file, a new one will be created.");
         //Load default values if no file found
         alignmentMap.put("PlayerKill", new Deed("PlayerKill", 0, 0));
         alignmentMap.put("MonsterKill", new Deed("MonsterKill", 0, 0));
         alignmentMap.put("Give", new Deed("Give", 0, 0));
      }catch(IOException e){
         log.info("Error reading alignment file");
      }catch(Exception e){
         log.info("Incorrectly formatted alignment file");
      }
      
      //Load the player data
      playerMap = new HashMap<String, AlignedPlayer>();
      try{
         BufferedReader br = new BufferedReader(new FileReader(playerFile));
         StringTokenizer st;
         String input;
         String name;
         while((input = br.readLine()) != null){
            st = new StringTokenizer(input, " ");
            name = st.nextToken();
            playerMap.put(name, new AlignedPlayer(name, Integer.getInteger(st.nextToken()), Integer.getInteger(st.nextToken()), Integer.getInteger(st.nextToken())));
         }
         
      }catch(FileNotFoundException e){
         log.info("No original player file, a new one will be created.");
      }catch(IOException e){
         log.info("Error reading player file");
      }catch(Exception e){
         log.info("Incorrectly formatted player file");
      }
      
      //Set up the listeners
      PluginManager pm = this.getServer().getPluginManager();
      pm.registerEvent(Event.Type.ENTITY_DEATH, entityListener, Event.Priority.Normal, this);
      pm.registerEvent(Event.Type.PLAYER_LOGIN, playerListener, Event.Priority.Normal, this);
      
      log.info("Dynamic Alignment v0.1 is enabled");
   }

   public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
      if(commandLabel.compareToIgnoreCase("displayladder") == 0){
         Iterator<Map.Entry<String, AlignedPlayer>> it = playerMap.entrySet().iterator();

         sender.sendMessage("Name--GoodScale--BadScale--Rank");
         while(it.hasNext()){
            Entry<String, AlignedPlayer> mp = it.next();
            sender.sendMessage(mp.getValue().getName() + "--" + mp.getValue().getGood() + "--" + mp.getValue().getBad() + "--" + mp.getValue().getRank());
         }
      }
      else if(commandLabel.compareToIgnoreCase("displaydeeds") == 0){
         Iterator<Map.Entry<String, Deed>> it = alignmentMap.entrySet().iterator();

         sender.sendMessage("Name--GoodScale--BadScale");
         while(it.hasNext()){
            Entry<String, Deed> mp = it.next();
            sender.sendMessage(mp.getValue().getName() + "--" + mp.getValue().getGood() + "--" + mp.getValue().getBad());
         }
      }
      return true;
   }

   public HashMap<String, Deed> getAlignmentMap(){
      return alignmentMap;
   }

   public HashMap<String, AlignedPlayer> getPlayerMap(){
      return playerMap;
   }
}
