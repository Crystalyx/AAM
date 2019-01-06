package aam.api.animation;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

import java.util.ArrayList;
import java.util.List;

public class AnimatedModel extends ModelBase
{
	public List<ModelRenderer> parts = new ArrayList<>();
	public List<Animation> animations = new ArrayList<>();

	public void addPart(ModelRenderer mr)
	{
		parts.add(mr);
	}

}
