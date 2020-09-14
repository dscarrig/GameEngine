package banks;

import overlayInterface.DeathMessage;
import overlayInterface.HealthHeart;
import overlayInterface.Overlay;
import overlayInterface.OverlayComponent;
import overlayInterface.PauseScreen;

public class OverlayComponentBank
{
	public OverlayComponentBank()
	{
	}

	public OverlayComponent getComponent(String name, Overlay ov)
	{
		OverlayComponent newComponent = null;

		switch (name)
		{
		
		case "HealthHeart":
			newComponent = new HealthHeart(ov, "");
			break;
			
		case "PauseScreen":
			newComponent = new PauseScreen(ov, "");
			break;
			
		case "DeathMessage":
			newComponent = new DeathMessage(ov, "");
			break;
			
		default:
			System.out.println("Component: " + name + " does not exist.");
		}
		
		return newComponent;
	}
}
