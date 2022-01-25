package top.abmcar.easyafk.runnable;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import top.abmcar.easyafk.reward.Reward;
import top.abmcar.easyafk.status.PlayerStatus;

import java.util.Collection;

public class PlayerStatusRunnable extends BukkitRunnable {
    @Override
    public void run() {
        PlayerStatus.INSTANCE.nowTime++;
        Collection<? extends Player> Players = Bukkit.getOnlinePlayers();
        for (Player player : Players) {
            if (PlayerStatus.INSTANCE.isAFK(player)) {
                Reward.INSTANCE.sendReward(player);
            }
        }
    }
}
