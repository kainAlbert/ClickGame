package Object.Character;

import Application.Application;
import Application.Define;
import Application.Direction;
import Application.GSvector2;
import Object.Effect.ImpactEffect;

public class Bullet extends CharacterBase{

	GSvector2 mTargetPos;

	// コンストラクタ
	public Bullet( GSvector2 pos, int force ){
		super( null, null, "bullet", pos, 0, 0 );

		mForce = force;
		mSize = new GSvector2( Define.BULLET_SIZE, Define.BULLET_SIZE );
	}

	// 初期化
	public void initialize(){

		if( mForce == Define.BASE_FORCE.PLAYER.ordinal() ){

			mTargetPos = new GSvector2( Define.BASE_ENEMY_INIT_POS.x, Define.BASE_ENEMY_INIT_POS.y );
		}
		if( mForce == Define.BASE_FORCE.ENEMY.ordinal() ){

			mTargetPos = new GSvector2( Define.BASE_PLAYER_INIT_POS.x, Define.BASE_PLAYER_INIT_POS.y );
		}

		mReSize.x = ( mForce + 1 ) * Define.BASE_RESIZE;
	}

	// 更新
	public void update(){

		GSvector2 vel = Direction.getToVelocity( mPos, mTargetPos );

		mPos.x += vel.x * Define.BULLET_SPEED;
		mPos.y += vel.y * Define.BULLET_SPEED;

		mAngle = Direction.getToVelocity( vel ) + 90;
	}

	// 衝突
	public void collision(){

		mIsDead = true;

		CharacterBase e = new ImpactEffect( new GSvector2( mPos.x, mPos.y ), mSize.x );

		Application.getObj().getEM().addEffectList( e );
	}
}
