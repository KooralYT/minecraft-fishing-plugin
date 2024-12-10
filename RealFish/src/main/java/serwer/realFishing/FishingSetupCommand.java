package serwer.realFishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class FishingSetupCommand implements CommandExecutor, Listener {

    private final Map<UUID, Inventory> rodInventories = new HashMap<>();
    private final Map<Player, Long> castStartTimes = new HashMap<>();
    private final Map<Player, BukkitRunnable> castTasks = new HashMap<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            openFishingSetupGUI(player);
            return true;
        }
        return false;
    }

    private void openFishingSetupGUI(Player player) {
        ItemStack rod = player.getInventory().getItemInMainHand();
        if (rod != null && rod.getType() == Material.FISHING_ROD) {
            ItemMeta meta = rod.getItemMeta();
            if (meta != null) {
                NamespacedKey key = new NamespacedKey("realfishing", "rod_uuid");
                UUID rodUUID;
                if (meta.getPersistentDataContainer().has(key, PersistentDataType.STRING)) {
                    rodUUID = UUID.fromString(meta.getPersistentDataContainer().get(key, PersistentDataType.STRING));
                } else {
                    rodUUID = UUID.randomUUID();
                    meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, rodUUID.toString());
                    rod.setItemMeta(meta);
                }

                Inventory gui = rodInventories.get(rodUUID);
                if (gui == null) {
                    gui = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Fishing Setup");
                    rodInventories.put(rodUUID, gui);
                }
                player.openInventory(gui);
            }
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.BOLD + "Fishing Setup")) {
            ItemStack currentItem = event.getCurrentItem();
            if (currentItem != null) {
                int slot = event.getSlot();
                if (slot >= 0 && slot <= 4) {

                } else if (slot >= 9) {

                } else {
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = player.getInventory().getItemInMainHand();
        if (item != null && item.getType() == Material.FISHING_ROD) {
            switch (event.getAction()) {
                case RIGHT_CLICK_AIR:
                    event.setCancelled(true);
                    startCasting(player);
                    break;
                case LEFT_CLICK_AIR:
                case LEFT_CLICK_BLOCK:
                    openFishingSetupGUI(player);
                    break;
                default:
                    break;
            }
        }
    }

    @EventHandler
    public void onPlayerItemHeld(PlayerItemHeldEvent event) {
        Player player = event.getPlayer();
        if (castTasks.containsKey(player)) {
            castTasks.get(player).cancel();
            castTasks.remove(player);
            castStartTimes.remove(player);
            player.sendMessage(ChatColor.RED + "Casting cancelled.");
        }
    }

    private void startCasting(Player player) {
        castStartTimes.put(player, System.currentTimeMillis());
        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                long startTime = castStartTimes.get(player);
                long elapsedTime = System.currentTimeMillis() - startTime;
                double progress = Math.min(1.0, elapsedTime / 3000.0); // 3 seconds to full charge
                player.sendActionBar(ChatColor.GREEN + "Casting: " + (int) (progress * 100) + "%");

                if (!player.isHandRaised()) {
                    executeCast(player, progress);
                    this.cancel();
                }
            }
        };
        task.runTaskTimer(RealFishing.getInstance(), 0, 1);
        castTasks.put(player, task);
    }

    private void executeCast(Player player, double progress) {
        long startTime = castStartTimes.remove(player);
        long elapsedTime = System.currentTimeMillis() - startTime;
        double maxDistance = calculateMaxDistance(player);
        double castDistance = maxDistance * progress;
        player.sendMessage(ChatColor.GREEN + "You cast your fishing rod " + castDistance + " meters!");
        castTasks.remove(player);
    }

    private double calculateMaxDistance(Player player) {


        return 50.0;
    }
}