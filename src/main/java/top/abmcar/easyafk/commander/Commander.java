package top.abmcar.easyafk.commander;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import top.abmcar.easyafk.config.ConfigData;
import top.abmcar.easyafk.message.MessageSender;

public class Commander implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                ConfigData.INSTANCE.reload();
                sender.sendMessage("EasyAFK已经重载配置文件.");
                return true;
            }
        }
        return false;
    }
}
