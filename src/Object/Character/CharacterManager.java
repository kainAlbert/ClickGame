package Object.Character;

import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import Application.Application;

public class CharacterManager {

	private List<CharacterBase> mBaseList;
	private BasePosReader mBR;

	// コンストラクタ
	public CharacterManager( Application app, Container c ){

		// リストを生成
		mBaseList = new ArrayList<CharacterBase>();

		// 位置リーダークラス生成
		mBR = new BasePosReader();

//		// プレイヤーと敵のカード生成
//		for(int i=0;i<5;i++){
//
//			// 生成
//			Base b = new Base( app, c, new GSvector2( Define.BASE_FIRST_X + i * Define.BASE_SIZE.x * 2, Define.BASE_PLAYER_Y ), i );
//
//			// リストに追加
//			mBaseList.add( b );
//		}
//		for(int i=0;i<5;i++){
//
//			// 生成
//			Base en = new Base( app, c, new GSvector2( Define.BASE_FIRST_X + i * Define.BASE_SIZE.x * 2, Define.BASE_ENEMY_Y ), i+5 );
//
//			// リストに追加
//			mBaseList.add( en );
//		}
	}

	// 更新
	public void update(){

		// 各カードの更新
		for( int i=0; i<mBaseList.size(); i++ ){

			mBaseList.get(i).update();
		}
	}

	// リストに追加
	public void addBaseList( CharacterBase b ){ mBaseList.add( b ); }

	// ゲッター
	public List<CharacterBase> getList(){ return mBaseList; }
}
