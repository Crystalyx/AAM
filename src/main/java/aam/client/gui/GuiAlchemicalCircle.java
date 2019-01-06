package aam.client.gui;

import aam.client.gui.base.GuiOBJ;
import aam.common.tiles.TETransCircle;
import aam.common.transmutations.Circle;
import aam.common.transmutations.CircleUtils;
import aam.common.transmutations.ModCircles;
import aam.network.packages.AlchemicalDispatcher;
import aam.network.packages.AlchemicalPackage;
import aam.network.CircleChangePackage;
import aam.utils.Graph;
import aam.utils.MiscUtils;
import aam.utils.PlayerDataHandler;
import aam.utils.vectors.VectorWorld;
import aam.utils.vectors.Wec3;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

public class GuiAlchemicalCircle extends GuiScreen
{

	private int x;
	private int y;
	int w = 256;
	int h = 200;

	public Wec3 tePos;

	public GuiAlchemicalCircle(Wec3 tePos)
	{
		this.tePos = tePos;
	}

	@Override
	public void drawScreen(int mx, int my, float z)
	{
		super.drawScreen(mx, my, z);

		try
		{
			GL11.glPushMatrix();

			GL11.glTranslated(x - w / 2, y - h / 2, z);
			MiscUtils.bindTexture(GuiOBJ.baseTexture);
			Graph.drawSizedSqr(this, 256, 256, 36, 36, w, h, 220, 184, 12, 12);

			GL11.glTranslated(w / 2 - 72, h / 2 - 72, 0);
			Graph.drawSizedSqr(this, 256, 256, 32, 32, 140, 140, 72, 0, 4, 4);

			TileEntity teRaw = new VectorWorld(mc.theWorld).getTile(tePos);

			if (teRaw instanceof TETransCircle)
			{
				TETransCircle te = (TETransCircle) teRaw;
				double maxSize = CircleUtils.getMaxScale(te.circle);
				GL11.glPushMatrix();
				GL11.glTranslated(72, 72, 0);
				GL11.glScaled(0.5, 0.5, 1);
				GL11.glScaled(1 / maxSize, 1 / maxSize, 1);
				for (Circle c : te.circle)
				{
					GL11.glPushMatrix();
					GL11.glScaled(c.scale, c.scale, 1);
					int wh = 256;
					CircleUtils.bindBlockTexture(c);

					GL11.glTranslated(-64 * 2, -64 * 2, 0);
					this.drawTexturedModalRect(0, 0, 0, 0, wh, wh);

					GL11.glPopMatrix();
				}
				GL11.glPopMatrix();
			}

			GL11.glPopMatrix();
		}
		catch (Exception e)
		{
			// Handling crash when login and seeing extended circle
		}
	}

	@Override
	public void updateScreen()
	{
		super.updateScreen();
	}

	@Override
	public void initGui()
	{
		super.initGui();
		ScaledResolution sr = new ScaledResolution(mc, mc.displayWidth, mc.displayHeight);
		x = sr.getScaledWidth() / 2;
		y = sr.getScaledHeight() / 2;
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		for (int i = 0; i < ModCircles.count; i++)
		{
			int ix = Math.floorDiv(i, 6);
			int iy = Math.floorMod(i, 6);
			CircleButton brm = new CircleButton(i, x + w / 2 + ix * 32, y - h / 2 + iy * 32);
			buttonList.add(brm);
		}

		World world = mc.theWorld;

		TileEntity teRaw = new VectorWorld(world).getTile(tePos);
		if (teRaw instanceof TETransCircle)
		{
			TETransCircle te = (TETransCircle) teRaw;
			for (int i = 0; i < te.circle.size(); i++)
			{
				int iy = Math.floorDiv(i, 8);
				int ix = Math.floorMod(i, 8);
				CircleRemoveButton brm = new CircleRemoveButton(i + bigVal, x - w / 2 + ix * 32, y - h / 2 - 32 - iy * 32, te.circle.get(i));
				buttonList.add(brm);
			}
		}
	}

	public static final int bigVal = 200;;

	@Override
	public void actionPerformed(GuiButton b)
	{
		World world = mc.theWorld;
		TileEntity teRaw = new VectorWorld(mc.theWorld).getTile(tePos);

		if (teRaw instanceof TETransCircle)
		{
			TETransCircle te = (TETransCircle) teRaw;
			if (b.id < ModCircles.count)
			{

				boolean cont = false;
				Circle pr = new Circle(ModCircles.parts.get(b.id), 1, Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) || Keyboard.isKeyDown(Keyboard.KEY_RSHIFT));

				boolean added = CircleUtils.tryToAddCircle(te, pr);

				AlchemicalPackage ap = new CircleChangePackage(ModCircles.getCodeStr(pr), tePos.ix, tePos.iy, tePos.iz, 0);
				AlchemicalDispatcher.sendToServer(ap);
				// Logger.chat(this.mc.thePlayer, "Client: " +
				// CircleUtils.outputCircle(te.circle));

				if (added)
				{
					int i = te.circle.size() - 1;
					int iy = Math.floorDiv(i, 8);
					int ix = Math.floorMod(i, 8);
					CircleRemoveButton brm = new CircleRemoveButton(i + bigVal, x - w / 2 + ix * 32, y - h / 2 - 32 - iy * 32, pr);
					buttonList.add(brm);
				}
				slideButtons();
			}
			else
			{
				if (b instanceof CircleRemoveButton)
				{
					CircleRemoveButton cb = (CircleRemoveButton) b;
					Circle cir = cb.circle;
					te.circle.remove(cir);
					buttonList.remove(b);
					slideButtons();
					AlchemicalPackage ap = new CircleChangePackage(ModCircles.getCodeStr(cir), tePos.ix, tePos.iy, tePos.iz, 1);
					AlchemicalDispatcher.sendToServer(ap);
				}
			}
		}
	}

	public void slideButtons()
	{
		int i = 0;
		for (Object ob : buttonList)
		{
			if (ob instanceof CircleRemoveButton)
			{
				CircleRemoveButton b = (CircleRemoveButton) ob;
				int iy = Math.floorDiv(i, 8);
				int ix = Math.floorMod(i, 8);
				b.xPosition = x - w / 2 + ix * 32;
				b.yPosition = y - h / 2 - 32 - iy * 32;
				i += 1;
			}
		}
	}

	@Override
	public boolean doesGuiPauseGame()
	{
		return true;
	}

	@Override
	protected void keyTyped(char par1, int par2)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);

		if (par2 == 1 || par2 == mc.gameSettings.keyBindInventory.getKeyCode())
		{
			mc.thePlayer.closeScreen();
		}
	}

	@Override
	protected void mouseClicked(int x, int y, int mouseButton)
	{
		PlayerDataHandler ph = PlayerDataHandler.get(Minecraft.getMinecraft().thePlayer);
		super.mouseClicked(x, y, mouseButton);

	}
}
