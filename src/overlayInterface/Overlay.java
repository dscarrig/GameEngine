package overlayInterface;

import java.awt.Graphics;

import banks.OverlayComponentBank;
import environment.Environment;

public class Overlay
{

	private static final int MAX_COMPONENTS = 500;

	private int componentCount;

	private OverlayComponentBank componentBank;
	private Environment env;
	private OverlayComponent[] components = new OverlayComponent[MAX_COMPONENTS];

	public Overlay(Environment e)
	{
		componentBank = new OverlayComponentBank();
		componentCount = 0;
		env = e;
	}

	public void addComponent(OverlayComponent oc)
	{
		if (componentCount < MAX_COMPONENTS)
		{
			int index = 0;
			while (index < componentCount)
			{
				if (components[index].getType().equals("Blank"))
					break;
				else
					index++;
			}

			components[index] = oc;

			if (index == componentCount)
				componentCount++;
		} else
			System.out.println("Maximum components");
	}

	public void addComponentFromBank(String componentName)
	{
		OverlayComponent oc = componentBank.getComponent(componentName, this);

		oc.setName(oc.getType());

		if (componentCount < MAX_COMPONENTS)
		{
			int index = 0;
			while (index < componentCount)
			{
				if (components[index].getType().equals("Blank"))
					break;
				else
					index++;
			}

			components[index] = oc;

			if (index == componentCount)
				componentCount++;
		} else
			System.out.println("Maximum components");
	}

	public void removeComponent(OverlayComponent oc)
	{
		int index = 0;

		while (!components[index].equals(oc) && index < components.length)
			index++;

		if (index < components.length)
		{
			components[index].setType("Blank");
			components[index].setName("");
		}
	}

	public void removeComponentByName(String name)
	{
		int index = 0;

		while (!components[index].getName().equals(name) && index < components.length)
			index++;

		if (index < components.length)
		{
			components[index].setType("Blank");
			components[index].setName("");
		}
	}

	public Environment getEnvironment()
	{
		return env;
	}

	public OverlayComponent[] getOverlayComponents()
	{
		return components;
	}

	public int getNumComponents()
	{
		return componentCount;
	}

	public void paintOverlay(Graphics g)
	{
		for (int i = 0; i < componentCount; i++)
		{
			OverlayComponent curComponent = components[i];

			if (curComponent.hasSprite() && !curComponent.getType().equals("Blank"))
			{
				g.drawImage(curComponent.getImage(), curComponent.getXPos(), curComponent.getYPos(), env);
			}
		}
	}
}
