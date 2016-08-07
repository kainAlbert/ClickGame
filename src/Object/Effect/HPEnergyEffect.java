package Object.Effect;

import Application.Application;
import Application.Define;
import Application.GSvector2;
import Object.Character.CharacterBase;
import Object.Character.CharacterManager;

public class HPEnergyEffect extends CharacterBase{

	// コンストラクタ
	public HPEnergyEffect( int force ){

		super( null, null, "hp", new GSvector2(), 0, 0 );

		GSvector2[] pos = {
				new GSvector2( Define.ENERGY_EFFECT_POS.x, Define.ENERGY_EFFECT_POS.y ),
				new GSvector2( Define.HP_EFFECT_POS.x, Define.HP_EFFECT_POS.y ),
				new GSvector2( Define.HP_EFFECT_POS.x, Define.HP_EFFECT_POS.y + Define.HP_ENERGY_EFFECT_SIZE.y )
		};

		mPos = pos[ force ];
		mForce = force;
		mSize = new GSvector2( Define.HP_ENERGY_EFFECT_SIZE.x, Define.HP_ENERGY_EFFECT_SIZE.y );
		mReSize = new GSvector2( Define.HP_ENERGY_EFFECT_RESIZE, Define.HP_ENERGY_EFFECT_RESIZE );
		mFirstReSize = new GSvector2( Define.HP_ENERGY_EFFECT_RESIZE, Define.HP_ENERGY_EFFECT_RESIZE );

		mReSize.x = ( force + 1 ) * Define.HP_ENERGY_EFFECT_RESIZE;
	}

	// 更新
	public void update(){

		double point = 0;
		double maxPoint = 0;

		CharacterManager cm = Application.getObj().getCM();

		// HPの場合
		if( mForce != Define.BASE_FORCE.NONE.ordinal() ){

			point = cm.getHP( mForce );
			maxPoint = Define.BASE_INIT_HP;
		}

		// エネルギーの場合
		if( mForce == Define.BASE_FORCE.NONE.ordinal() ){

			point = cm.getEnergy();
			maxPoint = Define.MAX_ENERGY;
		}

		update( point, maxPoint );
	}

	// ゲージ更新
	public void update( double point, double maxPoint ){

		double length = point / maxPoint * Define.HP_ENERGY_EFFECT_SIZE.x;

		mSize.x = length;
	}
}