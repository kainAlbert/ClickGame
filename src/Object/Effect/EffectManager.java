package Object.Effect;

import java.util.ArrayList;
import java.util.List;

import Application.Define;
import Object.Character.CharacterBase;

public class EffectManager {

	private List<CharacterBase> mEffectList;

	// コンストラクタ
	public EffectManager(){

		mEffectList = new ArrayList<CharacterBase>();

		// 各エフェクト生成
		CharacterBase b = new StartEffect( "blue", Define.TYPE_PLAYER );
		CharacterBase r = new StartEffect( "red", Define.TYPE_ENEMY );

		mEffectList.add( b );
		mEffectList.add( r );

		CharacterBase[] hp = new CharacterBase[3];

		for( int i=0; i<hp.length; i++ ){

			hp[i] = new HPEnergyEffect( i );
			mEffectList.add( hp[i] );
		}
	}

	// 更新
	public void update(){

		for( int i=0; i<mEffectList.size(); i++ ){

			mEffectList.get(i).update();

			// 死亡処理
			if( !mEffectList.get(i).getIsDead() ) continue;

			mEffectList.remove(i);
		}
	}

	// リストに追加
	public void addEffectList( CharacterBase e ){ mEffectList.add( e ); }

	// ゲッター
	public List<CharacterBase> getEffectList(){ return mEffectList; }
}
