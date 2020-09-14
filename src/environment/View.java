package environment;

import javax.swing.*;

public class View extends JFrame
{

	public static final int X_RESOLUTION = 1024;
	public static final int Y_RESOLUTION = 768;

	private static final long serialVersionUID = 1L;

	Environment environment;

	public View()
	{
		setSize(X_RESOLUTION, Y_RESOLUTION);
		setTitle("Game");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);

		environment = new Environment();
		add(environment);
	}

}
