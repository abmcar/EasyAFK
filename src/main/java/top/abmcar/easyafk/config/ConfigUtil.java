package top.abmcar.easyafk.config;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.File;
import java.io.IOException;

public class ConfigUtil {
    @SuppressWarnings("all")
    public static YamlConfiguration loadYaml(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            plugin.saveResource(configName, true);
        }
        return YamlConfiguration.loadConfiguration(configFile);
    }

    @SuppressWarnings("all")
    public static Config loadConfig(Plugin plugin, String configName) {
        File pluginDataFolder = plugin.getDataFolder();
        File configFile = new File(pluginDataFolder, configName);
        if (!pluginDataFolder.exists()) pluginDataFolder.mkdir();
        if (!configFile.exists()) {
            try {
                if (!configName.equalsIgnoreCase("config.yml"))
                    configFile.createNewFile();
                else
                    plugin.saveResource(configName, false);
            } catch (Exception e) {
                plugin.getLogger().info("创建" + configFile + "文件失败");
                plugin.getLogger().info(e.toString());
            }
        }
        return new Config(pluginDataFolder, configFile, YamlConfiguration.loadConfiguration(configFile));
    }

    public static void saveFile(Config config) {
        YamlConfiguration yaml = config.getConfigYaml();
        try {
            yaml.save(config.getConfigFile());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
