package aam.api.interfaces;

import aam.utils.Structure;
import aam.utils.vectors.Wec3;

/**
 * Class used to create objects that can represent Structure cores
 * 
 * @author user
 */
public interface IStructureCore
{
	/**
	 * @return Structure that this represents
	 */
	public Structure getStructure();

	/**
	 * @return Vector from structure side to the core
	 */
	public Wec3 getOffset();

}
