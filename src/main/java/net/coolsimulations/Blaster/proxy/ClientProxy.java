package net.coolsimulations.Blaster.proxy;

import net.coolsimulations.Blaster.init.BlasterItems;

public class ClientProxy implements CommonProxy {
	
	@Override
	public void init() {
		BlasterItems.registerRenders();
	}

}
