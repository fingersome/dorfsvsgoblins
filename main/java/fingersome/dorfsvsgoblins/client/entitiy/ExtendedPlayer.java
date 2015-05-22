package fingersome.dorfsvsgoblins.client.entitiy;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties
{
/*
Here I create a constant EXT_PROP_NAME for this class of properties. You need a unique name for every instance of 
IExtendedEntityProperties you make, and doing it at the top of each class as a constant makes
it very easy to organize and avoid typos. It's easiest to keep the same constant name in every class, 
as it will be distinguished by the class name: ExtendedPlayer.EXT_PROP_NAME vs. ExtendedEntity.EXT_PROP_NAME

Note that a single entity can have multiple extended properties, so each property should have a unique name. 
Try to come up with something more unique than the tutorial example.
*/
public final static String EXT_PROP_NAME = "ExtendedPlayer";

// I always include the entity to which the properties belong for easy access
private final EntityPlayer player;

// Declare other variables you want to add here
// We're adding three different player types, i.e three different teams: no team, dwarves, and goblins
private int playerType;

/*
The default constructor takes no arguments, but I put in the Entity so I can initialize the above variable 'player'
Also, it's best to initialize any other variables you may have added, just like in any constructor.
*/
public ExtendedPlayer(EntityPlayer player)
{
this.player = player;
// Start with the default playerType. Every player starts with the "no team" type.
this.playerType = 0;
}

/**
* Used to register these extended properties for the player during EntityConstructing event
* This method is for convenience only; it will make your code look nicer
*/
public static final void register(EntityPlayer player)
{
player.registerExtendedProperties(ExtendedPlayer.EXT_PROP_NAME, new ExtendedPlayer(player));
}

/**
* Returns ExtendedPlayer properties for player
* This method is for convenience only; it will make your code look nicer
*/
public static final ExtendedPlayer get(EntityPlayer player)
{
return (ExtendedPlayer) player.getExtendedProperties(EXT_PROP_NAME);
}

// Save any custom data that needs saving here
@Override
public void saveNBTData(NBTTagCompound compound)
{
// We need to create a new tag compound that will save everything for our Extended Properties
NBTTagCompound properties = new NBTTagCompound();

// We only have 1 variable currently; save it to the new tag
properties.setInteger("PlayerType", this.playerType);

/*
Now add our custom tag to the player's tag with a unique name (our property's name). This will allow you to save multiple types of properties and distinguish between them. If you only have one type, it isn't as important, but it will still avoid conflicts between your tag names and vanilla tag names. For instance, if you add some "Items" tag, that will conflict with vanilla. Not good. So just use a unique tag name.
*/
compound.setTag(EXT_PROP_NAME, properties);
}

// Load whatever data you saved
@Override
public void loadNBTData(NBTTagCompound compound)
{
// Here we fetch the unique tag compound we set for this class of Extended Properties
NBTTagCompound properties = (NBTTagCompound) compound.getTag(EXT_PROP_NAME);
// Get our data from the custom tag compound
this.playerType = properties.getInteger("PlayerType");

// Debug check
System.out.println("[DvG PROPS] Properties from NBT: " +  getTeam());
}

/*
Not sure what this is for but it's required by the interface...
*/
@Override
public void init(Entity entity, World world)
{
	
}

/**
* Returns team name for console
*/
public String getTeam()
{
	if(this.playerType == 1)
	{
		return "Dwarves!";
	}
	else if(this.playerType == 2)
	{
		return "Goblins!";
	}
	
	return "No team selected.";
}

/**
* Simple method sets player type
*/
public void setTeam(int team)
{
	this.playerType = team;
}

}