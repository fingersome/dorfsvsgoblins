package fingersome.dorfcraft.tab;

import fingersome.dorfcraft.item.ItemList;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public final class CreativeTabMasonry extends CreativeTabs
{

    public CreativeTabMasonry(int id, String name)
    {
        super(id, name);
    }

	@Override
	public ItemStack getTabIconItem() 
	{
		return null;
	}	

}