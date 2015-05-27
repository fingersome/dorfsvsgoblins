package fingersome.dorfsvsgoblins.item;

import fingersome.dorfsvsgoblins.DorfsVsGoblins;
import fingersome.dorfsvsgoblins.ModInfo;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class ItemPlateMithril extends Item
{
	private final String name = ItemInfo.ITEM_PLATE_MITHRIL_UNLOCALIZED;

	public ItemPlateMithril()
	{
		setUnlocalizedName(ModInfo.MODID + "." + ItemInfo.ITEM_PLATE_MITHRIL_UNLOCALIZED);
		setCreativeTab(DorfsVsGoblins.tabSmithing);
		GameRegistry.registerItem(this, name);
	}
	
	public String getName()
	{
		return name;
	}
	
}