package Object.Character;

import java.awt.Container;

import Application.Application;
import Application.Define;
import Application.GSvector2;

public class AttackBase extends CharacterBase{

	public AttackBase( Application app, Container c, GSvector2 pos, int number, int type ) {
		super( app, c, pos, number, type );

	}

	// 更新
	public void update(){

		super.update();

		if( mForce == Define.BASE_FORCE.NONE.ordinal() ) return;

		
	}
}
