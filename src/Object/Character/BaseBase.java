package Object.Character;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;

public class BaseBase extends CharacterBase{

	public BaseBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, pos, number, type );

	}

	// 更新
	public void update(){

		super.update();

		if( mForce == Define.BASE_FORCE.NONE.ordinal() ) return;


	}
}
