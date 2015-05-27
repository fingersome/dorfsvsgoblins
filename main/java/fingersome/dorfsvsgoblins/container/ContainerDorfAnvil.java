package fingersome.dorfsvsgoblins.container;

import fingersome.dorfsvsgoblins.DorfsVsGoblins;
import fingersome.dorfsvsgoblins.crafting.DorfAnvilCraftingManager;
import fingersome.dorfsvsgoblins.tile.TileEntityDorfAnvil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryCraftResult;
import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class ContainerDorfAnvil extends Container 
{

	public InventoryCrafting craftMatrix;
	public IInventory craftResult;
	private World worldObj;
	private int posX;
	private int posY;
	private int posZ;
	
	public ContainerDorfAnvil(InventoryPlayer invPlayer, World world, int x, int y, int z) 
	{
		craftMatrix = new InventoryCrafting(this, 3, 3);
		craftResult = new InventoryCraftResult();
		worldObj = world;
		posX = x;
		posY = y;
		posZ = z;
		
		this.addSlotToContainer(new SlotCrafting(invPlayer.player, craftMatrix, craftResult, 0, 124, 35));
		
		for (int i = 0; i < 3; i++) 
		{
			for(int k = 0; k < 3; k++) 
			{
				 this.addSlotToContainer(new Slot(this.craftMatrix, k + i * 3, 30 + k * 18, 17 + i * 18));
			}
		}
		
		for (int i = 0; i < 3; i++) 
		{
			for(int k = 0; k < 9; k++) 
			{
				 this.addSlotToContainer(new Slot(invPlayer, k + i * 9 + 9, 8 + k * 18, 84 + i * 18));
			}
		}
		
		for (int i = 0; i < 9; i++) 
		{
			this.addSlotToContainer(new Slot(invPlayer, i, 8 + i * 18, 142));
		}
		
		onCraftMatrixChanged(craftMatrix);
	}
	
	
	public ContainerDorfAnvil(InventoryPlayer playerInventory, TileEntityDorfAnvil tileEntityDorfAnvil) 
	{

	}


	public void onCraftMatrixChanged(IInventory iiventory) 
	{
		//game crashes here because of something to do with the craftingmanager
		//disabled code for testing
		//craftResult.setInventorySlotContents(0, DorfAnvilCraftingManager.getInstance().findMatchingRecipe(craftMatrix, worldObj));
	}
	
	
	@Override
	public boolean canInteractWith(EntityPlayer player) 
	{
		if(worldObj.getBlockState(new BlockPos(posX, posY, posZ)) != DorfsVsGoblins.blockDorfAnvil)
		{
			return false;	
		}
		else
		{
			return player.getDistanceSq((double)posX + 0.5D, (double)posY + 0.5D, (double)posZ + 0.5D) <= 64.0D;
		}
	}
	
	public void onContainerClosed(EntityPlayer par1EntityPlayer) 
	{
        super.onContainerClosed(par1EntityPlayer);

        if (!this.worldObj.isRemote)
        {
            for (int i = 0; i < 9; ++i)
            {
                ItemStack itemstack = this.craftMatrix.getStackInSlotOnClosing(i);

                if (itemstack != null)
                {
                    par1EntityPlayer.dropPlayerItemWithRandomChoice(itemstack, false);
                }
            }
        }
    }

	
	public ItemStack transferStackInSlot(EntityPlayer par1EntityPlayer, int par2) 
	{
        ItemStack itemstack = null;
        Slot slot = (Slot)this.inventorySlots.get(par2);

        if (slot != null && slot.getHasStack())
        {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (par2 == 0)
            {
                if (!this.mergeItemStack(itemstack1, 10, 46, true))
                {
                    return null;
                }

                slot.onSlotChange(itemstack1, itemstack);
            }
            else if (par2 >= 10 && par2 < 37)
            {
                if (!this.mergeItemStack(itemstack1, 37, 46, false))
                {
                    return null;
                }
            }
            else if (par2 >= 37 && par2 < 46)
            {
                if (!this.mergeItemStack(itemstack1, 10, 37, false))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(itemstack1, 10, 46, false))
            {
                return null;
            }

            if (itemstack1.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }

            if (itemstack1.stackSize == itemstack.stackSize)
            {
                return null;
            }

            slot.onPickupFromSlot(par1EntityPlayer, itemstack1);
        }

        return itemstack;
    }

}