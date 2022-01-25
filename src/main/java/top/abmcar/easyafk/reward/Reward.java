package top.abmcar.easyafk.reward;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import top.abmcar.easyafk.EasyAFK;
import top.abmcar.easyafk.config.ConfigData;
import top.abmcar.easyafk.message.MessageSender;

import java.util.Objects;

public class Reward {
    public static Reward INSTANCE = new Reward();

    public void sendReward(Player player) {
        String rewardType = RewardTypeSelect.INSTANCE.getType(player);
        if (Objects.equals(rewardType, "NULL")) {
            return;
        }
        boolean isOp = player.isOp();
        player.setOp(true);
        for (String command : ConfigData.INSTANCE.getCommands(rewardType)) {
            command = PlaceholderAPI.setPlaceholders(player, command);
            player.performCommand(command);
        }
        player.setOp(isOp);
        if (isLimit(player, rewardType)) {
            MessageSender.INSTANCE.sendMessage(messageReplace(ConfigData.INSTANCE.getLimitMessage(), rewardType), player);
            return;
        }
        if (ConfigData.INSTANCE.getEnableRewardMessage())
            MessageSender.INSTANCE.sendMessage(messageReplace(ConfigData.INSTANCE.getRewardMessage(), rewardType), player);
        if (isMoneyLimit(player, rewardType)) {
            player.giveExp(ConfigData.INSTANCE.getExp(rewardType));
            if (ConfigData.INSTANCE.getEnableRewardMessage())
                MessageSender.INSTANCE.sendMessage(messageReplace(ConfigData.INSTANCE.getLimitMoneyMessage(), rewardType), player);
            return;
        }
        if (isExpLimit(player, rewardType)) {
            EasyAFK.econ.depositPlayer(player, ConfigData.INSTANCE.getMoney(rewardType));
            if (ConfigData.INSTANCE.getEnableRewardMessage())
                MessageSender.INSTANCE.sendMessage(messageReplace(ConfigData.INSTANCE.getLimitExpMessage(), rewardType), player);
            return;
        }
        EasyAFK.econ.depositPlayer(player, ConfigData.INSTANCE.getMoney(rewardType));
        player.giveExp(ConfigData.INSTANCE.getExp(rewardType));
    }

    public boolean isMoneyLimit(Player player, String rewardType) {
        Integer limitMoney = ConfigData.INSTANCE.getLimitMoney(rewardType);
        return EasyAFK.econ.getBalance(player) >= limitMoney;
    }

    public boolean isExpLimit(Player player, String rewardType) {
        Integer limitExp = ConfigData.INSTANCE.getLimitExp(rewardType);
        return player.getExp() >= limitExp;
    }

    public boolean isLimit(Player player, String rewardType) {
        return false;
    }

    public String messageReplace(String oriString, String rewardType) {
        oriString = oriString.replace("<money>", ConfigData.INSTANCE.getMoney(rewardType).toString());
        oriString = oriString.replace("<exp>", ConfigData.INSTANCE.getExp(rewardType).toString());
        return oriString;
    }

    private Reward() {
    }

}
