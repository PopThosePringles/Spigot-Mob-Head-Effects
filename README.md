# Spigot - Mob Head Effects

This plugin will apply effects to players when they wear certain mob heads.

By adding line 1 lore to a mob head, you can set which effects get applied to the player when they wear it.

Download from the release page: https://github.com/PopThosePringles/Spigot-Mob-Head-Effects/releases

### Example:

In the loot table, you can include a lore entry when setting the NBT data.

**Before:**

```JavaScript
"entries": [
	{
		"type": "item",
		"name": "minecraft:skull",
		"weight": 1,
		"functions": [
			{
				"function": "set_data",
				"data": 3
			},
			{
				"function": "set_nbt",
				"tag": "{display:{LocName:\"Shulker Skull\"},SkullOwner:{Id:\"cda568d7-46da-4468-a46a-4c1ed73faf53\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU3MzgzMmUyNzJmODg0NGM0NzY4NDZiYzQyNGEzNDMyZmI2OThjNThlNmVmMmE5ODcxYzdkMjlhZWVhNyJ9fX0=\"}]}}}"
			}
		]
	}
]
```

**After:**

```JavaScript
"entries": [
	{
		"type": "item",
		"name": "minecraft:skull",
		"weight": 1,
		"functions": [
			{
				"function": "set_data",
				"data": 3
			},
			{
				"function": "set_nbt",
				"tag": "{display:{Lore: [\"Up Up And Away\"], LocName:\"Shulker Skull\"},SkullOwner:{Id:\"cda568d7-46da-4468-a46a-4c1ed73faf53\",Properties:{textures:[{Value:\"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWU3MzgzMmUyNzJmODg0NGM0NzY4NDZiYzQyNGEzNDMyZmI2OThjNThlNmVmMmE5ODcxYzdkMjlhZWVhNyJ9fX0=\"}]}}}"
			}
		]
	}
]
```

Notice the new `Lore` property added.

In the `config.yml` file, you add a new entry for that mob head like so...

```heads:

  # Squid
  -
    lore: Look Mum, I'm A Squid
    effects: water breathing

  # Ocelot
  -
    lore: Cat Eyes
    effects: night vision

  # Blaze
  -
    lore: Toasty
    effects: fire resistance

  # Rabbit
  -
    lore: Jump For The Stars
    effects: jump boost

  # Shulker
  -
     lore: Up Up And Away
     effects: levitation
```

Multiple effects can be applied by separating them by a comma.  All the effects that are supported can be found here...

https://minecraft.gamepedia.com/Status_effect

It's important to note that the lore that needs to match is line 1.  Generally this is no concern, but if you do have custom items with your own lore, then you will need to put the lore to match on line 1.

Here is a video of it in action...

https://www.youtube.com/watch?v=7h68WX4zvvg

#### Additional Notes:

I took care in considering performance with this plugin.  The way the plugin works, is it looks to see what item is in the helmet slot.  It only does this check using 2 events.  Hopefully this should be good enough to apply and remove the effects correctly.

- Inventory Close

  When the player closes their inventory, it looks to see what item is in the helmet slot and attempts to make a match based on the lore on the item and in the config.

- Player Join

  When the player joins the server, it checks to see if the effects should still get applied or not.  This does the same checks as above.  The reason for this is to catch a few conditions where the head could be took off and placed inside the inventory then the server restarts.  Next time the player joins, they would still have the effect applied without having the head on.  So this covers that condition.
  
Dispenses are not supported.  They will add a head to the helmet slow, but the effects will not get applied until the player rejoins the server, or they open and close their inventory.  Not many people use dispenses for this, though I may consider supporting them if it's really needed.

I took care to not remove effects that are applied by other means.  The effects added by this plugin have the duration set to the max integer value.  A simple check is done to make sure we are removing effects that are using a long duration.  That way if a player has night vision from a head, and fire resistance from a potion, then it will not remove the fire resistance effect when the head is removed.

#### Config.yml

The config contains a few working examples for Blaze, Rabbit, Squid, and Ocelots.  These will not work until you add the lore to the NBT data in your loot tables (see video or above code for example)

#### Mob Heads

These mob heads come from Xisuma and his team, you can find all the info on his website with download and instructions.

http://xisumavoid.com/moremobheads/ 