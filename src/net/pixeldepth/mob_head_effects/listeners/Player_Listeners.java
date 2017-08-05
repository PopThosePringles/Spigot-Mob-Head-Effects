package net.pixeldepth.mob_head_effects.listeners;

import net.pixeldepth.mob_head_effects.Effector;
import net.pixeldepth.mob_head_effects.Mob_Head_Effects;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Player_Listeners implements Listener {

	private static Mob_Head_Effects plugin;

	@EventHandler
	private void on_player_join(PlayerJoinEvent event){
		Effector.apply(event.getPlayer());
	}

}
