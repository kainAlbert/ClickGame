package Object.Character;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;

public class CharacterManager {

	private List<CharacterBase> mBaseList;

	// コンストラクタ
	public CharacterManager( Application app, Container c ){

		// リストを生成
		mBaseList = new ArrayList<CharacterBase>();
	}

	// 初期化
	public void initialize( Application app, Container c ){

		// ベースがなければ作成
		if( mBaseList.size() == 0 ){
			// ベース作成
			BasePosReader.readBasePos( app, c );
		}
	}

	// 更新
	public void update(){

		// 各ベースの更新
		for( int i=0; i<mBaseList.size(); i++ ){

			mBaseList.get(i).update();
		}
	}

	public void test( int number ){
		mBaseList.get(number).test();
	}

	// 勢力変更
	public void changeForce( int number ){

		int id = Application.getID();
		CharacterBase b = mBaseList.get(number);

		if( b.getForce() == Define.BASE_FORCE.NONE.ordinal() ){

			b.changeForce( 1 );
			return;
		}

		b.changeForce( Define.BASE_FORCE.NONE.ordinal() );
	}

	// リストに追加
	public void addBaseList( CharacterBase b ){ mBaseList.add( b ); }

	// ゲッター
	public List<CharacterBase> getList(){ return mBaseList; }
}
