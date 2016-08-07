package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 1200, 900 );

	// プレイヤー属性
	int TYPE_PLAYER = 1;
	int TYPE_ENEMY = 2;

	// ベース関連
	int BASE_SELECT_NUM = 4;
	int BASE_INIT_HP = 200;
	int BASE_SIZE = 80;
	int BASE_RESIZE = 128;
	int BASE_SPEED = 30;
	GSvector2 BASE_PLAYER_INIT_POS = new GSvector2( 0, 0 );
	GSvector2 BASE_ENEMY_INIT_POS = new GSvector2( 1100, 770 );
	enum BASE_FORCE{ NONE, PLAYER, ENEMY }
	enum BASE_TYPE{ BASE, ENERGY, ATTACK, GUARD }
	int BASE_TYPE_NUM = 4;
	String FILE_NAME[] = { "base", "energy", "attack", "guard" };
	double ENERGY_ADD = 0.33333;
	double GUARD_ENERGY = 0.5;
	double GUARD_DISTANCE = 400;

	// ベース工場関連
	int FACTORY_TIME = 600;
	GSvector2 FACTORY_POS_MIN = new GSvector2( 0, BASE_SIZE );
	GSvector2 FACTORY_POS_MAX = new GSvector2( WINDOW_SIZE.x - BASE_SIZE - 100, WINDOW_SIZE.y - BASE_SIZE * 2 );

	// 弾関連
	int BULLET_TIMER = 30;
	int BULLET_MAX_TIMER = 120;
	int BULLET_SPEED = 3;
	int BULLET_SIZE = 50;

	// エフェクト
	GSvector2 IMPACT_EFFECT_RESIZE = new GSvector2( 240, 240 );
	int IMPACT_EFFECT_TIME = 5;
	GSvector2 WIN_LOSE_EFFECT_RESIZE = new GSvector2( 256, 128 );
	GSvector2 WIN_LOSE_EFFECT_SIZE = new GSvector2( 800, 600 );
	GSvector2 WIN_LOSE_EFFECT_POS = new GSvector2( ( WINDOW_SIZE.x - WIN_LOSE_EFFECT_SIZE.x ) / 2, ( WINDOW_SIZE.y - WIN_LOSE_EFFECT_SIZE.y ) / 2 );


	// サーバーに送る文字
	String STR_D = ",";
	String STR_GAME_START = "gameStart";
	String STR_GAME_OVER = "gameOver";
	String STR_CHANGE_FORCE = "changeForce";
	String STR_CREATE_BULLET = "createBullet";
	String STR_COLLISION_GUARD = "collisionGuard";
	String STR_ALL_MOVE = "allMove";
	String STR_MOVE_LIST = "moveList";
}
