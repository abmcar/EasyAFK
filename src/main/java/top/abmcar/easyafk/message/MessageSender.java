package top.abmcar.easyafk.message;

import org.bukkit.entity.Player;
import top.abmcar.easyafk.EasyAFK;
import me.clip.placeholderapi.PlaceholderAPI;

public class MessageSender {
    public static MessageSender INSTANCE = new MessageSender();

    public void sendMessage(String message, Player player) {
        message = PlaceholderAPI.setPlaceholders(player,message);
        player.sendMessage(message);
    }

    public MessageSender() {
    }

}
