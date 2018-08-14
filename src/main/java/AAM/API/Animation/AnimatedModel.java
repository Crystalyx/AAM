package AAM.API.Animation;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;

public class AnimatedModel extends ModelBase
{
	public List<ModelRenderer> parts = new ArrayList<ModelRenderer>();
	public List<Animation> animations = new ArrayList<Animation>();

	public void addPart(ModelRenderer mr)
	{
		this.parts.add(mr);
	}

}
