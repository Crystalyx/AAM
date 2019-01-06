/**
 * This Class Created By Lord_Crystalyx.
 */
package GF.Client.Render;

import GF.Client.Model.GoldPan;
import GF.Registry.Registry;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

public class GoldPanRender implements IItemRenderer {

	public GoldPanRender(int type) 
	{
		this.fullType=type;
	}
	
	public int fullType=0;

	@Override
	public boolean handleRenderType(ItemStack item, ItemRenderType type) {
		return true;
	}

	@Override
	public boolean shouldUseRenderHelper(ItemRenderType type, ItemStack item,ItemRendererHelper helper) {
		return true;
	}
	
	public ResourceLocation pant =  new ResourceLocation ("goldflushing:textures/misc/goldpan_"+Registry.names[this.fullType]+ ".png");
	GoldPan panm = new GoldPan(this.fullType);

	@Override
	public void renderItem(ItemRenderType type, ItemStack item, Object... data) 
	{
		GL11.glPushMatrix();
        GL11.glRotatef(180F, 0F, 0F, 359F);
        GL11.glTranslatef(0.0F, -2.0F, 0.0F);
        float scale = 1.0F;
        GL11.glScalef(2.0F, 2.0F, 2.0F);
        Minecraft.getMinecraft().renderEngine.bindTexture(this.pant);  

        panm.render(null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
        GL11.glPopMatrix();
	}

}
