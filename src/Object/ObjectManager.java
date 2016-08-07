package Object;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;
import Object.Character.BaseFactory;
import Object.Character.CharacterBase;
import Object.Character.CharacterManager;
import Object.Effect.EffectManager;
import Object.Effect.ImpactEffect;
import Object.Effect.WinLoseEffect;

public class ObjectManager {

	private boolean mIsStart;
	private boolean mIsEnd;
	private boolean mIsWin;

	private CharacterManager mCM;
	private BaseFactory mBF;
	private Collision mCollision;
	private EffectManager mEM;

	// コンストラクタ
	public ObjectManager( Application app, Panel p ){

		mIsStart = false;
		mIsEnd = false;
		mIsWin = false;

		mCM = new CharacterManager( app, p );
		mBF = new BaseFactory( app, p );
		mCollision = new Collision();
		mEM = new EffectManager();
	}

	// 更新
	public void update(){

		mCM.update();
		mBF.update();
		mCollision.update();
		mEM.update();
	}

	// 勝った時の処理
	public void isWin(){

		mIsWin = true;
		mIsEnd = true;

		GSvector2 pos = Application.getID() == Define.TYPE_PLAYER ? Define.BASE_ENEMY_INIT_POS : Define.BASE_PLAYER_INIT_POS;

		// 本拠地爆発
		CharacterBase impact = new ImpactEffect( pos, Define.BASE_SIZE * 1.5 );
		mEM.addEffectList( impact );

		// 勝利画像
		CharacterBase win = new WinLoseEffect( "win" );
		mEM.addEffectList( win );

	}

	// 負けた時の処理
	public void isLose(){

		mIsWin = false;
		mIsEnd = true;

		GSvector2 pos = Application.getID() == Define.TYPE_PLAYER ? Define.BASE_PLAYER_INIT_POS : Define.BASE_ENEMY_INIT_POS;

		// 本拠地爆発
		CharacterBase impact = new ImpactEffect( pos, Define.BASE_SIZE * 1.5 );
		mEM.addEffectList( impact );

		// 敗北画像
		CharacterBase lose = new WinLoseEffect( "lose" );
		mEM.addEffectList( lose );
	}

	// スタート
	public void goStart(){ mIsStart = true; }

	// ゲッター
	public boolean getIsStart(){ return mIsStart; }
	public boolean getIsEnd(){ return mIsEnd; }
	public boolean getIsWin(){ return mIsWin; }
	public CharacterManager getCM(){ return mCM; }
	public BaseFactory getBF(){ return mBF; }
	public Collision getCollision(){ return mCollision; }
	public EffectManager getEM(){ return mEM; }

}
