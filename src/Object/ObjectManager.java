package Object;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.Base;
import Object.Character.CharacterBase;

public class ObjectManager {

	List<CharacterBase> mBaseList;

	public ObjectManager( Application app, Container c ){

		// リストを生成
		mBaseList = new ArrayList<CharacterBase>();

		// プレイヤーと敵のカード生成
		for(int i=0;i<5;i++){

			// 生成
			Base b = new Base( app, c, new GSvector2( Define.BASE_FIRST_X + i * Define.BASE_SIZE.x * 2, Define.BASE_PLAYER_Y ), i );

			// リストに追加
			mBaseList.add( b );
		}
		for(int i=0;i<5;i++){

			// 生成
			Base en = new Base( app, c, new GSvector2( Define.BASE_FIRST_X + i * Define.BASE_SIZE.x * 2, Define.BASE_ENEMY_Y ), i+5 );

			// リストに追加
			mBaseList.add( en );
		}
	}

	// 更新
	public void update(){

		// 各カードの更新
		for( int i=0; i<mBaseList.size(); i++ ){

			mBaseList.get(i).update();
		}
	}

	// てすと
	public void test( int number ){

		mBaseList.get(number).test();
	}

	// ゲッター
	public List<CharacterBase> getList(){ return mBaseList; }
}
