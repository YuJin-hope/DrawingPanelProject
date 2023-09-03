package frames;

import javax.swing.JMenuBar;

public class GMenuBar extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private GFileMenu fileMenu; // 이 방에서만 쓰는 게 아니라 바깥에서도 쓰게 하기 위해서
	
	
	public GMenuBar() {
		
		fileMenu = new GFileMenu("file");
		this.add(fileMenu);
		
		
	}
}
