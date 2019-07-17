package straywolfe.cookingwithtfc.common.item.itemblock;

import java.util.List;

import com.dunk.tfc.Core.TFCTabs;
import com.dunk.tfc.Items.ItemBlocks.ItemTerraBlock;
import com.dunk.tfc.TileEntities.TEPottery;
import com.dunk.tfc.api.TFCBlocks;
import com.dunk.tfc.api.Enums.EnumSize;
import com.dunk.tfc.api.Enums.EnumWeight;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import straywolfe.cookingwithtfc.common.tileentity.TileMixBowl;

public class ItemMixingBowl extends ItemTerraBlock
{

	public ItemMixingBowl(Block b)
	{
		super(b);
		setHasSubtypes(true);
		metaNames = new String[2];
		metaNames[0] = "Clay";
		metaNames[1] = "Ceramic";
		this.setCreativeTab(TFCTabs.TFC_POTTERY);
	}
	
	@Override
	public EnumSize getSize(ItemStack is)
	{
		return EnumSize.SMALL;
	}

	@Override
	public EnumWeight getWeight(ItemStack is)
	{
		return EnumWeight.LIGHT;
	}

	@Override
	public int getItemStackLimit(ItemStack is)
	{
		return 1;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void getSubItems(Item item, CreativeTabs tab, List metadata)
	{
		for(int i = 0; i < metaNames.length; i++)
			metadata.add(new ItemStack(this, 1, i));
	}
	
	@Override
	public boolean placeBlockAt(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ, int metadata)
	{
		if(metadata > 0)
		{
			if(!world.isSideSolid(x, y-1, z, ForgeDirection.UP))
				return false;
			
			if (!world.setBlock(x, y, z, field_150939_a, metadata&15, 3))
				return false;

			if (world.getBlock(x, y, z) == field_150939_a)
			{
				field_150939_a.onBlockPlacedBy(world, x, y, z, player, stack);
				field_150939_a.onPostBlockPlaced(world, x, y, z, 0);
				
				float size = 0.25F;
				float xMin = hitX - size;
				float xMax = hitX + size;
				float zMin = hitZ - size;
				float zMax = hitZ + size;
				
				if(!(xMin >= 0 && xMax <= 1 && zMin >= 0 && zMax <= 1))
				{
					if(xMin < 0)
						xMin = 0;
					
					if(xMax > 1)
						xMin = 1 - (size * 2);
					
					if(zMin < 0)
						zMin = 0;
					
					if(zMax > 1)
						zMin = 1 - (size * 2);
				}
				
				if(world.getTileEntity(x, y, z) instanceof TileMixBowl)
				{
					TileMixBowl te = (TileMixBowl)world.getTileEntity(x, y, z);
					te.setBowlCoord(xMin, 0);
					te.setBowlCoord(zMin, 1);
				}
				
				return true;
			}
		}
		else if(metadata == 0 && side == 1 && player.isSneaking())
		{
			Block base = world.getBlock(x, y-1, z);
			if(base != TFCBlocks.pottery && world.isAirBlock(x, y, z))
			{
				if(!world.isSideSolid(x, y-1, z, ForgeDirection.UP))
					return false;
				world.setBlock(x, y, z, TFCBlocks.pottery);
			}
			else
			{
				return false;
			}

			if (world.getTileEntity(x, y, z) instanceof TEPottery)
			{
				TEPottery te = (TEPottery) world.getTileEntity(x, y, z);
				if(te.canAddItem(0))
				{
					te.inventory[0] = stack.copy();
					te.inventory[0].stackSize = 1;
					world.markBlockForUpdate(x, y, z);
					return true;
				}
			}
		}

		return false;
	}
}
