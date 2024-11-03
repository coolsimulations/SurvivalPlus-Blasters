package net.coolsimulations.Blaster.init;

import net.coolsimulations.Blaster.BlasterReference;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

public class BlasterSoundHandler {
	
	public static int size = 0;
	
	public static SoundEvent blaster_fire;
	public static SoundEvent blaster_reload;
	
	public static void init() {
		size = SoundEvent.REGISTRY.getKeys().size();
		
		blaster_fire = register("item.blaster.blaster_fire");
		blaster_fire = register("item.blaster.blaster_reload");
	}
	
	public static SoundEvent register(String name) {
		ResourceLocation location = new ResourceLocation(BlasterReference.MOD_ID, name);
		SoundEvent e = new SoundEvent(location);
		e.setRegistryName(name);
		
		ForgeRegistries.SOUND_EVENTS.register(e);
		return e;
	}

}
