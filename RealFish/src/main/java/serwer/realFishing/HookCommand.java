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

public class HookCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
        if (sender instanceof Player player) {
            // Create the hook items
            List<ItemStack> hooks = new ArrayList<>();
            hooks.add(createHookItem("Haczyk 6", "6", "średnia"));
            hooks.add(createHookItem("Haczyk 4", "4", "średnia"));
            hooks.add(createHookItem("Haczyk 2", "2", "średnia"));
            hooks.add(createHookItem("Haczyk 1", "1", "średnia"));
            hooks.add(createHookItem("Haczyk 1/0", "1/0", "średnia"));
            hooks.add(createHookItem("Haczyk 2/0", "2/0", "średnia"));
            hooks.add(createHookItem("Haczyk 4/0", "4/0", "średnia"));
            hooks.add(createHookItem("Haczyk 6/0", "6/0", "wysoka"));
            hooks.add(createHookItem("Haczyk 8/0", "8/0", "wysoka"));
            hooks.add(createHookItem("Haczyk 10/0", "10/0", "wysoka"));

            // Give the items to the player
            for (ItemStack hook : hooks) {
                player.getInventory().addItem(hook);
            }
            player.sendMessage("Stworzono haczyki: 6, 4, 2, 1, 1/0, 2/0, 4/0, 6/0, 8/0, 10/0");
            return true;
        }
        return false;
    }

    private ItemStack createHookItem(String name, String size, String durability) {
        ItemStack hookItem = new ItemStack(Material.TRIPWIRE_HOOK);
        ItemMeta meta = hookItem.getItemMeta();
        if (meta != null) {
            // Set the display name with bold formatting and reset to avoid italics
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);

            // Add lore to display the attributes
            List<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + "Rozmiar: " + size);
            lore.add(ChatColor.GRAY + "Wytrzymałość: " + durability);
            meta.setLore(lore);

            // Add custom NBT tags
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "size"), PersistentDataType.STRING, size);
            meta.getPersistentDataContainer().set(new NamespacedKey("realfishing", "durability"), PersistentDataType.STRING, durability);
            hookItem.setItemMeta(meta);
        }
        return hookItem;
    }
}