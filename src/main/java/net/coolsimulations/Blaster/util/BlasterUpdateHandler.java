package net.coolsimulations.Blaster.util;

import java.net.URL;
import java.util.Scanner;

import net.coolsimulations.Blaster.BlasterReference;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.event.ClickEvent;
import net.minecraft.util.text.event.HoverEvent;
import net.minecraftforge.common.MinecraftForge;

public class BlasterUpdateHandler {

	private static String latestVersion;
	private static String latestVersionInfo;
	public static boolean isOld = false;
	public static TextComponentTranslation updateInfo = null;
	public static TextComponentString updateVersionInfo = null;

	public static void init() {

		try {
			URL url = new URL("https://coolsimulations.net/mcmods/blaster/versionchecker112.txt");
			Scanner s = new Scanner(url.openStream());
			latestVersion = s.next();
			s.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		try {
			URL url = new URL("https://coolsimulations.net/mcmods/blaster/updateinfo112.txt");
			Scanner s = new Scanner(url.openStream());
			latestVersionInfo = s.nextLine();
			s.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		if(latestVersion != null) {

			if(latestVersion.equals("ended")) {

				isOld = true;

				TextComponentTranslation blaster = new TextComponentTranslation("sp.blaster.name");
				blaster.getStyle().setColor(TextFormatting.BLUE);

				TextComponentString MCVersion = new TextComponentString(MinecraftForge.MC_VERSION);
				MCVersion.getStyle().setColor(TextFormatting.BLUE);

				updateInfo = new TextComponentTranslation("sp.update.display3", new Object[] {blaster, MCVersion});
				updateInfo.getStyle().setColor(TextFormatting.YELLOW);

				updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("sp.update.display2")));
				updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-blasters"));

			}

			if(!latestVersion.equals(BlasterReference.VERSION) && !latestVersion.equals("ended")) {

				isOld = true;

				TextComponentTranslation blaster = new TextComponentTranslation("sp.blaster.name");
				blaster.getStyle().setColor(TextFormatting.BLUE);

				TextComponentString version = new TextComponentString(latestVersion);
				version.getStyle().setColor(TextFormatting.BLUE);

				updateInfo = new TextComponentTranslation("sp.update.display1", new Object[] {blaster, version});
				updateInfo.getStyle().setColor(TextFormatting.YELLOW);

				updateInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("sp.update.display2")));
				updateInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.curseforge.com/minecraft/mc-mods/survivalplus-blasters"));

				if(latestVersionInfo != null) {

					updateVersionInfo = new TextComponentString(latestVersionInfo);
					updateVersionInfo.getStyle().setColor(TextFormatting.DARK_AQUA);
					updateVersionInfo.getStyle().setBold(true);

					updateVersionInfo.getStyle().setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new TextComponentTranslation("sp.update.display2")));
					updateVersionInfo.getStyle().setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://curseforge.com/minecraft/mc-mods/survivalplus-blasters"));

				}

			}

		}
	}

}
