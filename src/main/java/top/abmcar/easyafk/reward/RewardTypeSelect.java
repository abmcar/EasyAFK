package top.abmcar.easyafk.reward;

import org.bukkit.entity.Player;
import top.abmcar.easyafk.EasyAFK;
import top.abmcar.easyafk.config.ConfigData;

import java.util.List;

public class RewardTypeSelect {
    public static RewardTypeSelect INSTANCE = new RewardTypeSelect();

    public String getType(Player player) {
        List<String> priorityList = ConfigData.INSTANCE.getPriorityList();
        for (String nowType : priorityList) {
            String nowPermission = ConfigData.INSTANCE.getPermission(nowType);
            if (nowPermission == null)
                return nowType;
            if (EasyAFK.perms.has(player, nowPermission))
                return nowType;
        }
        return "NULL";
    }

    private RewardTypeSelect() {
    }
}
