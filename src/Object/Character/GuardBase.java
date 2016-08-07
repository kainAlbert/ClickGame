package Object.Character;

import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Application.Panel;
import Object.Effect.GuardDistanceEffect;

public class GuardBase extends CharacterBase{

	private CharacterBase mEffect;

	public GuardBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, Define.FILE_NAME[ type ], pos, number, type );

		mEffect = null;
	}

	// 更新
	public void update(){

		super.update();

		if( !Application.getObj().getIsStart() ) return;

		createEffect();

		if( Application.getObj().getIsEnd() ) finish();

		if( mForce != Application.getID() ) return;

		CharacterManager cm = Application.getObj().getCM();

		double energy = cm.getEnergy();

		// ガードを展開してサーバーに送る
		guard( energy );

		if( energy >= Define.GUARD_ENERGY ) cm.addEnergy( -Define.GUARD_ENERGY );

		MesgRecvThread.outServer( Application.getID() + Define.STR_D + Define.STR_COLLISION_GUARD + Define.STR_D + mNumber + Define.STR_D + energy );
	}

	// ガードエフェクト生成
	private void createEffect(){

		if( mEffect != null ) return;

		CharacterBase e = new GuardDistanceEffect();

		Application.getObj().getEM().addEffectList( e );

		mEffect = e;
	}

	// ガードを展開
	public void guard( double energy ){

		GSvector2 pos = new GSvector2( mPos.x + mSize.x / 2 - Define.GUARD_DISTANCE, mPos.y + mSize.y / 2 - Define.GUARD_DISTANCE );

		((GuardDistanceEffect)mEffect).update( energy, pos, mForce );

		if( energy < Define.GUARD_ENERGY ) return;

		List<CharacterBase> list = null;

		CharacterManager cm = Application.getObj().getCM();

		if( mForce == Define.TYPE_PLAYER ) list = cm.getEnemyBulletList();
		if( mForce == Define.TYPE_ENEMY ) list = cm.getPlayerBulletList();

		Application.getObj().getCollision().collisionBulletGuard( this, list );


	}
}
