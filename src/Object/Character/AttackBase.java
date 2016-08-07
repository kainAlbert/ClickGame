package Object.Character;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.MesgRecvThread;
import Application.Panel;

public class AttackBase extends CharacterBase{

	private int mTimer;

	public AttackBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, Define.FILE_NAME[ type ], pos, number, type );

		mTimer = 0;
	}

	// 初期化
	public void initialize(){

		mTimer = 0;
	}

	// 更新
	public void update(){

		super.update();

		if( Application.getObj().getIsEnd() ) finish();

		if( mForce != Application.getID() ) return;

		if( mForce == Define.BASE_FORCE.NONE.ordinal() ) return;

		mTimer++;

		double energy = Application.getObj().getCM().getEnergy();

		if( mTimer > Define.BULLET_MAX_TIMER ){

			createBullet();

			mTimer = 0;
			return;
		}

		if( mTimer < Define.BULLET_TIMER || energy < Define.BULLET_TIMER ) return;

		mTimer = 0;
		Application.getObj().getCM().addEnergy( -Define.BULLET_TIMER );

		createBullet();
	}

	// 弾を発射
	private void createBullet(){

		CharacterBase b = new Bullet( new GSvector2( mPos.x, mPos.y ), mForce );
		b.initialize();

		if( mForce == Define.TYPE_PLAYER ){

			Application.getObj().getCM().addPlayerBulletList( b );
		}
		if( mForce == Define.TYPE_ENEMY ){

			Application.getObj().getCM().addEnemyBulletList( b );
		}

		MesgRecvThread.outServer( Application.mID + Define.STR_D + Define.STR_CREATE_BULLET + Define.STR_D + mNumber);
	}
}
