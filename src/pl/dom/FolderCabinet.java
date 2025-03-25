package pl.dom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FolderCabinet {

	private static boolean isfolderFound = false;
	private static Optional<Folder> folderSearched = Optional.empty(); 
	private static List<Folder> foldersBySizeList = new ArrayList<Folder>();
	private static int sizeOfList = 0;
	
	public Optional<Folder> findFolderByName(List<Folder> folders, String name) {
		if(isDataEmpty(name) || isCollectionNull(folders))
			return Optional.empty();

//		mechanizm ze stream -> iteruje całą kolekcję, 
//		lepiej z pętlą 'for' i flagą 'folderFound' która kończy iterację pętli po znalezieniu szukanego elementu
		for(int i = 0; i < folders.size() && !isfolderFound; i++) {
			searchFolderByName(folders.get(i), name);
		}
		return folderSearched;
	}
	
	private void searchFolderByName(Folder folder, String name) {
		if(name.equals(folder.getName())) {
			isfolderFound = true;
			folderSearched = Optional.of(folder);
		}
		if(folder instanceof MultiFolder) {
			findFolderByName(((MultiFolder)folder).getFolders(), name);
		}
	}

	public List<Folder> findFoldersBySize(List<Folder> folders, String size) {
		if(!isSize(size))
			return Collections.emptyList();
		
		folders.stream()
			.forEach(folder -> searchFolderForSize(size,folder));
		return foldersBySizeList;
	}

	private void searchFolderForSize(String size, Folder folder) {
		if(size.equals(folder.getSize())) {
			foldersBySizeList.add(folder);
		}
		if(folder instanceof MultiFolder) {
			findFoldersBySize(((MultiFolder)folder).getFolders(), size);
		}
	}
	
	public int count(List<Folder> folders) {
		if(isCollectionNull(folders))
			return sizeOfList;
		
		folders.stream()
			.forEach(folder -> {
				sizeOfList++;
				countFolderOfMultiFolder(folder);
			});
		return sizeOfList;
	}

	private void countFolderOfMultiFolder(Folder folder) {
		if(folder instanceof MultiFolder) {
			count(((MultiFolder)folder).getFolders());
		}
	}
	
	private boolean isDataEmpty(String data) {
		return (data == null || data.isEmpty()) ? true : false;
	}
	
	private boolean isCollectionNull(List<Folder> folders) {
		return (folders == null) ? true : false;
	}
	
	private boolean isSize(String aSize) {
		if(isDataEmpty(aSize))
			return false;
		
		for(Size size : Size.values()) {
			if (size.name().equals(aSize))
				return true;
		}
		return false;
	}
	
	enum Size {
		SMALL,
		MEDIUM,
		LARGE
	}
	
}
