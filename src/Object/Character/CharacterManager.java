package Object.Character;

import java.util.ArrayList;
import java.util.List;

import Application.Application;
import Application.Define;
import Application.MesgRecvThread;
import Application.Panel;

public class CharacterManager {

	private List<CharacterBase> mBaseList;
	private List<CharacterBase> mPlayerBulletList;
	private List<CharacterBase> mEnemyBulletList;
	private double mEnergy;
	private int[] mHP;

	// コンストラクタ
	public CharacterManager( Application app, Panel p ){

		// リストを生成
		mBaseList = new ArrayList<CharacterBase>();
		mPlayerBulletList = new ArrayList<CharacterBase>();
		mEnemyBulletList = new ArrayList<CharacterBase>();

		mEnergy = 0;
		mHP = new int[3];

		for( int i=0; i<mHP.length; i++ ){

			mHP[i] = Define.BASE_INIT_HP;
		}
	}

	// 初期化
	public void initialize( Application app, Panel p ){

		// ベースがなければ作成
		if( mBaseList.size() == 0 ){
			// ベース作成
			BasePosReader.readBasePos( app, p );
		}
	}

	// 更新
	public void update(){

		// 各ベースの更新
		updateList(mBaseList);
		updateList(mPlayerBulletList);
		updateList(mEnemyBulletList);

		// 負け処理
		if( mHP[ Application.getID() ] > 0 || Application.getObj().getIsEnd() ) return;

		Application.getObj().isLose();

		MesgRecvThread.outServer( Application.getID() + Define.STR_D + Define.STR_GAME_OVER );
	}

	// リスト更新
	private void updateList( List<CharacterBase> list ){

		for( int i=0; i<list.size(); i++ ){

			list.get(i).update();

			if( !list.get(i).getIsDead() ) continue;

			list.remove(i);
		}
	}

	// リスト内の本拠地以外のベースを初期位置に移動
	public void moveBase(){

		for( int i=0; i<mBaseList.size(); i++ ){

			if( mBaseList.get(i).getType() == Define.BASE_TYPE.BASE.ordinal() ) continue;

			mBaseList.get(i).move();
		}
	}


	// 勢力変更
	public void changeForce( int number, int id ){

		CharacterBase b = mBaseList.get(number);

		if( b.getForce() == Define.BASE_FORCE.NONE.ordinal() ){

			if( returnForceNum( id ) >= Define.BASE_SELECT_NUM ) return;

			b.changeForce( id );
			b.initialize();
			return;
		}

		if( b.getForce() != id ) return;

		b.changeForce( Define.BASE_FORCE.NONE.ordinal() );
	}

	// 指定した勢力の数を返す
	private int returnForceNum( int force ){

		int num = 0;

		for( int i=0; i<mBaseList.size(); i++ ){

			if( mBaseList.get(i).getForce() == force ) num++;
		}

		return num;
	}

	// リストに追加
	public void addBaseList( CharacterBase b ){ mBaseList.add( b ); }
	public void addPlayerBulletList( CharacterBase b ){ mPlayerBulletList.add( b ); }
	public void addEnemyBulletList( CharacterBase b ){ mEnemyBulletList.add( b ); }

	// エネルギー加算
	public void addEnergy( double e ){ mEnergy += e; }

	// ダメージ
	public void damage( int damage, int force ){ mHP[ force ] -= damage; }

	// ゲッター
	public List<CharacterBase> getBaseList(){ return mBaseList; }
	public List<CharacterBase> getPlayerBulletList(){ return mPlayerBulletList; }
	public List<CharacterBase> getEnemyBulletList(){ return mEnemyBulletList; }
	public double getEnergy(){ return mEnergy; }
	public int getHP( int force ){ return mHP[ force ]; }
}
