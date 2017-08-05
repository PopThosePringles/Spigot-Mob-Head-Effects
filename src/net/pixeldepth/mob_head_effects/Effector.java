package net.pixeldepth.mob_head_effects;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.Collection;
import java.util.List;

public class Effector {

	public static void apply(Player player){
		if(player == null){
			return;
		}

		PlayerInventory inventory = player.getInventory();
		ItemStack helmet = inventory.getHelmet();

		boolean remove_potion_effects = true;

		if(helmet != null && helmet.getType() == Material.SKULL_ITEM){
			ItemMeta meta = helmet.getItemMeta();

			if(meta != null){
				List<String> lore = meta.getLore();
				String lore_match = lore.get(0);

				if(lore_match != null){

					// Remove existing infinite potions.  This prevents potion
					// stacking if the player takes a head off and puts another on.

					remove_effects(player);

					if(Mob_Head_Effects.lookup.containsKey(lore_match)){
						List<String> effects = Mob_Head_Effects.lookup.get(lore_match);

						for(String effect : effects){
							PotionEffectType effect_type = null;
							short effect_amp = 0;

							switch(effect.toLowerCase()){

								case "absorption":
									effect_type = PotionEffectType.ABSORPTION;
									break;

								case "bad luck":
									effect_type = PotionEffectType.UNLUCK;
									break;

								case "blindness":
									effect_type = PotionEffectType.INVISIBILITY;
									break;

								case "fire resistance":
									effect_type = PotionEffectType.FIRE_RESISTANCE;
									break;

								case "glowing":
									effect_type = PotionEffectType.GLOWING;
									break;

								case "haste":
									effect_type = PotionEffectType.FAST_DIGGING;
									break;

								case "health boost":
									effect_type = PotionEffectType.HEALTH_BOOST;
									break;

								case "hunger":
									effect_type = PotionEffectType.HUNGER;
									break;

								case "instant health":
									effect_type = PotionEffectType.HEAL;
									break;

								case "instant damage":
									effect_type = PotionEffectType.HARM;
									break;

								case "invisibility":
									effect_type = PotionEffectType.INVISIBILITY;
									break;

								case "jump boost":
									effect_type = PotionEffectType.JUMP;
									effect_amp = 1;
									break;

								case "levitation":
									effect_type = PotionEffectType.LEVITATION;
									break;

								case "luck":
									effect_type = PotionEffectType.LUCK;
									break;

								case "mining fatigue":
									effect_type = PotionEffectType.SLOW_DIGGING;
									break;

								case "nausea":
									effect_type = PotionEffectType.CONFUSION;
									break;

								case "night vision":
									effect_type = PotionEffectType.NIGHT_VISION;
									break;

								case "poison":
									effect_type = PotionEffectType.POISON;
									break;

								case "regeneration":
									effect_type = PotionEffectType.REGENERATION;
									break;

								case "resistance":
									effect_type = PotionEffectType.DAMAGE_RESISTANCE;
									break;

								case "saturation":
									effect_type = PotionEffectType.SATURATION;
									break;

								case "slowness":
									effect_type = PotionEffectType.SLOW;
									break;

								case "speed":
									effect_type = PotionEffectType.SPEED;
									break;

								case "strength":
									effect_type = PotionEffectType.INCREASE_DAMAGE;
									break;

								case "water breathing":
									effect_type = PotionEffectType.WATER_BREATHING;
									break;

								case "weakness":
									effect_type = PotionEffectType.WEAKNESS;
									break;

								case "wither":
									effect_type = PotionEffectType.WITHER;
									break;
							}

							if(effect_type != null){

								// Remove old effect (i.e player drunk a potion)

								if(player.hasPotionEffect(effect_type)){
									player.removePotionEffect(effect_type);
								}

								player.addPotionEffect(new PotionEffect(effect_type, Integer.MAX_VALUE, effect_amp, false, false));

								remove_potion_effects = false;
							}
						}
					}
				}
			}
		}

		if(remove_potion_effects){
			remove_effects(player);
		}
	}


	// Removes all effects that likely added by this class

	private static void remove_effects(Player player){
		Collection<PotionEffect> effects = player.getActivePotionEffects();

		effects.forEach(e -> {
			if(e.getDuration() > 10000000){
				player.removePotionEffect(e.getType());
			}
		});
	}
}
