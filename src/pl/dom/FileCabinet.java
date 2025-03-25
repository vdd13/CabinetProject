package pl.dom;

import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
	private List<Folder> folders;
	private FolderCabinet folderCabinet= new FolderCabinet();
	
	@Override
	public Optional<Folder> findFolderByName(String name) {
		return folderCabinet.findFolderByName(folders, name);
	}

	@Override
	public List<Folder> findFoldersBySize(String size) {
		return folderCabinet.findFoldersBySize(folders, size);
	}

	@Override
	public int count() {
		return folderCabinet.count(folders);
	}

}


