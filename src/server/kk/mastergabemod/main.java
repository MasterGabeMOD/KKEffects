package server.kk.mastergabemod;

import java.io.File;
import java.util.Iterator;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class main extends JavaPlugin
    implements Listener
{

    public main()
    {
    }

    @Override
    public void onEnable()
    {
        File file = new File(getDataFolder() + File.separator + "config.yml");

        if(!file.exists())
        {
            this.getLogger().info("Generating config.yml...");
            this.getLogger().info("Default configuration generated successfully!");
        }


        this.getLogger().info("[KKEffects] has been enabled!");

    }

    @Override
    public void onDisable()
    {

        this.getLogger().info("[KKEffects] has been disabled!");
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        Player player = event.getPlayer();
        if(getConfig().getBoolean("RemovePotions") && player.hasPermission("kkeffects.remove"))
        {
            PotionEffect effect;
            for(Iterator<PotionEffect> iterator = player.getActivePotionEffects().iterator(); iterator.hasNext(); player.removePotionEffect(effect.getType()))
                effect = (PotionEffect)iterator.next();

        }
        
        if(getConfig().getBoolean("JumpEnabled") && player.hasPermission("kkjump.jump"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, getConfig().getInt("JumpTime"), getConfig().getInt("JumpHeight")));
        if(getConfig().getBoolean("SpeedEnabled") && player.hasPermission("kkeffects.speed"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, getConfig().getInt("SpeedTime"), getConfig().getInt("SpeedLevel")));
        if(getConfig().getBoolean("NightVisionEnabled") && player.hasPermission("kkeffects.nightvision"))
            player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, getConfig().getInt("NightVisionTime"), getConfig().getInt("NightVisionLevel")));
    }
}

