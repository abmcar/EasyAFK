package top.abmcar.easyafk;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import top.abmcar.easyafk.commander.Commander;
import top.abmcar.easyafk.config.ConfigUtil;
import top.abmcar.easyafk.listener.PlayerChatListener;
import top.abmcar.easyafk.listener.PlayerJoinListener;
import top.abmcar.easyafk.listener.PlayerMoveListener;
import top.abmcar.easyafk.runnable.PlayerStatusRunnable;

public final class EasyAFK extends JavaPlugin {
    private static Plugin plugin;
    public static Economy econ = null;
    public static Permission perms = null;
    public static Plugin PlaceholderAPI = Bukkit.getPluginManager().getPlugin("PlaceholderAPI");

    @Override
    public void onEnable() {
        if (!setupEconomy() || !setupPermissions()) {
            this.getLogger().info("- Vault未找到,插件卸载.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }
        Bukkit.getPluginCommand("EasyAFK").setExecutor(new Commander());
        plugin = this;
        Bukkit.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerMoveListener(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerChatListener(), this);
        PlayerStatusRunnable playerStatusRunnable = new PlayerStatusRunnable();
        playerStatusRunnable.runTaskTimer(this, 1, 20);
        ConfigUtil.loadConfig(this, "config.yml");
        this.getLogger().info("- 插件已加载.");
    }

    @Override
    public void onDisable() {
        this.getLogger().info("- 插件已卸载.");
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupPermissions() {
        RegisteredServiceProvider<Permission> rsp = getServer().getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public static Plugin getPlugin() {
        return plugin;
    }


}
