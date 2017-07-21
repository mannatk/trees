package trees;

import java.util.*;


class TreeNode 
{
	private String					name;
	private TreeNode				parent;
	private ArrayList<TreeNode>		children;
	
	
	TreeNode(String name)
	{
		this.name = name;
		children = new ArrayList<>();
	}
	
	
	String getName()
	{
		return name;
	}
	
	
	void addChild(TreeNode childNode)
	{
		// Add childNode to this node's children list. Also
		// set childNode's parent to this node.
		children.add(childNode);
		childNode.parent = this;
	}
	
	
	// Searches subtree at this node for a node
	// with the given name. Returns the node, or null if not found.
	TreeNode getNodeWithName(String targetName)
	{
		// Does this node have the target name?
		if (name.equals(targetName))
			return this;
		
		// Does an immediate child have the target name?
		for (TreeNode child: children)
		{
			// If child's name is targetName, return child.
			if(child.getName().equals(targetName)) {
				return child;
			}
		}
		
		// No, recurse.
		for (TreeNode child: children)
		{
			// If child.getNodeWithName(targetName) returns a non-null node, 
			// then that's the node we're looking for. Return it.
			if(child.getNodeWithName(targetName) != null) {
				return child.getNodeWithName(targetName);
			}
		}
		
		// Not found anywhere.
		return null;
	}
	
	
	// Returns a list of ancestors of this TreeNode. Order is from recent to ancient.
	ArrayList<TreeNode> collectAncestorsToList()
	{
		ArrayList<TreeNode> ancestors = new ArrayList<>();
		TreeNode ancestor = parent;
		while (ancestor != null)
		{
			// Add ancestor to the ancestors list. Then set ancestor to be its own parent.
			ancestors.add(ancestor);
			ancestor = ancestor.parent; 
		}
		return ancestors;
	}
	
	
	public String toString()
	{
		return toStringWithIndent("");
	}
	
	
	private String toStringWithIndent(String indent)
	{
		String s = indent + name + "\n";
		indent += "  ";
		for (TreeNode childNode: children)
			s += childNode.toStringWithIndent(indent);
		return s;
	}
}
