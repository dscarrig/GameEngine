package environment;

import java.awt.event.*;

import javax.swing.Timer;

import overlayInterface.OverlayComponent;

/*
 * 	Class containing all the input listeners.
 */
public class Listeners
{

	private Environment env;
	private Timer timer;

	public Listeners(Environment e)
	{
		env = e;

		env.addMouseListener(new ClickEvent());
		env.addKeyListener(new KListener());
	}

	public void startTimer(int ms)
	{
		timer = new Timer(ms, new TimerListener());
		timer.start();
	}

	private class TimerListener implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			env.frameProgress();
		}
	}

	private class ClickEvent implements MouseListener
	{

		@Override
		public void mouseClicked(MouseEvent arg0)
		{
		}

		@Override
		public void mouseEntered(MouseEvent arg0)
		{
		}

		@Override
		public void mouseExited(MouseEvent arg0)
		{
		}

		@Override
		public void mousePressed(MouseEvent arg0)
		{
			OverlayComponent[] allComps = env.getOverlay().getOverlayComponents();

			for (int i = 0; i < env.getOverlay().getNumComponents(); i++)
				allComps[i].clickPressedAt(arg0.getX(), arg0.getY());

			env.getPlayer().clickPressedAt(arg0.getX(), arg0.getY());
		}

		@Override
		public void mouseReleased(MouseEvent arg0)
		{
			OverlayComponent[] allComps = env.getOverlay().getOverlayComponents();

			for (int i = 0; i < env.getOverlay().getNumComponents(); i++)
				allComps[i].clickReleasedAt(arg0.getX(), arg0.getY());

			env.getPlayer().clickReleasedAt(arg0.getX(), arg0.getY());
		}

	}

	private class KListener implements KeyListener
	{

		@Override
		public void keyPressed(KeyEvent e)
		{
			int key = e.getKeyCode();

			env.getPlayer().keyPressed(key);
		}

		@Override
		public void keyReleased(KeyEvent e)
		{
			int key = e.getKeyCode();

			env.getPlayer().keyReleased(key);
		}

		@Override
		public void keyTyped(KeyEvent arg0)
		{
		}
	}

}
