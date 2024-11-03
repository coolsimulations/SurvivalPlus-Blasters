package net.coolsimulations.Blaster.util;

import java.util.Timer;
import java.util.TimerTask;

import com.mrcrayfish.guns.event.CommonEvents;
import com.mrcrayfish.guns.init.ModSounds;
import com.mrcrayfish.guns.item.ItemAmmo;
import com.mrcrayfish.guns.item.ItemGun;
import com.mrcrayfish.guns.object.Gun;

import net.coolsimulations.Blaster.Blaster;
import net.coolsimulations.Blaster.BlasterReference;
import net.coolsimulations.Blaster.init.BlasterItems;
import net.coolsimulations.SurvivalPlus.api.SPConfig;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementManager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent.PlayerLoggedInEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class BlasterEventHandler {

	@SubscribeEvent
	public void onplayerLogin(PlayerLoggedInEvent event)
	{
		EntityPlayerMP player = (EntityPlayerMP) event.player;
		NBTTagCompound entityData = player.getEntityData();

		AdvancementManager manager = player.getServer().getAdvancementManager();
		Advancement install = manager.getAdvancement(new ResourceLocation(BlasterReference.MOD_ID, BlasterReference.MOD_ID + "/install"));

		boolean isDone = false;

		Timer timer = new Timer();

		if(install !=null && player.getAdvancements().getProgress(install).hasProgress()) {
			isDone = true;
		}


		if(!entityData.getBoolean("blaster.firstJoin") && !isDone && !SPConfig.disableThanks) {

			entityData.setBoolean("blaster.firstJoin", true);

			if(!player.world.isRemote) {

				TextComponentTranslation installInfo = new TextComponentTranslation("advancements.blaster.install.display1");
				installInfo.getStyle().setColor(TextFormatting.GOLD);
				player.sendMessage(installInfo);

			}
		}

		if(BlasterUpdateHandler.isOld == true && SPConfig.disableUpdateCheck == false) {
			timer.schedule(new TimerTask() {
				@Override
				public void run() {
					player.sendMessage(BlasterUpdateHandler.updateInfo);
					player.sendMessage(BlasterUpdateHandler.updateVersionInfo);
				}
			}, 18000);

		}
	}
	
	private int weapon_amount;
	private int ammo_amount;
	
    //private Map<UUID, ReloadTracker> reloadTrackerMap = new HashMap<>();
	
	private static boolean containsPack(IInventory inventory) {
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
			ItemStack stack = inventory.getStackInSlot(slot);
			Item item = stack.getItem();
			if (!stack.isEmpty() && stack.getItem() instanceof ItemAmmo && item == BlasterItems.blaster_power_pack) {
				return true;
			}
		}
		return false;
	}
	
	private static ItemStack getPack(IInventory inventory) {
		for (int slot = 0; slot < inventory.getSizeInventory(); slot++) {
			ItemStack stack = inventory.getStackInSlot(slot);
			Item item = stack.getItem();
			if (!stack.isEmpty() && stack.getItem() instanceof ItemAmmo && item == BlasterItems.blaster_power_pack) {
				return stack;
			}
		}
		return ItemStack.EMPTY;
	}

	@SubscribeEvent
	public void onPlayerTick(TickEvent.PlayerTickEvent event)
	{
		if(event.phase == TickEvent.Phase.START && !event.player.world.isRemote)
		{
			EntityPlayer player = event.player;
			Gun gun;
			NBTTagCompound tag = player.inventory.getCurrentItem().getTagCompound();
			//ItemStack ammo = ItemGun.findAmmo(player, gun.projectile.item);
			
			if((player.inventory.getCurrentItem().getItem() instanceof ItemGun)) {
				gun = ((ItemGun) player.inventory.getCurrentItem().getItem()).getModifiedGun(player.inventory.getCurrentItem());
			}
			
			if(ammo_amount > getPack(player.inventory).getCount() && weapon_amount < tag.getInteger("AmmoCount")) {
				tag.setInteger("AmmoCount", 15);
				System.out.print("Set Weapon");
			}
			
			if(containsPack(player.inventory) && player.inventory.getCurrentItem().getItem() == BlasterItems.blaster) {
				ammo_amount = getPack(player.inventory).getCount();
				if(tag != null) {
					weapon_amount = tag.getInteger("AmmoCount");
				} else {
					weapon_amount = 0;
				}
				System.out.print("Set amounts, ammo: " + ammo_amount + ", weapon: " + weapon_amount);
			} else {
				ammo_amount = 0;
				weapon_amount = 0;
			}
		}
	}

	@SubscribeEvent
	public void registerItems(RegistryEvent.Register<Item> event)
	{
		BlasterItems.registerItems(event.getRegistry());
	}

	@SubscribeEvent
	public void onModelRegistry(ModelRegistryEvent event)
	{
		for(Item item : Blaster.ITEMS) {
			BlasterItems.registerRenders();
		}
	}

}
