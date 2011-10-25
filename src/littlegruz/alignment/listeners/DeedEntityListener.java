package littlegruz.alignment.listeners;

import littlegruz.alignment.AlignmentMain;
import littlegruz.alignment.entities.AlignedPlayer;
import littlegruz.alignment.entities.Deed;

import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityListener;

public class DeedEntityListener extends EntityListener{
   private AlignmentMain plugin;
   
   public DeedEntityListener(AlignmentMain instance){
      plugin = instance;
   }
   
   public void onEntityDeath(EntityDeathEvent event){
      if(event.getEntity().getLastDamageCause() instanceof EntityDamageByEntityEvent){
         EntityDamageByEntityEvent entityDamageEvent = (EntityDamageByEntityEvent) event.getEntity().getLastDamageCause();
         if(entityDamageEvent.getDamager() instanceof Player){
            Player player = (Player) entityDamageEvent.getDamager();
            plugin.getServer().broadcastMessage(player.getName());
            AlignedPlayer ap = plugin.getPlayerMap().get(player.getName());
            Deed deed;
            if(event.getEntity() instanceof Player){
               deed = plugin.getAlignmentMap().get("PlayerKill");
               ap.setGood(ap.getGood() + deed.getGood());
               ap.setBad(ap.getBad() + deed.getBad());
               plugin.getServer().broadcastMessage("Fatality!");
            }
            else if(event.getEntity() instanceof Monster){
               deed = plugin.getAlignmentMap().get("MonsterKill");
               ap.setGood(ap.getGood() + deed.getGood());
               ap.setBad(ap.getBad() + deed.getBad());
               plugin.getServer().broadcastMessage("M-m-m-m-m-monster kill!");
            }
            else if(event.getEntity() instanceof Animals){
               deed = plugin.getAlignmentMap().get("AnimalKill");
               ap.setGood(ap.getGood() + deed.getGood());
               ap.setBad(ap.getBad() + deed.getBad());
               plugin.getServer().broadcastMessage("Animal kill!");
            }
         }
      }
   }
}
