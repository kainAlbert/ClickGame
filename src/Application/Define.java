package Application;

public interface Define {

	// ウィンドウ関連
	GSvector2 WINDOW_SIZE = new GSvector2( 800, 600 );

	// プレイヤー属性
	int TYPE_PLAYER = 1;
	int TYPE_ENEMY = 2;

	// ベース関連
	int BASE_FIRST_X = 100;
	int BASE_PLAYER_Y = 400;
	int BASE_ENEMY_Y = 50;
	GSvector2 BASE_SIZE = new GSvector2( 50, 50 );
	GSvector2 BASE_RESIZE = new GSvector2( 256, 256 );
	enum BASE_FORCE{ NONE, PLAYER, ENEMY }
	enum BASE_TYPE{ BASE, ENERGY, ATTACK, GUARD }
}
