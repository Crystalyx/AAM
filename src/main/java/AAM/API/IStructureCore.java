package AAM.API;

import AAM.Utils.Structure;
import AAM.Utils.WorldPos;

/**
 * Class used to create objects that can represent Structure cores
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
	public WorldPos getOffset();

}
