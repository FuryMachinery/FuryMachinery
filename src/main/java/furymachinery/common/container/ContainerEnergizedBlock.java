package furymachinery.common.container;

import furymachinery.api.EnumTier;
import furymachinery.client.network.message.MessageUpdateContainerDouble;
import furymachinery.common.FuryMachinery;
import furymachinery.common.tile.TileEntityEnergizedBlock;
import furymachinery.common.tile.TileEntityGenerator;
import furymachinery.common.tile.TileEntitySolarGenerator;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerEnergizedBlock extends Container {
	TileEntityEnergizedBlock tile;
	// all
	public double energy, capacity, maxTransfer, typeID;
	// solar generators
	public double mirrorCount, generateAmount;
	IContainerListener listener;

	protected ContainerEnergizedBlock(InventoryPlayer playerInv, TileEntityEnergizedBlock tile, EnumTier tier) {
		this.tile = tile;
		for (int y = 0; y < 3; ++y) {
			for (int x = 0; x < 9; ++x) {
				this.addSlotToContainer(new Slot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18));
			}
		}

		for (int x = 0; x < 9; ++x) {
			this.addSlotToContainer(new Slot(playerInv, x, 8 + x * 18, 142));
		}
	}

	@Override
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		for (int i = 0; i < this.listeners.size(); i++) {
			IContainerListener listener = (IContainerListener) listeners.get(i);
			this.listener = listener;
			sync(0, energy);
			sync(1, capacity);
			sync(2, maxTransfer);
			sync(3, typeID);
			if (tile instanceof TileEntityGenerator)
				sync(4, generateAmount);
			if (tile instanceof TileEntitySolarGenerator)
				sync(5, mirrorCount);
		}
		endSync(0, energy);
		endSync(1, capacity);
		endSync(2, maxTransfer);
		endSync(3, typeID);
		if (tile instanceof TileEntityGenerator)
			endSync(4, generateAmount);
		if (tile instanceof TileEntitySolarGenerator)
			endSync(5, mirrorCount);
	}

	@Override
	public boolean canInteractWith(EntityPlayer playerIn) {
		return tile.isUsableByPlayer(playerIn);
	}

	public void setDouble(int id, double value) {
		tile.setDField(id, value);
	}

	public void sync(int id, double value) {
		if (value != tile.getDField(id))
			FuryMachinery.NETWORK.sendTo(new MessageUpdateContainerDouble(id, tile.getDField(id)),
					(EntityPlayerMP) listener);
	}

	public void endSync(int id, double value) {
		value = tile.getDField(id);
	}
	@Override
	public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
		return super.transferStackInSlot(playerIn, index);
	}
}
