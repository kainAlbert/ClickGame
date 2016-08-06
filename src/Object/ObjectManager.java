package Object;

import Application.Application;
import Application.Panel;
import Object.Character.CharacterManager;

public class ObjectManager {

	private CharacterManager mCM;

	// コンストラクタ
	public ObjectManager( Application app, Panel p ){

		mCM = new CharacterManager( app, p );
	}

	// 更新
	public void update(){

		mCM.update();
	}

	// ゲッター
	public CharacterManager getCM(){ return mCM; }
}
