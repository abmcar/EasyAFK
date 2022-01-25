package top.abmcar.easyafk.config;

import org.bukkit.configuration.file.YamlConfiguration;
import top.abmcar.easyafk.EasyAFK;

import java.util.List;

public class ConfigData {
    public static ConfigData INSTANCE = new ConfigData();

    private ConfigData() {
    }

    private YamlConfiguration config = ConfigUtil.loadYaml(EasyAFK.getPlugin(), "config.yml");

    public void reload() {
        config = ConfigUtil.loadYaml(EasyAFK.getPlugin(), "config.yml");
    }

    public YamlConfiguration getConfigYaml() {
        return config;
    }

    public void setConfigYaml(YamlConfiguration config) {
        this.config = config;
    }

    //自动AFK时间
    public Integer AUTO_AFK_TIME() {
        return config.getInt("autoAFK");
    }

    //优先级列表
    public List<String> getPriorityList() {
        return config.getStringList("priority");
    }

    //是否启用获取提示信息
    public boolean getEnableRewardMessage() {
        return config.getBoolean("RewardMessage.enabled");
    }

    //获取提示信息
    public String getRewardMessage() {
        return config.getString("RewardMessage.message");
    }

    //上限信息
    public String getLimitMessage() {
        return config.getString("LimitMessage");
    }

    public String getLimitMoneyMessage() {
        return config.getString("LimitMoneyMessage");
    }

    public String getLimitExpMessage() {
        return config.getString("LimitExpMessage");
    }

    //根据奖励类型获取权限
    public String getPermission(String rewardName) {
        return config.getString(rewardName + ".permission");
    }

    //奖励金钱
    public Integer getMoney(String rewardName) {
        return config.getInt(rewardName + ".money");
    }

    //奖励经验
    public Integer getExp(String rewardName) {
        return config.getInt(rewardName + ".exp");
    }

    //金钱上限
    public Integer getLimitMoney(String rewardName) {
        return config.getInt(rewardName + ".limitMoney");
    }

    //经验上限
    public Integer getLimitExp(String rewardName) {
        return config.getInt(rewardName + ".limitExp");
    }

    //命令列表
    public List<String> getCommands(String rewardName) {
        return config.getStringList(rewardName + ".commands");
    }
}