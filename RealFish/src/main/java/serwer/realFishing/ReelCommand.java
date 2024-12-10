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

public class ReelCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            // Create the reel items
            List<ItemStack> reels = new ArrayList<>();
            reels.add(createReelItem("Kołowrotek \"Błyskawica\"", "5.0:1", 120, "0.30mm", 8, "niska", "brak"));
            reels.add(createReelItem("Kołowrotek \"Tornado\"", "4.8:1", 150, "0.35mm", 10, "średnia", "średni"));
            reels.add(createReelItem("Kołowrotek \"Rekin\"", "6.2:1", 100, "0.25mm", 6, "wysoka", "bardzo wysoki"));
            reels.add(createReelItem("Kołowrotek \"Łowca\"", "4.4:1", 180, "0.30mm", 12, "średnia", "wysoki"));
            reels.add(createReelItem("Kołowrotek \"Mistrz\"", "5.5:1", 250, "0.40mm", 15, "bardzo wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Thunder\"", "4.6:1", 130, "0.28mm", 10, "wysoka", "bardzo wysoki"));
            reels.add(createReelItem("Kołowrotek \"Striker\"", "5.2:1", 100, "0.30mm", 7, "średnia", "średni"));
            reels.add(createReelItem("Kołowrotek \"X-Treme\"", "6.0:1", 200, "0.25mm", 15, "bardzo wysoka", "wysoki"));
            reels.add(createReelItem("Kołowrotek \"Jazda\"", "5.0:1", 160, "0.35mm", 9, "średnia", "niski"));
            reels.add(createReelItem("Kołowrotek \"Viper\"", "4.5:1", 180, "0.40mm", 11, "wysoka", "bardzo wysoki"));
            reels.add(createReelItem("Kołowrotek \"Książę\"", "5.8:1", 220, "0.30mm", 14, "bardzo wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Czarna Perła\"", "6.5:1", 250, "0.25mm", 18, "wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Ognisty\"", "4.2:1", 140, "0.28mm", 6, "średnia", "niski"));
            reels.add(createReelItem("Kołowrotek \"Ranger\"", "5.2:1", 200, "0.30mm", 13, "bardzo wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Mocny\"", "6.0:1", 220, "0.35mm", 16, "wysoka", "bardzo wysoki"));
            reels.add(createReelItem("Kołowrotek \"Złoty Ząb\"", "4.4:1", 180, "0.25mm", 8, "średnia", "niski"));
            reels.add(createReelItem("Kołowrotek \"Mistrzowski\"", "5.6:1", 240, "0.30mm", 17, "bardzo wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Sztorm\"", "6.2:1", 190, "0.40mm", 12, "wysoka", "bardzo wysoki"));
            reels.add(createReelItem("Kołowrotek \"Krypton\"", "5.3:1", 210, "0.35mm", 14, "wysoka", "średni"));
            reels.add(createReelItem("Kołowrotek \"Zimowy\"", "4.0:1", 150, "0.30mm", 10, "średnia", "wysoki"));

            // Give the items to the player
            for (ItemStack reel : reels) {
                player.getInventory().addItem(reel);
            }
            player.sendMessage("Stworzono kołowrotki: Błyskawica, Tornado, Rekin, Łowca, Mistrz, Thunder, Striker, X-Treme, Jazda, Viper, Książę, Czarna Perła, Ognisty, Ranger, Mocny, Złoty Ząb, Mistrzowski, Sztorm, Krypton, Zimowy");
            return true;
        }
        return false;
    }

    private ItemStack createReelItem(String name, String gearRatio, int maxLineLength, String lineDiameter, int maxWeight, String durability, String brakes) {
        ItemStack reelItem = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta meta = reelItem.getItemMeta();
        if (meta != null) {
            // Set the display name with bold formatting and reset to avoid italics
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);

            // Add lore to display the attributes
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Przełożenie: " + gearRatio);
            lore.add(ChatColor.GRAY + "Maksymalne nawinięcie żyłki: " + maxLineLength + "m (" + lineDiameter + ")");
            lore.add(ChatColor.GRAY + "Maksymalny ciężar: " + maxWeight + "kg");
            lore.add(ChatColor.GRAY + "Wytrzymałość: " + durability);
            lore.add(ChatColor.GRAY + "Hamulce: " + brakes);
            meta.setLore(lore);

            // Add custom NBT tags
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "gear_ratio"), PersistentDataType.STRING, gearRatio);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "max_line_length"), PersistentDataType.INTEGER, maxLineLength);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "line_diameter"), PersistentDataType.STRING, lineDiameter);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "max_weight"), PersistentDataType.INTEGER, maxWeight);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "durability"), PersistentDataType.STRING, durability);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "brakes"), PersistentDataType.STRING, brakes);
            reelItem.setItemMeta(meta);
        }
        return reelItem;
    }
}