package me.tuliotriste.nearlyplayers;

import lombok.Getter;
import me.tuliotriste.nearlyplayers.commands.EndPlayersCommand;
import me.tuliotriste.nearlyplayers.commands.NearCommand;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class Main extends JavaPlugin {

    @Getter private static Main instance;

    @Override
    public void onEnable() {
        super.onEnable();
        instance = this;
        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        registerCommands();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    private void registerCommands() {
        getCommand("near").setExecutor(new NearCommand());
        getCommand("endplayers").setExecutor(new EndPlayersCommand());
    }
}
