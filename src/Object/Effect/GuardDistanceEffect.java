package Object.Effect;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;

public class GuardDistanceEffect extends CharacterBase{

	private double mBeforeSizeX;

	// コンストラクタ
	public GuardDistanceEffect(){

		super( null, null, "guardDistance", new GSvector2(), 0, 0 );

		mSize = new GSvector2( Define.GUARD_DISTANCE * 2, Define.GUARD_DISTANCE * 2 );
		mReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );
		mFirstReSize = new GSvector2( Define.BASE_RESIZE, Define.BASE_RESIZE );

		mBeforeSizeX = 0;
	}

	// 更新
	public void update(){

		if( mBeforeSizeX != 0 ) mSize.x = 0;

		mBeforeSizeX = mSize.x;
	}

	// 大きさ更新
	public void update( double energy, GSvector2 pos, int force ){

		mPos = pos;

		mReSize.x = ( force + 1 ) * Define.BASE_RESIZE;

		if( energy < Define.GUARD_ENERGY ) return;

		mSize.x = Define.GUARD_DISTANCE * 2;
	}
}
