package top.abmcar.easyafk.status;

import org.bukkit.entity.Player;
import top.abmcar.easyafk.config.ConfigData;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PlayerStatus {
    public static PlayerStatus INSTANCE = new PlayerStatus();

    private PlayerStatus() {
    }

    public Integer nowTime = 0;
    public Map<UUID, Integer> lastMoveTime = new HashMap<>();
    public Map<UUID, Boolean> tipsEnabled = new HashMap<>();

    public void updateTime(Player player) {
        lastMoveTime.put(player.getUniqueId(), nowTime);
    }

    public boolean isAFK(Player player) {
        if (!lastMoveTime.containsKey(player.getUniqueId()))
            lastMoveTime.put(player.getUniqueId(), nowTime);
        return nowTime - lastMoveTime.get(player.getUniqueId()) >= ConfigData.INSTANCE.AUTO_AFK_TIME();
    }

    public boolean tipsEnabled(Player player) {
        if (!tipsEnabled.containsKey(player.getUniqueId()))
            tipsEnabled.put(player.getUniqueId(), true);
        return tipsEnabled.get(player.getUniqueId());
    }

    public void changeTipsEnabled(Player player, boolean enabled) {
        tipsEnabled.put(player.getUniqueId(), enabled);
    }
}
