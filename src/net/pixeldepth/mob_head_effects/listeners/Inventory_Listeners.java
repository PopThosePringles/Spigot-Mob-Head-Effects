package net.pixeldepth.mob_head_effects.listeners;

import net.pixeldepth.mob_head_effects.Effector;
import net.pixeldepth.mob_head_effects.Mob_Head_Effects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

public class Inventory_Listeners implements Listener {

	@EventHandler
	private void on_inventory_closed(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();

		Effector.apply(player);
	}

}
