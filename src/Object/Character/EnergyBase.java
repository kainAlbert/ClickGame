package Object.Character;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;

public class EnergyBase extends CharacterBase{

	public EnergyBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, Define.FILE_NAME[ type ], pos, number, type );

	}

	// 更新
	public void update(){

		super.update();

		if( Application.getObj().getIsEnd() ) finish();

		if( mForce != Application.getID() ) return;

		if( Application.getObj().getCM().getEnergy() >= Define.MAX_ENERGY ) return;

		Application.getObj().getCM().addEnergy( Define.ENERGY_ADD );
	}
}
