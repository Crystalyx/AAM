package aam.api.animation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class AnimatedModel extends ModelBase
{
	public List<ModelRenderer> parts = new ArrayList<>();
	public List<Animation> animations = new ArrayList<>();

	public void addPart(ModelRenderer mr)
	{
		parts.add(mr);
	}

}
