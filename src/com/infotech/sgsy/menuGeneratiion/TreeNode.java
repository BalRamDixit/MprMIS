package com.infotech.sgsy.menuGeneratiion;
import java.io.Serializable;

public class TreeNode implements Serializable{
	public int nodeId;
	public String name;
	public int parentId;
	public String url;
	
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public TreeNode(int id, String name, int parentId,String url) {
		this.nodeId = id;
		this.name = name;
		this.parentId = parentId;
		this.url=url;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNodeId() {
		return nodeId;
	}

	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}

	public int getParentId() {
		return parentId;
	}
	
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}
	
	public void print() {
		System.out.println(nodeId + "\t" + name + "\t" + parentId + "\n");
	}
}
