package pl.dom;

import java.util.List;

public class MultiFolderImpl extends FolderImpl implements MultiFolder{

	List<Folder> folders;
	
	public MultiFolderImpl(String name, String size, List<Folder> list) {
		super(name, size);
		this.folders = list;
	}

	@Override
	public List<Folder> getFolders() {
		return folders;
	}

}
