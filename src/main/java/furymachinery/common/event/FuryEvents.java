package furymachinery.common.event;

import java.awt.Color;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.client.event.RenderBlockOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class FuryEvents {
	public static Minecraft mc = Minecraft.getMinecraft();

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void overlayEvent(RenderGameOverlayEvent.Post event) {
		switch (event.getType()) {
		case TEXT:
			EntityPlayer playerIn = mc.player;
			FontRenderer renderer = mc.fontRendererObj;
			String string = playerIn.inventory.getStackInSlot(39).isEmpty() ? ""
					: (playerIn.inventory.getStackInSlot(39).getItem() == Item.getItemFromBlock(Blocks.PUMPKIN)
							? "you are wearing a pumpkin" : "you are wearing a hat");
			ScaledResolution size = event.getResolution();
			int x = 50;
			int y = size.getScaledHeight() - 20;
			renderer.drawStringWithShadow(string, x - (renderer.getStringWidth(string) / 2), y, Color.BLACK.getRGB());
			break;
		default:
			break;
		}
	}

	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public void overlayBlockEvent(RenderBlockOverlayEvent event) {
		EntityPlayer player = event.getPlayer();
		switch (event.getOverlayType()) {
		case BLOCK:
			player.sendMessage(new TextComponentString(event.getBlockForOverlay().getBlock().getUnlocalizedName()));
			break;
		default:
			break;
		}
	}
}
