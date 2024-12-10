package serwer.realFishing;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class FishingRodCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            // Create the fishing rods
            List<ItemStack> rods = new ArrayList<>();
            // Spławikowe rods
            rods.add(createFishingRod("Wędka Spławikowa \"Łaskotka\"", 3, "5-15g", "wysoka", "średnia"));
            rods.add(createFishingRod("Wędka Spławikowa \"Łowca\"", 3.5, "10-25g", "średnia", "wysoka"));
            rods.add(createFishingRod("Wędka Spławikowa \"Cisza Wody\"", 4, "7-20g", "wysoka", "średnia"));
            rods.add(createFishingRod("Wędka Spławikowa \"Delikatny Dotyk\"", 2.5, "3-10g", "bardzo wysoka", "niska"));
            rods.add(createFishingRod("Wędka Spławikowa \"Mistrz Spławika\"", 3, "5-20g", "średnia", "średnia"));
            // Feederowe rods
            rods.add(createFishingRod("Wędka Feederowa \"Siła Gruntu\"", 3.6, "40-80g", "średnia", "wysoka"));
            rods.add(createFishingRod("Wędka Feederowa \"Potężny Rzut\"", 4, "50-100g", "średnia", "bardzo wysoka"));
            rods.add(createFishingRod("Wędka Feederowa \"Precyzyjny Strzał\"", 3.3, "30-70g", "średnia", "średnia"));
            rods.add(createFishingRod("Wędka Feederowa \"Gruntowy Mistrz\"", 3.9, "60-120g", "średnia", "wysoka"));
            rods.add(createFishingRod("Wędka Feederowa \"Złoty Karp\"", 4.2, "80-150g", "niska", "bardzo wysoka"));
            // Spinningowe rods
            rods.add(createFishingRod("Wędka Spinningowa \"Szybki Strzał\"", 2.7, "5-20g", "bardzo wysoka", "średnia"));
            rods.add(createFishingRod("Wędka Spinningowa \"Drapieżca\"", 2.4, "10-30g", "wysoka", "wysoka"));
            rods.add(createFishingRod("Wędka Spinningowa \"Nocny Łowca\"", 3, "15-40g", "średnia", "średnia"));
            rods.add(createFishingRod("Wędka Spinningowa \"Wielki Złom\"", 2.9, "20-50g", "średnia", "wysoka"));
            rods.add(createFishingRod("Wędka Spinningowa \"Mistrz Łowienia\"", 2.5, "10-25g", "bardzo wysoka", "średnia"));

            // Give the items to the player
            for (ItemStack rod : rods) {
                player.getInventory().addItem(rod);
            }
            player.sendMessage("Stworzono wędki: Łaskotka, Łowca, Cisza Wody, Delikatny Dotyk, Mistrz Spławika, Siła Gruntu, Potężny Rzut, Precyzyjny Strzał, Gruntowy Mistrz, Złoty Karp, Szybki Strzał, Drapieżca, Nocny Łowca, Wielki Złom, Mistrz Łowienia");
            return true;
        }
        return false;
    }

    private ItemStack createFishingRod(String name, double length, String castWeight, String sensitivity, String durability) {
        ItemStack rodItem = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = rodItem.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Długość: " + length + "m");
            lore.add(ChatColor.GRAY + "Ciężar wyrzutu: " + castWeight);
            lore.add(ChatColor.GRAY + "Czułość: " + sensitivity);
            lore.add(ChatColor.GRAY + "Wytrzymałość: " + durability);
            meta.setLore(lore);
            rodItem.setItemMeta(meta);
        }
        return rodItem;
    }
}