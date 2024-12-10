package serwer.realFishing;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FishingLineCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            // Create the fishing line items
            List<ItemStack> lines = new ArrayList<>();
            lines.add(createFishingLineItem("Nylon 0.18mm 100m", "Nylon", 0.18, 100, 4, "średnia"));
            lines.add(createFishingLineItem("Nylon 0.20mm 150m", "Nylon", 0.20, 150, 6, "średnia"));
            lines.add(createFishingLineItem("Nylon 0.22mm 200m", "Nylon", 0.22, 200, 8, "średnia"));
            lines.add(createFishingLineItem("Nylon 0.25mm 250m", "Nylon", 0.25, 250, 10, "średnia"));
            lines.add(createFishingLineItem("Nylon 0.30mm 300m", "Nylon", 0.30, 300, 12, "średnia"));
            lines.add(createFishingLineItem("Fluorocarbon 0.18mm 50m", "Fluorocarbon", 0.18, 50, 4, "wysoka"));
            lines.add(createFishingLineItem("Fluorocarbon 0.22mm 100m", "Fluorocarbon", 0.22, 100, 6, "wysoka"));
            lines.add(createFishingLineItem("Fluorocarbon 0.25mm 150m", "Fluorocarbon", 0.25, 150, 8, "wysoka"));
            lines.add(createFishingLineItem("Fluorocarbon 0.30mm 200m", "Fluorocarbon", 0.30, 200, 10, "wysoka"));
            lines.add(createFishingLineItem("Fluorocarbon 0.35mm 250m", "Fluorocarbon", 0.35, 250, 12, "wysoka"));
            lines.add(createFishingLineItem("Spleciona 0.10mm 50m", "Spleciona", 0.10, 50, 3, "wysoka"));
            lines.add(createFishingLineItem("Spleciona 0.12mm 100m", "Spleciona", 0.12, 100, 5, "wysoka"));
            lines.add(createFishingLineItem("Spleciona 0.15mm 150m", "Spleciona", 0.15, 150, 7, "bardzo wysoka"));
            lines.add(createFishingLineItem("Spleciona 0.20mm 200m", "Spleciona", 0.20, 200, 10, "bardzo wysoka"));
            lines.add(createFishingLineItem("Spleciona 0.25mm 250m", "Spleciona", 0.25, 250, 12, "bardzo wysoka"));
            lines.add(createFishingLineItem("Kevlar 0.18mm 100m", "Kevlar", 0.18, 100, 6, "bardzo wysoka"));
            lines.add(createFishingLineItem("Kevlar 0.22mm 150m", "Kevlar", 0.22, 150, 8, "bardzo wysoka"));
            lines.add(createFishingLineItem("Kevlar 0.25mm 200m", "Kevlar", 0.25, 200, 10, "bardzo wysoka"));
            lines.add(createFishingLineItem("Kevlar 0.30mm 250m", "Kevlar", 0.30, 250, 12, "bardzo wysoka"));
            lines.add(createFishingLineItem("Dacron 0.18mm 100m", "Dacron", 0.18, 100, 5, "średnia"));

            // Give the items to the player
            for (ItemStack line : lines) {
                player.getInventory().addItem(line);
            }
            player.sendMessage("Stworzono żyłki: Nylon, Fluorocarbon, Spleciona, Kevlar, Dacron");
            return true;
        }
        return false;
    }

    private ItemStack createFishingLineItem(String name, String material, double diameter, int length, int maxWeight, String durability) {
        ItemStack lineItem = new ItemStack(Material.STRING);
        ItemMeta meta = lineItem.getItemMeta();
        if (meta != null) {
            // Set the display name with bold formatting and reset to avoid italics
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);

            // Add lore to display the attributes
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Materiał: " + material);
            lore.add(ChatColor.GRAY + "Średnica: " + diameter + "mm");
            lore.add(ChatColor.GRAY + "Długość: " + length + "m");
            lore.add(ChatColor.GRAY + "Maksymalny ciężar: " + maxWeight + "kg");
            lore.add(ChatColor.GRAY + "Wytrzymałość: " + durability);
            meta.setLore(lore);

            // Add custom NBT tags
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "material"), PersistentDataType.STRING, material);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "diameter"), PersistentDataType.DOUBLE, diameter);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "length"), PersistentDataType.INTEGER, length);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "max_weight"), PersistentDataType.INTEGER, maxWeight);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "durability"), PersistentDataType.STRING, durability);
            lineItem.setItemMeta(meta);
        }
        return lineItem;
    }
}