package frames;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;
import javax.swing.JToolBar;

import main.GConstants.EShape;

public class GToolBar extends JToolBar {
	private static final long serialVersionUID = 1L;
	
	private ButtonGroup buttonGroup;
	private EShape eSelectedShape;
	public EShape getESelectedShape() {
		return this.eSelectedShape;
	}

	public void resetESelectedShape() {
		this.buttonGroup.clearSelection();	
		this.eSelectedShape = EShape.eSelect;
	}

	public GToolBar() {
		super();
		ActionHandler ActionHandler = new ActionHandler();
		buttonGroup = new ButtonGroup();
		for (EShape eSelectedShape : EShape.values()) {
			if (eSelectedShape != EShape.eSelect) {
			JRadioButton buttonShape = new JRadioButton(eSelectedShape.getName());
			this.add(buttonShape);
			buttonShape.setActionCommand(eSelectedShape.toString()); // 어떤 버튼 눌렸는지 출력문자로 구별
			buttonShape.addActionListener(ActionHandler);
			buttonGroup.add(buttonShape);
		}
		}
		resetESelectedShape();
	}

	class ActionHandler implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent event) {
			eSelectedShape = EShape.valueOf(event.getActionCommand());
		}
		
	}
}