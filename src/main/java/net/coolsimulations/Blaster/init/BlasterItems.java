package net.coolsimulations.Blaster.init;

import com.mrcrayfish.guns.item.ItemAmmo;
import com.mrcrayfish.guns.item.ItemGun;

import net.coolsimulations.Blaster.Blaster;
import net.coolsimulations.Blaster.BlasterReference;
import net.coolsimulations.SurvivalPlus.api.SPTabs;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.registries.IForgeRegistry;

public class BlasterItems {
	
	public static Item blaster;
	public static Item blaster_power_pack;
	
	public static void init() {
		
		blaster = new ItemGun(new ResourceLocation(BlasterReference.MOD_ID, "blaster")).setCreativeTab(SPTabs.tabCombat);
		blaster_power_pack = new ItemAmmo(new ResourceLocation(BlasterReference.MOD_ID, "blaster_power_pack")).setCreativeTab(SPTabs.tabCombat);
	}
	
	public static void register() {
		
		registerItem(blaster);
		registerItem(blaster_power_pack);
	}
	
	public static void registerRenders() {
		
		registerRender(blaster);
		registerRender(blaster_power_pack);
	}
	
	public static void registerItem(Item item) {

		Blaster.ITEMS.add(item);
	}

	public static void registerItems(IForgeRegistry<Item> registry) {

		for (Item item : Blaster.ITEMS)
		{
			registry.register(item);
		}
	}

	public static void registerRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

}
