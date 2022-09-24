package me.akaishin.beddup;

/*Imports others */
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;

/*Commands Inport */
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public final class BedDupe extends JavaPlugin {


////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new BedDupeListener(), this);
        getCommand("beddupereload").setExecutor(new ReloadCommand());
    }
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    private final class BedDupeListener implements Listener {
    @EventHandler
    public void onPlayer(PlayerInteractEvent event) {
        Player p = event.getPlayer();
        Block block = event.getClickedBlock();
        if (block.getType().equals(Material.BED_BLOCK) && event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            int rng = (int)Math.round(Math.random() * 100);
                if (rng < getConfig().getInt("probability-percentage")) {
                    block.getWorld().dropItemNaturally(block.getLocation().add(0, 1, 0), p.getInventory().getItemInMainHand());
            }
        }
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////




////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
private final class ReloadCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender.isOp()) {
            reloadConfig();
            return true;
        }
        return false;
    }
}
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}







