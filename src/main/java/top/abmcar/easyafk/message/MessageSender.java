package top.abmcar.easyafk.message;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import top.abmcar.easyafk.status.PlayerStatus;

public class MessageSender {
    public static MessageSender INSTANCE = new MessageSender();

    public void sendMessage(String message, Player player) {
        if (!PlayerStatus.INSTANCE.tipsEnabled(player))
            return;
        message = PlaceholderAPI.setPlaceholders(player, message);
        player.sendMessage(message);
    }

    public MessageSender() {
    }

}
