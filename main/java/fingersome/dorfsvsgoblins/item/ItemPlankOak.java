package fingersome.dorfsvsgoblins.item;

import fingersome.dorfsvsgoblins.DorfsVsGoblins;
import fingersome.dorfsvsgoblins.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemPlankOak extends Item
{
	private final String name = ItemInfo.ITEM_PLANK_OAK_UNLOCALIZED;

	public ItemPlankOak()
	{
		setUnlocalizedName(ModInfo.MODID + "." + ItemInfo.ITEM_PLANK_OAK_UNLOCALIZED);
		setCreativeTab(DorfsVsGoblins.tabCarpentry);
		GameRegistry.registerItem(this, name);
	}
	
	public String getName()
	{
		return name;
	}
	
}
