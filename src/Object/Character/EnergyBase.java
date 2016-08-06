package Object.Character;

import Application.Application;
import Application.GSvector2;
import Application.Panel;

public class EnergyBase extends CharacterBase{

	public EnergyBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, pos, number, type );

	}

	// 更新
	public void update(){

		super.update();

		if( mForce != Application.getID() ) return;

		Application.getObj().getCM().addEnergy();
	}
}
