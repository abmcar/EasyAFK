package top.abmcar.easyafk.listener;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import top.abmcar.easyafk.status.PlayerStatus;

public class PlayerMoveListener implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        PlayerStatus.INSTANCE.updateTime(event.getPlayer());
    }
}
