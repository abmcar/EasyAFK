package top.abmcar.easyafk.commander;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import top.abmcar.easyafk.config.ConfigData;
import top.abmcar.easyafk.status.PlayerStatus;

public class Commander implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 1) {
            if (args[0].equals("reload")) {
                ConfigData.INSTANCE.reload();
                sender.sendMessage("EasyAFK已经重载配置文件.");
                return true;
            }
            if (args[0].equals("help")) {
                sender.sendMessage("/EasyAFK reload - 重载配置文件");
                sender.sendMessage("/EasyAFK on -  打开提示信息(玩家可用)");
                sender.sendMessage("/EasyAFK off - 关闭提示信息(玩家可用)");
                return true;
            }
            if (sender instanceof Player) {
                if (args[0].equals("off")) {
                    PlayerStatus.INSTANCE.changeTipsEnabled((Player) sender, false);
                    sender.sendMessage("提示信息已关闭,输入/EasyAFK on打开提示信息.");
                    return true;
                }
                if (args[0].equals("on")) {
                    PlayerStatus.INSTANCE.changeTipsEnabled((Player) sender, true);
                    sender.sendMessage("提示信息已打开,输入/EasyAFK off打开提示信息.");
                    return true;
                }
                return false;
            }
        }
        return false;
    }
}
