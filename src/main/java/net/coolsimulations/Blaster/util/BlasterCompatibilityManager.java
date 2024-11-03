package net.coolsimulations.Blaster.util;

import net.minecraftforge.fml.common.Loader;

public class BlasterCompatibilityManager {
	
	private static boolean modLightsaberLoaded;
	
	public static final String SPLIGHTSABERS_MODID = "lightsaber";
	
	public static void checkForCompatibleMods(){

		if (Loader.isModLoaded(SPLIGHTSABERS_MODID))
		{
			BlasterCompatibilityManager.modLightsaberLoaded = true;
		}
	}
	
	public static boolean isLightsabersLoaded()
	{
		return BlasterCompatibilityManager.modLightsaberLoaded;
	}

}
