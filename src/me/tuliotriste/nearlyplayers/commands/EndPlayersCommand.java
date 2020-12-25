package me.tuliotriste.nearlyplayers.commands;

import com.google.common.collect.Lists;
import me.tuliotriste.nearlyplayers.Main;
import me.tuliotriste.nearlyplayers.util.CC;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class EndPlayersCommand implements CommandExecutor {

    private Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&cNo console."));
            return true;
        }
        if (!commandSender.hasPermission("tulio.command.endplaayers")) {
            commandSender.sendMessage(CC.translate("&cNo permissions."));
            return true;
        }
        Player player = (Player) commandSender;
        List<String> end = endPlayers(player);
        player.sendMessage(CC.translate("&7&m--------------------------------"));
        player.sendMessage(CC.translate("&6&lEnd Players &7(" + end.size() + ")"));
        end.forEach(players -> player.sendMessage(CC.translate(players)));
        player.sendMessage(CC.translate("&7&m--------------------------------"));
        return false;
    }

    private List<String> endPlayers(Player player) {
        List<String> message = Lists.newArrayList();
        String end_world = plugin.getConfig().getString("end-world-name");
        if (Bukkit.getWorld(end_world).getPlayers().isEmpty()) message.add(" &7- &fEmpty");
        else {
            Bukkit.getWorld(end_world).getPlayers().forEach(players -> {
                if (!players.hasPotionEffect(PotionEffectType.INVISIBILITY))
                    if (player.getWorld().getName().equals(end_world))
                        message.add(" &7- &f" + players.getName() + " &7(" + player.getLocation().distanceSquared(players.getLocation()) + ")");

                    else message.add(" &7- &f" + players.getName());
            });
        }
        return message;
    }
}
