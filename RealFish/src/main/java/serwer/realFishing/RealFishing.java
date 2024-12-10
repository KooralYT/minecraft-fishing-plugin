package serwer.realFishing;

import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class RealFishing extends JavaPlugin {

    private static RealFishing instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new FishingSetupCommand(), this);
        Objects.requireNonNull(this.getCommand("zestaw")).setExecutor(new FishingSetupCommand());
        Objects.requireNonNull(this.getCommand("wedka")).setExecutor(new FishingRodCommand());
        Objects.requireNonNull(this.getCommand("kolowrotek")).setExecutor(new ReelCommand());
        Objects.requireNonNull(this.getCommand("zylka")).setExecutor(new FishingLineCommand());
        Objects.requireNonNull(this.getCommand("haczyk")).setExecutor(new HookCommand());
        Objects.requireNonNull(this.getCommand("przyneta")).setExecutor(new BaitCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static RealFishing getInstance() {
        return instance;
    }
}