package net.pixeldepth.mob_head_effects;

import net.pixeldepth.mob_head_effects.listeners.Inventory_Listeners;

import net.pixeldepth.mob_head_effects.listeners.Player_Listeners;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class Mob_Head_Effects extends JavaPlugin {

	private String plugin_name;

	public final FileConfiguration config = this.getConfig();

	private final Logger log = Logger.getLogger("Minecraft");

	public static Map<String, ArrayList<String>> lookup = new HashMap<String, ArrayList<String>>();

	@Override
	public void onEnable(){
		this.plugin_name = this.getDescription().getName();
		this.saveDefaultConfig();

		List<Map<?, ?>> list = this.config.getMapList("heads");

		list.forEach(v -> {
			String key = (String) v.get("lore");

			if(!lookup.containsKey(key)){
				lookup.put(key, new ArrayList<String>());
			}

			String[] effects = ((String) v.get("effects")).split(",");

			for(String effect : effects){
				lookup.get(key).add(effect.trim());
			}
		});

		this.getServer().getPluginManager().registerEvents(new Inventory_Listeners(), this);
		this.getServer().getPluginManager().registerEvents(new Player_Listeners(), this);
	}

	@Override
	public void onDisable(){
		this.info("Stopping");
	}

	public void info(String msg){
		this.log.info("[" + this.plugin_name + "]: " + msg + ".");
	}

	public void warn(String msg){
		this.log.warning("[" + this.plugin_name + "]: " + msg + ".");
	}

}
