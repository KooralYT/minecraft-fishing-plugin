package serwer.realFishing;

import org.bukkit.boss.BossBar;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;

public class PowerBar {
    private final BossBar bossBar;

    public PowerBar(Player player) {
        bossBar = Bukkit.createBossBar("Power", BarColor.GREEN, BarStyle.SOLID);
        bossBar.addPlayer(player);
    }

    public void setProgress(double progress) {
        bossBar.setProgress(progress);
    }

    public void remove() {
        bossBar.removeAll();
    }
}