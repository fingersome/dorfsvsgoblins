package fingersome.dorfcraft.common.items;

import net.minecraft.item.Item;

public class ItemMask extends Item {

	protected String name;
	 
	 public ItemMask(String name) {
	 this.name = name;
	 setUnlocalizedName(name);
	 setRegistryName(name);
	 }
	 	
}
