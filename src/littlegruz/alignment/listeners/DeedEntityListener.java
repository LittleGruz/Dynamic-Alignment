package littlegruz.alignment.listeners;

import littlegruz.alignment.AlignmentMain;

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
         if(entityDamageEvent.getDamager() instanceof Player && event.getEntity() instanceof Player){
            plugin.getServer().broadcastMessage("Fatality!");
         }
      }
   }
}
