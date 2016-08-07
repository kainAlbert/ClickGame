package Object.Character;

import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Application.Panel;

public class BaseBase extends CharacterBase{

	public BaseBase( Application app, Panel p, GSvector2 pos, int number, int type ) {
		super( app, p, Define.FILE_NAME[ type ], pos, number, type );


	}

	// 更新
	public void update(){

		// 現在の位置をボタンに割り当てる
		mButton.setBounds( (int)mPos.x, (int)mPos.y, (int)mSize.x, (int)mSize.y );

		impact();

		if( mForce != Define.BASE_FORCE.NONE.ordinal() ) return;

		mForce = Define.TYPE_ENEMY;

		mReSize.x = ( mForce + 1 ) * Define.BASE_RESIZE;

		this.setImage();

		List<CharacterBase> list = new ArrayList<CharacterBase>();

		list = Application.getObj().getCM().getBaseList();

		for( int i=0; i<list.size(); i++ ){

			if( list.get(i).getForce() == Define.TYPE_PLAYER ) return;
		}

		mForce = Define.TYPE_PLAYER;

		mReSize.x = ( mForce + 1 ) * Define.BASE_RESIZE;

		this.setImage();
	}

	// 爆発
	private void impact(){

		if( !Application.getObj().getIsEnd() ) return;

		boolean isLose = Application.getID() == mForce && !Application.getObj().getIsWin();
		boolean isWin = Application.getID() != mForce && Application.getObj().getIsWin();

		if( !isLose && !isWin ) return;

		mPos = new GSvector2( -1000, -1000 );
	}

	// 衝突
	public void collision(){

		if( mForce != Application.getID() ) return;

		Application.getObj().getCM().damage(1);
	}
}
