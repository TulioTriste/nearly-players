package me.tuliotriste.nearlyplayers.commands;

import com.google.common.collect.Lists;
import me.tuliotriste.nearlyplayers.Main;
import me.tuliotriste.nearlyplayers.util.CC;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class NearCommand implements CommandExecutor {

    private Main plugin = Main.getInstance();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(CC.translate("&cNo console."));
            return true;
        }
        if (!commandSender.hasPermission("tulio.command.near")) {
            commandSender.sendMessage(CC.translate("&cNo permissions."));
            return true;
        }
        Player player = (Player) commandSender;
        List<String> nearly = nearlyPlayers(player);
        player.sendMessage(CC.translate("&7&m--------------------------------"));
        player.sendMessage(CC.translate("&6&lNear Players &7(" + nearly.size() + ")"));
        nearly.forEach(players -> player.sendMessage(CC.translate(players)));
        player.sendMessage(CC.translate("&7&m--------------------------------"));
        return false;
    }

    private List<String> nearlyPlayers(Player player) {
        List<String> message = Lists.newArrayList();
        double distance = plugin.getConfig().getDouble("distance-blocks");
        player.getNearbyEntities(distance, distance, distance).forEach(entity -> {
            if (entity instanceof Player) {
                Player target = (Player) entity;
                if (!target.hasPotionEffect(PotionEffectType.INVISIBILITY)) {
                    message.add(" &7- &f" + target.getName() + " &7(" + player.getLocation().distanceSquared(target.getLocation()) + ")");
                }
            }
        });
        return message;
    }
}
