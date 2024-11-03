package net.coolsimulations.Blaster;

import java.util.ArrayList;
import java.util.List;

import com.mrcrayfish.guns.Reference;

import net.coolsimulations.Blaster.init.BlasterItems;
import net.coolsimulations.Blaster.init.BlasterSoundHandler;
import net.coolsimulations.Blaster.proxy.CommonProxy;
import net.coolsimulations.Blaster.util.BlasterCompatibilityManager;
import net.coolsimulations.Blaster.util.BlasterEventHandler;
import net.coolsimulations.SurvivalPlus.api.SPReference;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = BlasterReference.MOD_ID, name = BlasterReference.MOD_NAME, version = BlasterReference.VERSION, acceptedMinecraftVersions = BlasterReference.ACCEPTED_VERSIONS, dependencies = Reference.DEPENDENCIES, updateJSON = "https://coolsimulations.net/mcmods/blaster/versionchecker.json")
public class Blaster {
	
	@SidedProxy(clientSide = BlasterReference.CLIENT_PROXY, serverSide = BlasterReference.SERVER_PROXY)
	public static CommonProxy proxy;
	
	@Mod.Instance(BlasterReference.MOD_ID)
	public static Blaster instance;
	
	public static final List<Item> ITEMS = new ArrayList<Item>();

	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		System.out.print("Pre Init");
		SPReference.MOD_ADDON_NAMES.add("sp.blaster.name");
		MinecraftForge.EVENT_BUS.register(new BlasterEventHandler());
		BlasterCompatibilityManager.checkForCompatibleMods();
		BlasterItems.init();
		BlasterItems.register();
	}

	@EventHandler
	public void init(FMLInitializationEvent event)
	{
		System.out.print("Init");
		proxy.init();
		BlasterSoundHandler.init();
	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		System.out.print("Post Init");
	}
}
