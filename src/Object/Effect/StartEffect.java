package Object.Effect;
import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class StartEffect extends CharacterBase{

	// コンストラクタ
	public StartEffect( String fileName, int force ){

		super( null, null, fileName, Define.WIN_LOSE_EFFECT_POS, 0, 0 );

		mForce = force;
		mSize = new GSvector2( Define.WIN_LOSE_EFFECT_SIZE.x, Define.WIN_LOSE_EFFECT_SIZE.y );
		mReSize = new GSvector2( Define.WIN_LOSE_EFFECT_RESIZE.x, Define.WIN_LOSE_EFFECT_RESIZE.y );
		mFirstReSize = new GSvector2( Define.WIN_LOSE_EFFECT_RESIZE.x, Define.WIN_LOSE_EFFECT_RESIZE.y );
	}

	// 更新
	public void update(){

		if( Application.getObj().getIsStart() ){ mIsDead = true; }

		if( mForce != Application.getID() ){ mIsDead = true; }
	}
}
