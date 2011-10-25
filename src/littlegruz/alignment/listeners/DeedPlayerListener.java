package littlegruz.alignment.listeners;

import littlegruz.alignment.AlignmentMain;
import littlegruz.alignment.entities.AlignedPlayer;

import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerLoginEvent;

public class DeedPlayerListener extends PlayerListener{
   private AlignmentMain plugin;
   
   public DeedPlayerListener(AlignmentMain instance){
      plugin = instance;
   }
   
   public void onPlayerLogin(PlayerLoginEvent event){
      if(plugin.getPlayerMap().get(event.getPlayer().getName()) == null){
         plugin.getPlayerMap().put(event.getPlayer().getName(), new AlignedPlayer(event.getPlayer().getName(), 0, 0, 0));
      }
   }
}
