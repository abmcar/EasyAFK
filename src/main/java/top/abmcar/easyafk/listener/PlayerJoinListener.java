package top.abmcar.easyafk.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import top.abmcar.easyafk.status.PlayerStatus;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        PlayerStatus.INSTANCE.updateTime(event.getPlayer());
    }
}
