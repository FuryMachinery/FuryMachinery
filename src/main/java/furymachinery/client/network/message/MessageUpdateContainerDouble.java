package furymachinery.client.network.message;

import furymachinery.common.container.ContainerEnergizedBlock;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.util.IThreadListener;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateContainerDouble implements IMessage {
	int id;
	double value;

	public MessageUpdateContainerDouble() {
	}

	public MessageUpdateContainerDouble(int id, double value) {
		this.id = id;
		this.value = value;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		value = buf.readDouble();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		buf.writeDouble(value);
	}

	public static class Handler implements IMessageHandler<MessageUpdateContainerDouble, IMessage> {

		@Override
		public IMessage onMessage(final MessageUpdateContainerDouble message, MessageContext ctx) {
			final IThreadListener thread = Minecraft.getMinecraft();
			thread.addScheduledTask(new Runnable() {
				@Override
				public void run() {
					((ContainerEnergizedBlock) Minecraft.getMinecraft().player.openContainer).setDouble(message.id,
							message.value);
				}
			});
			return null;
		}

	}

}
