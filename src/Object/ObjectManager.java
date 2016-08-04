package Object;

import java.awt.Container;

import Application.Application;
import Object.Character.CharacterManager;

public class ObjectManager {

	private CharacterManager mCM;

	// コンストラクタ
	public ObjectManager( Application app, Container c ){

		mCM = new CharacterManager( app, c );
	}

	// 更新
	public void update(){

		mCM.update();
	}

	// ゲッター
	public CharacterManager getCM(){ return mCM; }
}
