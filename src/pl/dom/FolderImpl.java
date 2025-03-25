package pl.dom;


public class FolderImpl implements Folder{
	String name;
	String size;
	
	public FolderImpl(String name, String size) {
		this.name = name;
		this.size = size;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getSize() {
		return size;
	}
}
