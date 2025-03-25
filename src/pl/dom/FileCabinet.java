package pl.dom;

import java.util.List;
import java.util.Optional;

public class FileCabinet implements Cabinet {
//	private List<Folder> folders;
	private FolderCabinet folderCabinet= new FolderCabinet();
	
	private List<Folder> folders = 	List.of(
			new FolderImpl("m0", "LARGE"),
			new MultiFolderImpl("m1", "SMALL", List.of(new FolderImpl("m1", "SMALL"), new FolderImpl("m2", "SMALL"))),
			new MultiFolderImpl("m2", "SMALL", List.of(new FolderImpl("m1", "SMALL"), new FolderImpl("m2", "SMALL"))),
			new MultiFolderImpl("m1", "MEDIUM", List.of(new FolderImpl("m1", "SMALL"), new FolderImpl("m3", "SMALL"))),
//			new MultiFolderImpl("m4", "MEDIUM", List.of(new FolderImpl("m1", "SMALL"), new FolderImpl("m2", "SMALL"))),
			new MultiFolderImpl("m5", "MEDIUM", List.of(new FolderImpl("m1", "SMALL"), new FolderImpl("m2", "LARGE")))
			);
	
	public static void main(String args[]) {
		FileCabinet fc = new FileCabinet();
		Optional<Folder> tmp1 = fc.findFolderByName("m2");
		if(!tmp1.isEmpty())
			System.out.println("Founded folder: " + tmp1.get().getName()+ " " + tmp1.get().getSize());
		else
			System.out.println("No file named found");
		
		
		List<Folder> tmp2 = fc.findFoldersBySize("SMALL");
		tmp2.stream().forEach(folder -> System.out.println(folder.getName() + " " + folder.getSize()));
		
		System.out.println("Rozmiar listy " + fc.count());
	}
	
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


