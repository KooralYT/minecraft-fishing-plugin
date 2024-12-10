package serwer.realFishing;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class SetGUI implements Listener {

    public void openSetGUI(Player player) {
        Inventory gui = Bukkit.createInventory(null, 9, ChatColor.BOLD + "Create Fishing Set");

        // Add placeholders for each slot
        gui.setItem(0, createPlaceholderItem("Fishing Rod"));
        gui.setItem(1, createPlaceholderItem("Reel"));
        gui.setItem(2, createPlaceholderItem("Fishing Line"));
        gui.setItem(3, createPlaceholderItem("Float/Basket"));
        gui.setItem(4, createPlaceholderItem("Leader"));
        gui.setItem(5, createPlaceholderItem("Groundbait"));
        gui.setItem(6, createPlaceholderItem("Hook"));
        gui.setItem(7, createPlaceholderItem("Bait"));
        gui.setItem(8, createConfirmButton());

        player.openInventory(gui);
    }

    private ItemStack createPlaceholderItem(String name) {
        ItemStack item = new ItemStack(Material.GRAY_STAINED_GLASS_PANE);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + name);
            item.setItemMeta(meta);
        }
        return item;
    }

    private ItemStack createConfirmButton() {
        ItemStack item = new ItemStack(Material.GREEN_WOOL);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + "Confirm");
            item.setItemMeta(meta);
        }
        return item;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        if (event.getView().getTitle().equals(ChatColor.BOLD + "Create Fishing Set")) {
            Player player = (Player) event.getWhoClicked();
            int slot = event.getRawSlot();

            if (slot == 8) {
                // Handle confirm button click
                event.setCancelled(true);
                if (isSetComplete(event.getInventory())) {
                    createFishingSet(player, event.getInventory());
                    player.closeInventory();
                } else {
                    player.sendMessage(ChatColor.RED + "Please fill all required slots before confirming.");
                }
            } else if (slot < 9) {
                // Allow placing items in the first 8 slots
                event.setCancelled(false);
            }
        }
    }

    private boolean isSetComplete(Inventory inventory) {
        for (int i = 0; i < 8; i++) {
            if (inventory.getItem(i) == null || inventory.getItem(i).getType() == Material.GRAY_STAINED_GLASS_PANE) {
                return false;
            }
        }
        return true;
    }

    private void createFishingSet(Player player, Inventory inventory) {
        ItemStack rod = inventory.getItem(0);
        ItemStack reel = inventory.getItem(1);
        ItemStack line = inventory.getItem(2);
        ItemStack hook = inventory.getItem(6);
        ItemStack bait = inventory.getItem(7);

        // Create the custom fishing rod with attributes from the items
        ItemStack customRod = new ItemStack(Material.FISHING_ROD);
        ItemMeta meta = customRod.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.RESET.toString() + ChatColor.BOLD + "Custom Fishing Rod");
            List<String> lore = new ArrayList<>();
            if (rod != null && rod.hasItemMeta() && rod.getItemMeta().hasLore()) lore.addAll(rod.getItemMeta().getLore());
            if (reel != null && reel.hasItemMeta() && reel.getItemMeta().hasLore()) lore.addAll(reel.getItemMeta().getLore());
            if (line != null && line.hasItemMeta() && line.getItemMeta().hasLore()) lore.addAll(line.getItemMeta().getLore());
            if (hook != null && hook.hasItemMeta() && hook.getItemMeta().hasLore()) lore.addAll(hook.getItemMeta().getLore());
            if (bait != null && bait.hasItemMeta() && bait.getItemMeta().hasLore()) lore.addAll(bait.getItemMeta().getLore());
            meta.setLore(lore);
            customRod.setItemMeta(meta);
        }

        player.getInventory().addItem(customRod);
        player.sendMessage("Created custom fishing rod with selected attributes.");
    }
}