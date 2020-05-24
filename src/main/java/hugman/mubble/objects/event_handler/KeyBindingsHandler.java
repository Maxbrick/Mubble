package hugman.mubble.objects.event_handler;

import hugman.mubble.init.data.MubbleKeyBindings;
import net.minecraftforge.client.event.InputEvent.KeyInputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class KeyBindingsHandler
{
	@SubscribeEvent
	public void onKeyPress(KeyInputEvent event)
	{
		if(MubbleKeyBindings.OUTFIT_KEY.isPressed())
		{
			//Minecraft.getInstance().getConnection().sendPacket
			//Minecraft.getInstance().displayGuiScreen(new OutfitScreen(Minecraft.getInstance().player));
		}
	}
}