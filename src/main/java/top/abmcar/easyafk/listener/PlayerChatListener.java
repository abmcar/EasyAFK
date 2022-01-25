package top.abmcar.easyafk.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import top.abmcar.easyafk.status.PlayerStatus;

public class PlayerChatListener implements Listener {
    @EventHandler
    public void onPlayerMove(AsyncPlayerChatEvent event) {
        PlayerStatus.INSTANCE.updateTime(event.getPlayer());
    }
}
