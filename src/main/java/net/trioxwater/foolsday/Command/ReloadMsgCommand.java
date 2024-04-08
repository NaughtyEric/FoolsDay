package net.trioxwater.foolsday.Command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadMsgCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender.isOp()) {
            commandSender.sendMessage("Reload complete!");
            return true;
        } else {
            commandSender.sendMessage("You don't have permission to do this!");
            return false;
        }
    }
}
