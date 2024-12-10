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

public class BaitCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            // Create the bait items
            List<ItemStack> baits = new ArrayList<>();
            baits.add(createBaitItem("Worm (Robak)", "float", "Płoć – 30%, Karaś – 20%, Leszcz – 15%, Wzdręga – 10%, Karaś złoty – 25%"));
            baits.add(createBaitItem("Insect (Owady)", "float", "Wzdręga – 40%, Płoć – 35%, Karaś – 15%, Karaś złoty – 10%"));
            baits.add(createBaitItem("Corn (Kukurydza)", "float", "Karp – 50%, Karaś – 25%, Karaś złoty – 15%, Leszcz – 10%"));
            baits.add(createBaitItem("Maggot (Larwa)", "float", "Wzdręga – 40%, Karaś – 30%, Płoć – 20%, Karaś złoty – 10%"));
            baits.add(createBaitItem("Cheese (Ser)", "float", "Karp – 45%, Karaś złoty – 30%, Leszcz – 15%, Karaś – 10%"));
            baits.add(createBaitItem("Blades (Blachy)", "predator", "Sandacz – 35%, Troć – 25%, Troć potokowa – 20%, Sum – 15%, Troć wędrowna – 5%"));
            baits.add(createBaitItem("Gum Worms (Gumowe robaki)", "predator", "Sandacz – 40%, Sum – 30%, Troć wędrowna – 20%, Troć potokowa – 10%"));
            baits.add(createBaitItem("Live Bait (Żywy Bait)", "predator", "Sum – 50%, Troć – 30%, Sandacz – 15%, Troć potokowa – 5%"));
            baits.add(createBaitItem("Boilie (Kulki proteinowe)", "feeder", "Karp – 50%, Amur – 30%, Leszcz – 15%, Karaś – 5%"));
            baits.add(createBaitItem("Pellets (Granulki)", "feeder", "Karp – 40%, Karaś – 30%, Amur – 20%, Leszcz – 10%"));
            baits.add(createBaitItem("Crisps (Crispy Bait)", "feeder", "Karp – 40%, Leszcz – 25%, Karaś – 20%, Amur – 15%"));
            baits.add(createBaitItem("Fruits (Owoce)", "feeder", "Karaś – 35%, Płoć – 25%, Wzdręga – 20%, Leszcz – 20%"));

            // Give the items to the player
            for (ItemStack bait : baits) {
                player.getInventory().addItem(bait);
            }
            player.sendMessage("Stworzono przynęty: Worm, Insect, Corn, Maggot, Cheese, Blades, Gum Worms, Live Bait, Boilie, Pellets, Crisps, Fruits");
            return true;
        }
        return false;
    }

    private ItemStack createBaitItem(String name, String category, String fishTypes) {
        ItemStack baitItem = new ItemStack(Material.CARROT_ON_A_STICK);
        ItemMeta meta = baitItem.getItemMeta();
        if (meta != null) {
            // Set the display name with bold formatting and reset to avoid italics
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);

            // Add lore to display the attributes
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Rodzaj ryby i procent: " + fishTypes);
            lore.add(ChatColor.GRAY + "Kategoria: " + category);
            meta.setLore(lore);

            // Add custom NBT tags
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "fish_types"), PersistentDataType.STRING, fishTypes);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "category"), PersistentDataType.STRING, category);
            baitItem.setItemMeta(meta);
        }
        return baitItem;
    }
}